package com.benhurqs.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(carList: List<String>) {
    LazyColumn(
        Modifier
            .background(color = Color.White)
            .padding(horizontal = 16.dp)
    ) {
        items(carList) { car ->

            CarCardsComponent(car)
        }
    }
}

@Composable
fun CarCardsComponent(name: String){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Text(
                text = name,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(text = name)
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    MainScreen(mutableListOf("Benhur", "Alcione", "Joseph"))
}
