package com.example.ui.game

import androidx.compose.animation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.NanoData
import com.example.data.Nanoparticle
import com.example.ui.theme.*
import kotlin.math.abs

// Model for a cell position
data class Position(val r: Int, val c: Int)

// Procedural Level Data
data class LevelData(
    val nanoparticles: List<Nanoparticle>,
    val starts: List<Position>,
    val ends: List<Position>,
    val solutionPaths: List<List<Position>>
)

object NumberlinkGenerator {
    const val GridSize = 7
    const val PathCount = 5

    // Generate a new layout procedurally (fully filled & distinct sectors)
    fun generateNewLevel(): LevelData {
        var chosenNps = listOf<Nanoparticle>()
        var attemptsChoosing = 0
        while (attemptsChoosing < 500) {
            attemptsChoosing++
            val pool = NanoData.nanoparticles.shuffled()
            val temp = mutableListOf<Nanoparticle>()
            val sectors = mutableSetOf<String>()
            for (np in pool) {
                val sector = NanoData.getSectorAndEmoji(np.symbol).first
                if (sector !in sectors) {
                    sectors.add(sector)
                    temp.add(np)
                }
                if (temp.size == PathCount) {
                    break
                }
            }
            if (temp.size == PathCount) {
                chosenNps = temp
                break
            }
        }
        if (chosenNps.size < PathCount) {
            chosenNps = NanoData.nanoparticles.take(PathCount)
        }

        var attempts = 0
        while (attempts < 2500) {
            attempts++
            val grid = Array(GridSize) { IntArray(GridSize) { -1 } } // -1 means empty
            val paths = mutableListOf<List<Position>>()
            var success = true

            // Generate sequence lengths that are reasonable (e.g. 4 to 8 cells each)
            val targetLengths = List(PathCount) { (4..8).random() }

            for (p in 0 until PathCount) {
                val path = walkRandomPathWithTarget(grid, p, targetLengths[p])
                if (path == null) {
                    success = false
                    break
                }
                paths.add(path)
            }

            if (success) {
                val starts = paths.map { it.first() }
                val ends = paths.map { it.last() }

                if (paths.all { it.size >= 3 }) {
                    return LevelData(
                        nanoparticles = chosenNps,
                        starts = starts,
                        ends = ends,
                        solutionPaths = paths
                    )
                }
            }
        }

        // 100% full-fill fallback
        val fallbackNps = NanoData.nanoparticles.take(PathCount)
        return LevelData(
            nanoparticles = fallbackNps,
            starts = listOf(
                Position(0, 0),
                Position(2, 0),
                Position(4, 0),
                Position(6, 0),
                Position(0, 6)
            ),
            ends = listOf(
                Position(0, 5),
                Position(2, 6),
                Position(4, 6),
                Position(6, 6),
                Position(1, 6)
            ),
            solutionPaths = listOf(
                listOf(Position(0, 0), Position(0, 1), Position(0, 2), Position(0, 3), Position(0, 4), Position(0, 5)),
                listOf(Position(2, 0), Position(2, 1), Position(2, 2), Position(2, 3), Position(2, 4), Position(2, 5), Position(2, 6)),
                listOf(Position(4, 0), Position(4, 1), Position(4, 2), Position(4, 3), Position(4, 4), Position(4, 5), Position(4, 6)),
                listOf(Position(6, 0), Position(6, 1), Position(6, 2), Position(6, 3), Position(6, 4), Position(6, 5), Position(6, 6)),
                listOf(Position(0, 6), Position(1, 6))
            )
        )
    }

    private fun walkRandomPathWithTarget(grid: Array<IntArray>, pathId: Int, targetLength: Int): List<Position>? {
        val freeCells = mutableListOf<Position>()
        for (r in 0 until GridSize) {
            for (c in 0 until GridSize) {
                if (grid[r][c] == -1) freeCells.add(Position(r, c))
            }
        }
        if (freeCells.isEmpty()) return null

        val start = freeCells.random()
        val path = mutableListOf(start)
        grid[start.r][start.c] = pathId

        var current = start
        for (len in 1 until targetLength) {
            val neighbors = getFreeNeighbors(grid, current)
            if (neighbors.isEmpty()) {
                break
            }
            val next = neighbors.random()
            grid[next.r][next.c] = pathId
            path.add(next)
            current = next
        }

        if (path.size < 3 || path.size != targetLength) {
            for (p in path) {
                grid[p.r][p.c] = -1
            }
            return null
        }

        return path
    }

