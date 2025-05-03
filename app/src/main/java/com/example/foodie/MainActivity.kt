package com.example.foodie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    Surface(modifier = Modifier.fillMaxSize())
    {

       Column(modifier = Modifier.padding(25.dp)) {
        Text("Foodie")

           // Add Button
           Button(
               onClick = {
                   viewModel.addFood(
                       FoodItem(
                           id = 0,
                           name = "New Dish",
                           price = 10.0,
                           description = "Yummy!"
                       )
                   )
               },
               modifier = Modifier.padding(vertical = 16.dp)
           ) {
               Text("Add Food")
           }

           //food list
           LazyColumn {
                items(foodList) { food ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)

                    )
                    {
                        Column {
                            Text(food.name)
                            Text(food.description)


                            Row {
                                Button(
                                    onClick = { viewModel.deleteFood(food.id) },
                                ) {
                                    Text("Delete")
                                 }

                                Button(
                                    onClick = {
                                        viewModel.updateFood(
                                            food.copy(
                                                name = "Updated",
                                                description = "Updated description"
                                            )
                                        )
                                    }
                                ) {
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

