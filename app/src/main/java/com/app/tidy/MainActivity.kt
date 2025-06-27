package com.app.tidy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.tidy.tabview.TabBarItem
import com.app.tidy.tabview.TabView
import com.app.tidy.ui.theme.TidyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val homeTab = TabBarItem(title = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home)
            val calendarTab = TabBarItem(title = "Calendar", selectedIcon = Icons.Filled.DateRange, unselectedIcon = Icons.Outlined.DateRange)
            val settingsTab = TabBarItem(title = "Settings", selectedIcon = Icons.Filled.Settings, unselectedIcon = Icons.Outlined.Settings)

            val tabItems = listOf(homeTab, calendarTab, settingsTab)
            val navController = rememberNavController()

            TidyTheme {
                Scaffold(bottomBar = { TabView(tabItems = tabItems, navController = navController) },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = homeTab.title, modifier = Modifier.padding(innerPadding)){
                        composable(homeTab.title){
                            Text(text = "Home")
                        }
                        composable(calendarTab.title){
                            Text(text = "Calendar")
                        }
                        composable(settingsTab.title){
                            Text(text = "Settings")
                        }
                    }
                }
            }
        }
    }
}

