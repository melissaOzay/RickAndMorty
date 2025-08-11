package com.developer.rickandmorty.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(navController: NavController, onScreenChange: (String) -> Unit) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Episodes,
        BottomNavItem.Favorites
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.DarkGray,
        contentColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (currentRoute == item.path) Color.Green else Color.White
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (currentRoute == item.path) Color.Green else Color.White
                    )
                },
                selected = currentRoute == item.path,
                onClick = {
                    if (currentRoute != item.path) {
                        onScreenChange(item.path)
                        navController.navigate(item.path) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}