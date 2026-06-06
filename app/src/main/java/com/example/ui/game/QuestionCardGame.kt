package com.example.ui.game

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ChemistryScenario
import com.example.data.NanoData
import com.example.ui.theme.*

@Composable
fun QuestionCardGameScreen() {
    // Shuffled pool of scenarios
    val scenarios = remember { NanoData.chemistryScenarios.shuffled() }

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var streak by remember { mutableStateOf(0) }
    var bestStreak by remember { mutableStateOf(0) }

    // Null means unanswered, otherwise holds the user's boolean choice
    var userChoice by remember { mutableStateOf<Boolean?>(null) }
    var showResultsSummary by remember { mutableStateOf(false) }

    val currentScenario = if (currentIndex < scenarios.size) scenarios[currentIndex] else null

    fun handleAnswer(choice: Boolean) {
        if (userChoice != null) return // Already answered this card
        userChoice = choice

        val isCorrect = (choice == currentScenario?.isGreen)
        if (isCorrect) {
            score += 10
            streak += 1
            if (streak > bestStreak) {
                bestStreak = streak
            }
        } else {
            streak = 0
        }
    }

    fun handleNext() {
        userChoice = null
        if (currentIndex + 1 < scenarios.size) {
            currentIndex++
        } else {
            showResultsSummary = true
        }
    }

    fun handleRestart() {
        currentIndex = 0
        score = 0
        streak = 0
        userChoice = null
        showResultsSummary = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NaturalCream)
            .padding(16.dp)
            .testTag("question_game_parent"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Score bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Doğru mu Yanlış mı?",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = DarkGreen
                )
                Text(
                    text = "Süreç Analizi ve Yeşil Kimya Testi",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextLight
                )
            }

            // Small score card
            Card(
                colors = CardDefaults.cardColors(containerColor = LightGreen),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = "Puan",
                        tint = DarkGreen,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$score Puan",
                        color = DarkGreen,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (showResultsSummary) {
            // End screen scorecard
            ResultsSummaryCard(
                totalQuestions = scenarios.size,
                correctCount = score / 10,
                maxStreak = bestStreak,
                totalScore = score,
                onRestart = { handleRestart() }
            )
        } else if (currentScenario != null) {
            // Interactive progress indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    progress = { (currentIndex + 1).toFloat() / scenarios.size },
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = WarmGreen,
                    trackColor = WarmSlate
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "${currentIndex + 1} / ${scenarios.size}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = TextDark
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Streak indicator
            if (streak > 0) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = PaleOrange.copy(alpha = 0.9f)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = "Seri",
                            tint = Color(0xFFD84315),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$streak Soru Seri İsabet 🔥",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF5D1F00)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            } else {
                Spacer(modifier = Modifier.height(4.dp))
            }

            // PRIMARY FLASHCARD
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .testTag("chemical_process_card"),
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = currentScenario.title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = WarmGreen,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Icon(
                        imageVector = Icons.Default.Biotech,
                        contentDescription = "Laboratuvar",
                        tint = WarmGreen.copy(alpha = 0.8f),
                        modifier = Modifier.size(44.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Chemical scenario text
                    Text(
                        text = currentScenario.paragraph,
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextDark,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    HorizontalDivider(color = WarmSlate)

                    Spacer(modifier = Modifier.height(15.dp))

                    // Feed back answers
                    if (userChoice == null) {
                        // User needs to choose True/False
                        Text(
                            text = "Bu işlem Yeşil Kimya prensiplerine uygun mu?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = TextLight,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = { handleAnswer(true) },
                                modifier = Modifier
                                    .weight(1f)
                                        .height(52.dp)
                                    .testTag("answer_true_button"),
                                colors = ButtonDefaults.buttonColors(containerColor = WarmGreen),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Check, contentDescription = "Doğru")
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("DOĞRU", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            }

                            Button(
                                onClick = { handleAnswer(false) },
                                modifier = Modifier
                                    .weight(1f)
                                        .height(52.dp)
                                    .testTag("answer_false_button"),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = "Yanlış")
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("YANLIŞ", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            }
                        }
                    } else {
                        // Display feedback analysis report
                        val isCorrect = (userChoice == currentScenario.isGreen)

                        val feedbackBannerColor = if (isCorrect) LightGreen else Color(0xFFFFCDD2)
                        val feedbackTextColor = if (isCorrect) DarkGreen else Color(0xFFB71C1C)
                        val feedbackSymbol = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel

                        Card(
                            colors = CardDefaults.cardColors(containerColor = feedbackBannerColor),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = feedbackSymbol,
                                    contentDescription = "Geri bildirim",
                                    tint = feedbackTextColor,
                                    modifier = Modifier.size(28.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isCorrect) "Tebrikler! Doğru Teşhis" else "Maalesef! Yanlış Değerlendirme",
                                    color = feedbackTextColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Scientific deep description in Turkish
                        Card(
                            colors = CardDefaults.cardColors(containerColor = SoftSand),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = "Bilimsel Süreç Analizi:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = TextDark
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = currentScenario.explanation,
                                    fontSize = 13.sp,
                                    color = TextDark,
                                    lineHeight = 18.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Next question trigger button
                        Button(
                            onClick = { handleNext() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("next_question_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = WarmGreen),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = if (currentIndex + 1 < scenarios.size) "Sıradaki Vakayı İncele" else "Sonuçları Göster",
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "İleri")
                        }
                    }
                }
            }
        }
    }
}

// Result summary screen view
@Composable
fun ResultsSummaryCard(
    totalQuestions: Int,
    correctCount: Int,
    maxStreak: Int,
    totalScore: Int,
    onRestart: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .testTag("results_summary_card"),
        colors = CardDefaults.cardColors(containerColor = SoftWhite),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(LightGreen, RoundedCornerShape(36.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = "Diploma",
                    tint = DarkGreen,
                    modifier = Modifier.size(44.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tebrikler! Test Tamamlandı",
                fontWeight = FontWeight.Black,
                fontSize = 20.sp,
                color = DarkGreen,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Yeşil Kimya ve Sürdürülebilirlik Sertifikası",
                fontSize = 12.sp,
                color = TextLight,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Performance Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = SoftSand)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Doğru", fontSize = 11.sp, color = TextLight)
                        Text("$correctCount / $totalQuestions", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextDark)
                    }
                }

                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = SoftSand)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Maks Seri", fontSize = 11.sp, color = TextLight)
                        Text("$maxStreak 🔥", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextDark)
                    }
                }

                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = SoftSand)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Puan", fontSize = 11.sp, color = TextLight)
                        Text("$totalScore", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = WarmOrange)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Score explanation in Turkish
            val successRate = (correctCount.toFloat() / totalQuestions) * 100
            val rankTitle = when {
                successRate >= 90f -> "Yeşil Kimya Başmühendisi 🌿"
                successRate >= 70f -> "Nanoteknoloji Araştırmacısı 🔬"
                successRate >= 50f -> "Kimya Sınıfı Dehası 🎓"
                else -> "Gelişmekte Olan Yeşil Kimyager 🧪"
            }

            Text(
                text = "Unvanınız:",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = TextLight
            )
            Text(
                text = rankTitle,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                color = WarmGreen,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Seçimleriniz ve analizleriniz, yeşil kimya ve sürdürülebilirlik konularındaki farkındalığınızı ortaya koydu. Laboratuvar ortamlarında kirliliği kaynağında çözmeyi başardınız!",
                fontSize = 13.sp,
                color = TextDark,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onRestart() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("restart_game_button"),
                colors = ButtonDefaults.buttonColors(containerColor = WarmGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(imageVector = Icons.Default.RestartAlt, contentDescription = "Yeni Test")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Yeni Teste Başla", fontWeight = FontWeight.Bold)
            }
        }
    }
}
