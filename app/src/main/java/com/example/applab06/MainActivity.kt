package com.example.applab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
//dp
import androidx.compose.ui.unit.dp
//navegacion - recordar configurar dependencias y libs
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//remember - mutableStateOf
import androidx.compose.runtime.*
//sp
import androidx.compose.ui.unit.sp
import com.example.applab06.ui.theme.AppLab06Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLab06Theme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()
    var clickCount by remember { mutableStateOf(0) } // Estado del contador

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Scaffold(
                topBar = { CustomTopBar(navController = navController) },
                bottomBar = { CustomBottomBar(navController = navController) }, // Agregar la barra inferior aquí
                floatingActionButton = { CustomFAB { clickCount++ } } // Incrementar contador
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Has presionado el botón $clickCount veces")
                }
            }
        }
        composable("userProfile") {
            UserProfileScreen()
        }
        composable("testScreen") {
            TestScreen()
        }
    }
}

@Composable
fun TestScreen() {
    val message = "Hola, la navegación fue un éxito"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message, style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun UserProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "User Profile", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "User Name", style = MaterialTheme.typography.titleLarge)
        // Add more user details here
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /* TODO: Side navigation action */ }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Menu")
            }
        },
        title = { Text(text = "Sample Title") },
        actions = {
            IconButton(onClick = { /* TODO: Search action */ }) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
            }
            IconButton(onClick = { navController.navigate("userProfile") }) {
                Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "Go to User Profile")
            }
        }
    )
}

@Composable
fun CustomBottomBar(navController: NavController) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(), // Ocupa todo el ancho de la pantalla
            horizontalArrangement = Arrangement.SpaceEvenly // Distribuye los elementos de forma uniforme
        ) {
            IconButton(onClick = { navController.navigate("testScreen") }) {
                Icon(Icons.Filled.Home, contentDescription = "Home description")
            }
            IconButton(onClick = { navController.navigate("testScreen") }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorite description")
            }
            IconButton(onClick = { navController.navigate("testScreen") }) {
                Icon(Icons.Filled.Person, contentDescription = "User description")
            }
            IconButton(onClick = { navController.navigate("testScreen") }) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notification description")
            }
        }
    }
}

@Composable
fun CustomFAB(onFabClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onFabClick() }, // Acción al presionar el botón flotante
    ) {
        Text(
            fontSize = 24.sp, // Tamaño de fuente del texto del botón
            text = "+" // Texto del botón
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppLab06Theme {
        AppContent()
    }
}