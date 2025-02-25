@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.assignment2_2

import Country
import CountryList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment2_2.ui.theme.Assignment2_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2_2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "countryList") {
        composable("countryList") {
            CountryLister(navController)
        }
        composable("countryDetail/{countryName}") { backStackEntry ->
            val countryName = backStackEntry.arguments?.getString("countryName")
            val country = CountryList.find { it.name == countryName }
            country?.let {
                CountryDetailScreen(navController, it)
            }
        }
    }
}

@Composable
fun CountryLister(navController: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Countries") }) }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(CountryList) { country ->
                CountryRow(country, navController)
            }
        }
    }
}

@Composable
fun CountryRow(country: Country, navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("countryDetail/${country.name}") }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(country.flag),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(50.dp)
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = "Country: ${country.name}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Currency: ${country.currency}",
                fontSize = 12.sp
            )
        }
    }
    Divider()
}

@Composable
fun CountryDetailScreen(navController: NavController, country: Country) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(country.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Sharp.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(country.map),
                contentDescription = "Map of ${country.name}",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = country.fact,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
