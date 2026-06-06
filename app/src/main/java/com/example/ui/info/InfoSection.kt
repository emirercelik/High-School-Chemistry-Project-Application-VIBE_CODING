package com.example.ui.info

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.NanoData
import com.example.data.Nanoparticle
import com.example.ui.theme.*

@Composable
fun InfoSectionScreen() {
    // 3 sub-sections within Info: 0 = Nanoparçacıklar, 1 = Yeşil İlkeler, 2 = Genel Kılavuz/Teori
    var activeSubTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NaturalCream)
            .padding(horizontal = 16.dp)
            .testTag("info_section_parent")
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Upper Section Summary
        Text(
            text = "Eğitici Kimya Kütüphanesi",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = DarkGreen
        )
        Text(
            text = "Nanoteknoloji ve Yeşil Kimya Ansiklopedisi",
            style = MaterialTheme.typography.bodySmall,
            color = TextLight
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Tab selection pills (Turkish labels)
        ScrollableTabRow(
            selectedTabIndex = activeSubTab,
            containerColor = Color.Transparent,
            edgePadding = 0.dp,
            divider = {},
            indicator = {}
        ) {
            val tabs = listOf("Nanoparçacıklar 🔬", "12 Yeşil İlke 🌿", "Temel Teori 🧬")
            tabs.forEachIndexed { index, title ->
                val selected = activeSubTab == index
                Tab(
                    selected = selected,
                    onClick = { activeSubTab = index },
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (selected) WarmGreen else SoftSand)
                        .testTag("info_subtab_$index"),
                    text = {
                        Text(
                            text = title,
                            color = if (selected) Color.White else TextDark,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Core dynamic view switching with smooth transitions
        Box(modifier = Modifier.weight(1f)) {
            when (activeSubTab) {
                0 -> NanoparticlesListTab()
                1 -> GreenPrinciplesListTab()
                2 -> TheoryConceptsListTab()
            }
        }
    }
}

@Composable
fun NanoparticlesListTab() {
    var searchQuery by remember { mutableStateOf("") }
    val nanoparticles = remember { NanoData.nanoparticles }

    // Selected nanoparticle for detailed dialog
    var selectedNp by remember { mutableStateOf<Nanoparticle?>(null) }

    // Filtering logic (fully supports Turkish letters)
    val filteredNpList = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            nanoparticles
        } else {
            val q = searchQuery.lowercase().trim()
            nanoparticles.filter {
                it.name.lowercase().contains(q) ||
                        it.symbol.lowercase().contains(q) ||
                        it.category.lowercase().contains(q)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Search text fields completely in Turkish
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Nanoparçacık veya Simge Ara (ör: Ag, Altın)...", fontSize = 13.sp, color = TextLight) },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Ara", tint = WarmGreen) },
            trailingIcon = if (searchQuery.isNotEmpty()) {
                {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Temizle", tint = TextLight)
                    }
                }
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("nanoparticles_search_field"),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = SoftWhite,
                unfocusedContainerColor = SoftWhite,
                focusedIndicatorColor = WarmGreen,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (filteredNpList.isEmpty()) {
            // Empty state illustration
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.SearchOff,
                    contentDescription = "Sonuç yok",
                    tint = TextLight,
                    modifier = Modifier.size(54.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Aramanızla eşleşen hiçbir metal nanoparçacık bulunamadı.",
                    textAlign = TextAlign.Center,
                    color = TextLight,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            // Show list of 30 nanoparticles in a beautiful grid of cards and expandable parameters
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("nanoparticles_grid"),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(filteredNpList) { np ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedNp = np }
                            .testTag("nanoparticle_card_${np.symbol}"),
                        colors = CardDefaults.cardColors(containerColor = SoftWhite),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Circular Chemical symbol avatar
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .background(LightGreen, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = np.symbol,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 15.sp,
                                    color = DarkGreen
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = np.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = TextDark,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = np.category,
                                fontSize = 11.sp,
                                color = TextLight,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Box(
                                modifier = Modifier
                                    .background(WarmSlate, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = np.sizeRange,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Modal dialog with complete, deep detailed profile for selected nanoparticle
    if (selectedNp != null) {
        val np = selectedNp!!
        AlertDialog(
            onDismissRequest = { selectedNp = null },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(WarmGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = np.symbol,
                            color = Color.White,
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp
                        )
                    }
                    val (sectorName, emoji) = NanoData.getSectorAndEmoji(np.symbol)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "${np.name} $emoji",
                            fontWeight = FontWeight.Bold,
                            color = DarkGreen,
                            fontSize = 16.sp,
                            lineHeight = 20.sp
                        )
                        Text(
                            text = "${np.category} | $sectorName",
                            fontSize = 12.sp,
                            color = TextLight
                        )
                    }
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 420.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    HorizontalDivider(color = WarmSlate)
                    Spacer(modifier = Modifier.height(10.dp))

                    // Detail Row 1: Boyut Aralığı
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Icon(imageVector = Icons.Default.Straighten, contentDescription = "Boyut", tint = WarmGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Ortalama Boyut Aralığı: ", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextDark)
                        Text(np.sizeRange, fontSize = 13.sp, color = TextLight)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Detail Row 2: Ana Uygulama
                    Text("Ana Kullanım ve Uygulama Alanı:", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextDark)
                    Card(
                        colors = CardDefaults.cardColors(containerColor = PaleOrange.copy(alpha = 0.2f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.AddHomeWork, contentDescription = "Sektör", tint = WarmOrange, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(np.mainApplication, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D1F00), lineHeight = 15.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Detail Row 3: Açıklama
                    Text("Özellikleri ve Kimyasal Davranışı:", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextDark)
                    Text(
                        text = np.description,
                        fontSize = 12.sp,
                        color = TextDark,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Detail Row 4: Yeşil Sentez
                    Text("Yeşil (Çevre Dostu) Sentez Metodu:", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = DarkGreen)
                    Card(
                        colors = CardDefaults.cardColors(containerColor = LightGreen.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .border(1.dp, WarmGreen.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Icon(imageVector = Icons.Default.Eco, contentDescription = "Eko", tint = WarmGreen, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = np.greenSynthesisMethod,
                                fontSize = 12.sp,
                                color = DarkGreen,
                                lineHeight = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { selectedNp = null },
                    colors = ButtonDefaults.buttonColors(containerColor = WarmGreen)
                ) {
                    Text("Kapat")
                }
            },
            containerColor = SoftWhite
        )
    }
}

// 12 Principles List Tab showing all 12 items
@Composable
fun GreenPrinciplesListTab() {
    val principles = remember { NanoData.greenPrinciples }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("green_principles_list"),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(principles) { pr ->
            Card(
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("principle_card_${pr.number}"),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp)
                ) {
                    // Number Circle
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(WarmGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = pr.number.toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = pr.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = TextDark
                        )
                        Text(
                            text = pr.tagline,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = WarmOrange
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = pr.description,
                            fontSize = 12.sp,
                            color = TextLight,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }
}

// Theory concepts list view with beautiful analogies
@Composable
fun TheoryConceptsListTab() {
    val concepts = remember { NanoData.nanoConcepts }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("theory_concepts_list"),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(concepts) { con ->
            Card(
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("concept_card_${con.title.hashCode()}"),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(PaleOrange, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = "Konu",
                                tint = WarmOrange,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = con.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = TextDark
                            )
                            Text(
                                text = con.subtitle,
                                fontSize = 11.sp,
                                color = TextLight,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = con.definition,
                        fontSize = 13.sp,
                        color = TextDark,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Analogy highlighting Card
                    Card(
                        colors = CardDefaults.cardColors(containerColor = SoftSand),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = "Benzetim",
                                    tint = WarmGreen,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Grafiksel / Günlük Hayat Benzetimi:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = DarkGreen
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = con.realLifeExample,
                                fontSize = 12.sp,
                                color = TextDark,
                                lineHeight = 15.sp,
                                style = androidx.compose.ui.text.TextStyle(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                            )
                        }
                    }
                }
            }
        }
    }
}