    private fun getFreeNeighbors(grid: Array<IntArray>, pos: Position): List<Position> {
        val neighbors = mutableListOf<Position>()
        val dr = intArrayOf(-1, 1, 0, 0)
        val dc = intArrayOf(0, 0, -1, 1)
        for (i in 0 until 4) {
            val nr = pos.r + dr[i]
            val nc = pos.c + dc[i]
            if (nr in 0 until GridSize && nc in 0 until GridSize && grid[nr][nc] == -1) {
                neighbors.add(Position(nr, nc))
            }
        }
        return neighbors
    }
}

object NumberlinkGameState {
    var levelCount by mutableStateOf(1)
    var levelData by mutableStateOf<LevelData?>(null)
    var userPaths by mutableStateOf<Map<Int, List<Position>>>(emptyMap())
    private var isLoaded = false

    private fun Position.serialize(): String = "$r,$c"
    private fun String.toPosition(): Position {
        val parts = this.split(",")
        return Position(parts[0].toInt(), parts[1].toInt())
    }

    private fun List<Position>.serializeList(): String = joinToString("|") { it.serialize() }
    private fun String.toPositionList(): List<Position> {
        if (this.isEmpty()) return emptyList()
        return this.split("|").map { it.toPosition() }
    }

    private fun Map<Int, List<Position>>.serializePaths(): String {
        return entries.joinToString(";") { "${it.key}:${it.value.serializeList()}" }
    }
    private fun String.toPathsMap(): Map<Int, List<Position>> {
        if (this.isEmpty()) return emptyMap()
        val map = mutableMapOf<Int, List<Position>>()
        this.split(";").forEach {
            val parts = it.split(":")
            if (parts.size == 2) {
                val key = parts[0].toInt()
                val value = parts[1].toPositionList()
                map[key] = value
            }
        }
        return map
    }

    fun loadFromPrefs(context: android.content.Context) {
        if (isLoaded) return
        val prefs = context.getSharedPreferences("numberlink_prefs", android.content.Context.MODE_PRIVATE)
        if (prefs.contains("level_nps")) {
            val npsStr = prefs.getString("level_nps", "").orEmpty()
            val startsStr = prefs.getString("level_starts", "").orEmpty()
            val endsStr = prefs.getString("level_ends", "").orEmpty()
            val levelCountSaved = prefs.getInt("level_count", 1)
            val userPathsStr = prefs.getString("user_paths", "").orEmpty()

            if (npsStr.isNotEmpty() && startsStr.isNotEmpty() && endsStr.isNotEmpty()) {
                val symbols = npsStr.split(",")
                val npList = symbols.mapNotNull { sym ->
                    NanoData.nanoparticles.firstOrNull { it.symbol == sym }
                }
                val startsList = startsStr.toPositionList()
                val endsList = endsStr.toPositionList()

                if (npList.size == NumberlinkGenerator.PathCount &&
                    startsList.size == NumberlinkGenerator.PathCount &&
                    endsList.size == NumberlinkGenerator.PathCount
                ) {
                    levelData = LevelData(
                        nanoparticles = npList,
                        starts = startsList,
                        ends = endsList,
                        solutionPaths = emptyList()
                    )
                    levelCount = levelCountSaved
                    userPaths = userPathsStr.toPathsMap()
                    isLoaded = true
                    return
                }
            }
        }
        getOrInitLevel(context)
        isLoaded = true
    }

    fun getOrInitLevel(context: android.content.Context? = null): LevelData {
        var current = levelData
        if (current == null) {
            current = NumberlinkGenerator.generateNewLevel()
            levelData = current
            userPaths = current.starts.mapIndexed { index, startPos -> index to listOf(startPos) }.toMap()
            if (context != null) {
                saveToPrefs(context)
            }
        }
        return current
    }

    fun saveToPrefs(context: android.content.Context) {
        val currentLevel = levelData ?: return
        val prefs = context.getSharedPreferences("numberlink_prefs", android.content.Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("level_nps", currentLevel.nanoparticles.joinToString(",") { it.symbol })
            putString("level_starts", currentLevel.starts.serializeList())
            putString("level_ends", currentLevel.ends.serializeList())
            putInt("level_count", levelCount)
            putString("user_paths", userPaths.serializePaths())
            apply()
        }
    }

