package com.example.ui.main

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.game.NumberlinkGameScreen
import com.example.ui.game.QuestionCardGameScreen
import com.example.ui.info.InfoSectionScreen
import com.example.ui.theme.*

// Main View Tabs list
enum class MainTab(
    val title: String,
    val icon: ImageVector,
    val tag: String
) {
    LIBRARY("Kütüphane", Icons.Default.Book, "tab_library"),
    NUMBERLINK("Bağlantı Kur", Icons.Default.Hub, "tab_numberlink"),
    FLASHCARDS("Yeşil Sınav", Icons.Default.FactCheck, "tab_flashcards")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var activeTab by remember { mutableStateOf(MainTab.LIBRARY) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("app_scaffold"),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Biotech,
                            contentDescription = "Bilim",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "NanoKimya Akademisi",
                                fontWeight = FontWeight.Black,
                                fontSize = 17.sp,
                                color = Color.White
                            )
                            Text(
                                text = "Nanoteknoloji ve Yeşil Kimya Eğitim Aracı",
                                fontSize = 10.sp,
                                color = LightGreen,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = WarmGreen
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = SoftWhite,
                tonalElevation = 8.dp,
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                MainTab.values().forEach { tab ->
                    val isSelected = activeTab == tab
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { activeTab = tab },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.title,
                                tint = if (isSelected) WarmGreen else TextLight
                            )
                        },
                        label = {
                            Text(
                                text = tab.title,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 12.sp,
                                color = if (isSelected) DarkGreen else TextLight
                            )
                        },
                        modifier = Modifier.testTag(tab.tag),
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = LightGreen
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(NaturalCream)
        ) {
            // Animated visibility swapping screens smoothly
            Box(modifier = Modifier.weight(1f)) {
                when (activeTab) {
                    MainTab.LIBRARY -> {
                        InfoSectionScreen()
                    }
                    MainTab.NUMBERLINK -> {
                        NumberlinkGameScreen()
                    }
                    MainTab.FLASHCARDS -> {
                        QuestionCardGameScreen()
                    }
                }
            }
        }
    }
}
