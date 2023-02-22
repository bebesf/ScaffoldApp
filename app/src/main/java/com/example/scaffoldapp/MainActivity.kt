package com.example.scaffoldapp
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.scaffoldapp.ui.theme.ScaffoldAppTheme
import androidx.compose.foundation.layout.Row as Row1
import androidx.navigation.NavHost as NavHost1
import com.example.scaffoldapp.InfoScreen as ScaffoldappInfoScreen
import com.example.scaffoldapp.MainScreen as MainScreen1
import com.example.scaffoldapp.SettingsScreen as SettingsScreen1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldAppTheme {
                ScaffoldApp()
            }
        }
    }
}


@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost1(
        navController = navController,
        startDestination = "Home"
    ) {

        var route = "Home".run {
            Row1 {
                MainScreen1(navController)
            }
        }
         composable(route = "Info") {
            ScaffoldappInfoScreen(navController)
        }
        composable(route = "Settings") {
            SettingsScreen1(navController)
        }
    }
}

fun composable(route: String, function: () -> Unit): Any {
    TODO("Not yet implemented")
}

fun rememberNavController(): NavController {
    TODO("Not yet implemented")
}

@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar (
        title = { Text (title) },
        actions = {
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(Icons.Filled.MoreVert,contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded ,
                onDismissRequest = { expanded= false}) {
                DropdownMenuItem(onClick = { navController.navigate("info") }) {
                    Text("Info")
                }
                DropdownMenuItem(onClick = { navController.navigate("settings")}) {
                    Text("Settings")
                }
            }
        }
    )
}

@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen (navController: NavController) {
    Scaffold (
        topBar = { MainTopBar( "My App", navController  )},
        content = { Text(text = "Content for Home screen")},
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoScreen (navController: NavController) {
    Scaffold (
        topBar = { ScreenTopBar( "Info", navController  )},
        content = { Text(text = "Content for Info screen")},
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen ( navController: NavController) {
    Scaffold (
        topBar = { ScreenTopBar( "Settings" , navController )},
        content = { Text(text = "Content for Settings screen")}
    )
}


