package com.example.foodie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
              Foodie()
            }
        }
    }
}

@Composable
fun Foodie(viewModel: FoodViewModel=viewModel()) {
    val foodList = viewModel.foodList

    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priceText by remember { mutableStateOf("") }

    Surface (modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.padding(16.dp)){
            Text("Food Menu", style = MaterialTheme.typography.headlineMedium)

            TextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("ID") },
                modifier = Modifier.padding(vertical = 16.dp)
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.padding(vertical = 16.dp)
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.padding(vertical = 16.dp)
            )

            TextField(
                value = priceText,
                onValueChange = { priceText = it },
                label = { Text("Price") },
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Button(
                onClick = {
                    var id = id.toIntOrNull() ?: 0
                    val price = priceText.toDoubleOrNull() ?: 0.0
                    viewModel.addFood(
                        FoodItem(
                            id = id,
                            name = name,
                            description = description,
                            price = price
                        )
                    )

                    name = ""
                    description = ""
                    priceText = ""
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                androidx.compose.material3.Text("Add Food")
            }

            // Food List
            LazyColumn {
                items(foodList) { food ->
                    Card(modifier = Modifier.padding(8.dp)) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text("ID: ${food.id}")
                            Text("Name: ${food.name}")
                            Text("Description: ${food.description}")
                            Text("Price: ${food.price}")

                            Row {
                                Button(onClick = { viewModel.deleteFood(food.id) }) {
                                    Text("Delete")
                                }
                                Button(onClick = {
                                    viewModel.updateFood(
                                        food.copy(
                                            name = "Updated",
                                            description = "Updated description"
                                        )
                                    )
                                }) {
                                    Text("Update")
                                }
                            }
                        }
                    }
                }
            }
        }



        }
    }


