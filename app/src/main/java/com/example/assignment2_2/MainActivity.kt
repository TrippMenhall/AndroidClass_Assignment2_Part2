package com.example.assignment2_2

import CountryList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2_2.ui.theme.Assignment2_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2_2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    CountryLister()
                }
            }
        }
    }
}

@Composable
fun CountryLister () {
    LazyColumn {
        items(CountryList) { country ->
            CountryPrinter(country.name, country.flag, country.currency)
        }
    }
    Spacer(Modifier.height(700.dp))
}

@Composable
fun CountryPrinter(name: String, flag: Int, currency: String) {
    HorizontalDivider(thickness = 1.dp)
    Row (verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(flag), contentDescription = null, contentScale = FillWidth, modifier = Modifier.width(50.dp))
        Column (Modifier.padding(start = 8.dp)){
            Text(text = "Country: $name", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(text = "Currency: $currency", fontSize = 8.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment2_2Theme {
        CountryLister()
    }
}