    fun resetLevelAndLoad(context: android.content.Context, newLevel: Boolean) {
        if (newLevel) {
            val newlyGenerated = NumberlinkGenerator.generateNewLevel()
            levelData = newlyGenerated
            levelCount++
            userPaths = newlyGenerated.starts.mapIndexed { index, startPos -> index to listOf(startPos) }.toMap()
        } else {
            val current = getOrInitLevel(context)
            userPaths = current.starts.mapIndexed { index, startPos -> index to listOf(startPos) }.toMap()
        }
        saveToPrefs(context)
    }
}

@Composable
fun NumberlinkGameScreen() {
    val context = androidx.compose.ui.platform.LocalContext.current
    NumberlinkGameState.loadFromPrefs(context)

    val levelData = NumberlinkGameState.getOrInitLevel(context)
    val levelCount = NumberlinkGameState.levelCount
    val userPaths = NumberlinkGameState.userPaths

    // Active drawing color pair selected (-1 means none)
    var selectedPathId by remember { mutableStateOf(-1) }
    var showHelpDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }

    var gridWidthPx by remember { mutableStateOf(0f) }
    var gridHeightPx by remember { mutableStateOf(0f) }

    // Detect level generation change or reset
    fun resetLevelAndLoad(newLevel: Boolean) {
        NumberlinkGameState.resetLevelAndLoad(context, newLevel)
        selectedPathId = -1
        showSuccessDialog = false
    }

    // Colors mapping
    val pathColors = listOf(MatchRed, MatchBlue, MatchPurple, MatchYellow, MatchTeal)
    val startPositions = levelData.starts
    val endPositions = levelData.ends
    val nanoparticles = levelData.nanoparticles

    // Centralized touch helper for drawing & dragging linkage lines
    fun handleCellTouch(pos: Position, isStart: Boolean) {
        val startId = startPositions.indexOf(pos)
        val endId = endPositions.indexOf(pos)

        if (isStart) {
            if (startId != -1) {
                selectedPathId = startId
                NumberlinkGameState.userPaths = NumberlinkGameState.userPaths.toMutableMap().apply {
                    put(startId, listOf(pos))
                }
            } else if (endId != -1) {
                selectedPathId = endId
                NumberlinkGameState.userPaths = NumberlinkGameState.userPaths.toMutableMap().apply {
                    put(endId, listOf(pos))
                }
            } else {
                // Tapped an intermediate path cell -> choose path and retract to here
                val activePathIndex = NumberlinkGameState.userPaths.entries.firstOrNull { pos in it.value }?.key
                if (activePathIndex != null) {
                    selectedPathId = activePathIndex
                    val path = NumberlinkGameState.userPaths[activePathIndex].orEmpty()
                    val idx = path.indexOf(pos)
                    if (idx >= 0) {
                        NumberlinkGameState.userPaths = NumberlinkGameState.userPaths.toMutableMap().apply {
                            put(activePathIndex, path.subList(0, idx + 1))
                        }
                    }
                }
            }
        } else {
            // Drag move
            val activePathId = selectedPathId
            if (activePathId != -1) {
                val currentPath = NumberlinkGameState.userPaths[activePathId].orEmpty()
                if (currentPath.isNotEmpty()) {
                    val lastPos = currentPath.last()
                    if (pos == lastPos) return

                    if (pos in currentPath) {
                        // Dragging backward -> retract path to this cell
                        val idx = currentPath.indexOf(pos)
                        if (idx >= 0) {
                            NumberlinkGameState.userPaths = NumberlinkGameState.userPaths.toMutableMap().apply {
                                put(activePathId, currentPath.subList(0, idx + 1))
                            }
                        }
                    } else if (isAdjacent(lastPos, pos)) {
                        // Dragging to adjacent cell
                        // Cut/retract any other occupying path of a different color
                        val occupyingPathId = NumberlinkGameState.userPaths.entries.firstOrNull { pos in it.value }?.key
                        if (occupyingPathId != null) {
                            if (occupyingPathId != activePathId) {
                                val occPath = NumberlinkGameState.userPaths[occupyingPathId].orEmpty()
                                val idx = occPath.indexOf(pos)
                                if (idx >= 0) {
                                    NumberlinkGameState.userPaths = NumberlinkGameState.userPaths.toMutableMap().apply {
                                        val newOccPath = occPath.subList(0, idx)
                                        put(
                                            occupyingPathId,
                                            if (newOccPath.isEmpty()) listOf(levelData.starts[occupyingPathId]) else newOccPath
                                        )
                                    }
                                }
                            }
                        }

                        // Append to active path
                        NumberlinkGameState.userPaths = NumberlinkGameState.userPaths.toMutableMap().apply {
                            put(activePathId, currentPath + pos)
                        }
                    }
                }
            } else {
                // Not actively drawing, but dragged over a start/end node -> begin it
                if (startId != -1) {
                    selectedPathId = startId
                    NumberlinkGameState.userPaths = NumberlinkGameState.userPaths.toMutableMap().apply {
                        put(startId, listOf(pos))
                    }
                } else if (endId != -1) {
                    selectedPathId = endId
                    NumberlinkGameState.userPaths = NumberlinkGameState.userPaths.toMutableMap().apply {
                        put(endId, listOf(pos))
                    }
                }
            }
        }
    }

    val currentHandleCellTouchState = rememberUpdatedState<(Position, Boolean) -> Unit> { pos, isStart ->
        handleCellTouch(pos, isStart)
    }

    // Check if the game is successfully solved!
    val isSolved = remember(userPaths, levelData) {
        var allConnected = true
        for (i in levelData.starts.indices) {
            val p = userPaths[i].orEmpty()
            
            val completed = p.isNotEmpty() && (
                (p.first() == levelData.starts[i] && p.last() == levelData.ends[i]) ||
                (p.first() == levelData.ends[i] && p.last() == levelData.starts[i])
            )
            if (!completed) {
                allConnected = false
                break
            }
        }

        // Ensure no overlapping cells across paths
        val uniqueCells = mutableSetOf<Position>()
        var totalCellsCount = 0
        for (i in levelData.starts.indices) {
            val p = userPaths[i].orEmpty()
            uniqueCells.addAll(p)
            totalCellsCount += p.size
        }

        allConnected && (uniqueCells.size == totalCellsCount)
    }

    // Automatically trigger success dialog when solved
    LaunchedEffect(isSolved) {
        if (isSolved) {
            showSuccessDialog = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NaturalCream)
            .padding(16.dp)
            .testTag("numberlink_game_parent"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Upper Title & Progress
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Nano-Bağlantı Oyunu",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = DarkGreen
                )
                Text(
                    text = "Lise Kademesi Eğitici Bulmaca",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextLight
                )
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = LightGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Seviye $levelCount",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    fontWeight = FontWeight.Bold,
                    color = DarkGreen,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Spacer(modifier = Modifier.height(4.dp))

        // GRID WORKSPACE
        Box(
            modifier = Modifier
                .size(310.dp)
                .background(SoftWhite, RoundedCornerShape(16.dp))
                .border(2.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
                .padding(10.dp)
                .onGloballyPositioned { coordinates ->
                    gridWidthPx = coordinates.size.width.toFloat()
                    gridHeightPx = coordinates.size.height.toFloat()
                }
                .pointerInput(levelData) {
                    awaitPointerEventScope {
                        while (true) {
                            val down = awaitFirstDown(requireUnconsumed = false)
                            var lastCellPos: Position? = null
                            val offset = down.position
                            if (gridWidthPx > 0 && gridHeightPx > 0) {
                                val c = (offset.x / (gridWidthPx / NumberlinkGenerator.GridSize.toFloat())).toInt().coerceIn(0, NumberlinkGenerator.GridSize - 1)
                                val r = (offset.y / (gridHeightPx / NumberlinkGenerator.GridSize.toFloat())).toInt().coerceIn(0, NumberlinkGenerator.GridSize - 1)
                                val initialPos = Position(r, c)
                                currentHandleCellTouchState.value(initialPos, true)
                                lastCellPos = initialPos
                            }
                            
                            while (true) {
                                val event = awaitPointerEvent()
                                val anyPressed = event.changes.any { it.pressed }
                                if (!anyPressed) {
                                    selectedPathId = -1
                                    NumberlinkGameState.saveToPrefs(context)
                                    break
                                }
                                val change = event.changes.firstOrNull()
                                if (change != null) {
                                    val currentOffset = change.position
                                    if (gridWidthPx > 0 && gridHeightPx > 0) {
                                        val cc = (currentOffset.x / (gridWidthPx / NumberlinkGenerator.GridSize.toFloat())).toInt().coerceIn(0, NumberlinkGenerator.GridSize - 1)
                                        val rr = (currentOffset.y / (gridHeightPx / NumberlinkGenerator.GridSize.toFloat())).toInt().coerceIn(0, NumberlinkGenerator.GridSize - 1)
                                        val currentCellPos = Position(rr, cc)
                                        if (currentCellPos != lastCellPos) {
                                            currentHandleCellTouchState.value(currentCellPos, false)
                                            lastCellPos = currentCellPos
                                        }
                                    }
                                    change.consume()
                                }
                            }
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            // Draw connection paths underneath using Canvas
            Canvas(modifier = Modifier.fillMaxSize()) {
                val cellSize = size.width / NumberlinkGenerator.GridSize.toFloat()

                for (id in startPositions.indices) {
                    val path = userPaths[id].orEmpty()
                    if (path.isNotEmpty()) {
                        val color = pathColors[id]

                        for (i in 0 until path.size - 1) {
                            val startCell = path[i]
                            val endCell = path[i + 1]

                            val startOffset = Offset(
                                x = (startCell.c + 0.5f) * cellSize,
                                y = (startCell.r + 0.5f) * cellSize
                            )
                            val endOffset = Offset(
                                x = (endCell.c + 0.5f) * cellSize,
                                y = (endCell.r + 0.5f) * cellSize
                            )

                            // Draw a beautiful modern chemistry chain line link
                            drawCircle(
                                color = color.copy(alpha = 0.5f),
                                radius = size.width * 0.035f,
                                center = startOffset
                            )
                            drawCircle(
                                color = color.copy(alpha = 0.5f),
                                radius = size.width * 0.035f,
                                center = endOffset
                            )

                            drawLine(
                                color = color,
                                start = startOffset,
                                end = endOffset,
                                strokeWidth = size.width * 0.055f,
                                cap = StrokeCap.Round
                            )

                            // Inner core line
                            drawLine(
                                color = Color.White.copy(alpha = 0.6f),
                                start = startOffset,
                                end = endOffset,
                                strokeWidth = size.width * 0.012f,
                                cap = StrokeCap.Round
                            )
                        }
                    }
                }
            }

            // Interactive grid cells
            Column(modifier = Modifier.fillMaxSize()) {
                for (r in 0 until NumberlinkGenerator.GridSize) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        for (c in 0 until NumberlinkGenerator.GridSize) {
                            val currentPos = Position(r, c)

                            val startId = startPositions.indexOf(currentPos)
                            val endId = endPositions.indexOf(currentPos)

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .testTag("grid_cell_${r}_${c}"),
                                contentAlignment = Alignment.Center
                            ) {
                                // Node drawings
                                if (startId != -1) {
                                    val isCurrentSelected = selectedPathId == startId
                                    Box(
                                        modifier = Modifier
                                            .size(31.dp)
                                            .background(
                                                color = Color.White,
                                                shape = CircleShape
                                            )
                                            .border(
                                                width = if (isCurrentSelected) 3.dp else 1.5.dp,
                                                color = Color.Black,
                                                shape = CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = nanoparticles[startId].symbol,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 9.5.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                } else if (endId != -1) {
                                    // Application target circle showing sector emoji
                                    val (sectorName, emoji) = NanoData.getSectorAndEmoji(nanoparticles[endId].symbol)
                                    Box(
                                        modifier = Modifier
                                            .size(31.dp)
                                            .border(
                                                1.5.dp,
                                                Color.Black,
                                                CircleShape
                                            )
                                            .background(
                                                Color.White,
                                                CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = emoji,
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                } else {
                                    // Normal grid griddot indicator
                                    Box(
                                        modifier = Modifier
                                            .size(5.dp)
                                            .background(Color(0xFFCABFA8).copy(alpha = 0.5f), CircleShape)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Interactive control buttons
        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { resetLevelAndLoad(false) },
                colors = ButtonDefaults.buttonColors(containerColor = WarmSlate, contentColor = TextDark),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.testTag("reset_connections_button")
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Sıfırla")
                Spacer(modifier = Modifier.width(6.dp))
                Text("Bağları Temizle", style = MaterialTheme.typography.bodyMedium)
            }

            Button(
                onClick = { showHelpDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = PaleOrange, contentColor = Color(0xFF5D1F00)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.testTag("how_to_play_button")
            ) {
                Icon(imageVector = Icons.Default.QuestionMark, contentDescription = "Nasıl Oynanır")
                Spacer(modifier = Modifier.width(6.dp))
                Text("Nasıl Oynanır?", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Action Trigger for New Level
        Button(
            onClick = { resetLevelAndLoad(true) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .height(46.dp)
                .testTag("generate_new_level_button"),
            colors = ButtonDefaults.buttonColors(containerColor = WarmGreen),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(imageVector = Icons.Default.Casino, contentDescription = "Karıştır")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Yeni Seviye Sentezle (Rastgele Bulmaca)", fontWeight = FontWeight.Bold)
        }
    }

    // Help Dialog Instructions
    if (showHelpDialog) {
        AlertDialog(
            onDismissRequest = { showHelpDialog = false },
            title = {
                Text(
                    "Nasıl Oynanır?",
                    fontWeight = FontWeight.Bold,
                    color = DarkGreen,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Column {
                    Text(
                        "Bu oyun, bir Nano-Bağlantı bulmacasıdır. Amacınız aşağıdaki 5 hedefi birbirine bağlamaktır:",
                        fontSize = 14.sp,
                        color = TextDark
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "1. Bir atom yuvarlağına tıklayarak 'bağlantıyı' başlatın.\n\n" +
                                "2. Yatay veya dikey komşu karelere parmağınızı sürükleyerek moleküler bağ yolu uzatın.\n\n" +
                                "3. Çizgiyi kendi hedef dairesine ulaştırın. Sola/sağa geri kaydırarak yolunuzu silebilirsiniz.\n\n" +
                                "4. Sürtünerek başka bir hattan geçerseniz o hat kesilir. Tüm 5 hattın da kesişmeden hedeflerine ulaşması gerekir!",
                        fontSize = 13.sp,
                        color = TextLight,
                        lineHeight = 17.sp
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { showHelpDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = WarmGreen)
                ) {
                    Text("Anladım")
                }
            },
            containerColor = SoftWhite
        )
    }

    // Success dialog displaying full scientific reports in Turkish
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { resetLevelAndLoad(true) },
            modifier = Modifier.testTag("success_report_dialog"),
            title = {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .background(LightGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Başarı",
                            tint = DarkGreen,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Bağlantılar Başarıyla Kuruldu!",
                        fontWeight = FontWeight.Black,
                        color = DarkGreen,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Sentezlenen Nanoparçacık Raporu:",
                        fontWeight = FontWeight.Bold,
                        color = TextDark,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    for (np in nanoparticles) {
                        val (sectorName, emoji) = NanoData.getSectorAndEmoji(np.symbol)
                        Card(
                            colors = CardDefaults.cardColors(containerColor = SoftSand),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Badge(containerColor = WarmGreen) {
                                        Text(np.symbol, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(3.dp))
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("${np.name} ($emoji $sectorName)", fontWeight = FontWeight.Bold, color = DarkGreen, fontSize = 13.sp)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Uygulama Alanı: ${np.mainApplication}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = TextDark
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = np.description,
                                    fontSize = 11.sp,
                                    color = TextLight,
                                    lineHeight = 13.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Yeşil Sentezi: ${np.greenSynthesisMethod}",
                                    fontSize = 10.sp,
                                    color = DarkGreen,
                                    fontWeight = FontWeight.SemiBold,
                                    lineHeight = 12.sp
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { resetLevelAndLoad(true) },
                    modifier = Modifier.fillMaxWidth().testTag("next_level_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = WarmGreen)
                ) {
                    Text("Harika! Sonraki Seviye", fontWeight = FontWeight.Bold)
                }
            },
            containerColor = SoftWhite
        )
    }
}

// Check Manhattan distance adjacent
private fun isAdjacent(p1: Position, p2: Position): Boolean {
    return (abs(p1.r - p2.r) == 1 && p1.c == p2.c) || (abs(p1.c - p2.c) == 1 && p1.r == p2.r)
}
