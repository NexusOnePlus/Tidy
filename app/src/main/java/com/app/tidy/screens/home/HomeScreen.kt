package com.app.tidy.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.app.tidy.ui.theme.TidyTheme

data class EventItem(
    val title: String,
    val price: Double,
    val description: String,
    val place: String,
    val date: String,
)

// @Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen() {
    val events = remember {
        mutableStateListOf<EventItem>(
            EventItem("Evento 1", 100.0, "Descripción del evento 1", "Lugar 1", "Fecha"),
            EventItem("Evento 2", 200.0, "Descripción del evento 2", "Lugar 2", "Fecha"),
            EventItem("Evento 3", 300.0, "Descripción del evento 3", "Lugar 3", "Fecha"),
        )
    }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                }
            ) { Icon(imageVector = Icons.Default.Add, contentDescription = "Add") }
        }
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Home",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                )
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notification")
            }
            Text(text = "Status", style = TextStyle(fontSize = 20.sp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Row {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.LightGray)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = "$15.00", style = TextStyle(fontSize = 30.sp))
                            Text(
                                text = "Gas",
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            )
                            Text(text = "May 28")
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.LightGray)

                        ) {
                            Row(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = "1",
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(fontSize = 30.sp),
                                    modifier = Modifier.weight(1f)
                                )
                                Column(modifier = Modifier.weight(2f)) {
                                    Text(text = "Pending")
                                    Text(text = "Late")
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.LightGray)
                        ) {
                            Row(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = "3",
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(fontSize = 30.sp),
                                    modifier = Modifier.weight(1f)
                                )
                                Column(modifier = Modifier.weight(2f)) {
                                    Text(text = "pending")
                                    Text(text = "this month")
                                }

                            }
                        }
                    }
                }
            }

            Text(text = "Next", style = TextStyle(fontSize = 20.sp))
            Box(modifier = Modifier.fillMaxWidth()) {
                EventItemList(events, modifier = Modifier.fillMaxWidth())
            }
        }
        if (showDialog) {
            MinimalDialog(onAdd = { title, price, description, place, date ->
                events.add(EventItem(title, price, description, place, date))
                showDialog = false
            }, onDismissRequest = { showDialog = false })
        }
    }
}

@Composable
fun EventItemList(eventList: List<EventItem>, modifier: Modifier) {
    LazyColumn(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(8.dp))
    {
        items(eventList) { event ->
            EventItemComponent(event)
        }
    }
}

@Composable
fun EventItemComponent(event: EventItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            // .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Gray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = event.title)
            Text(text = event.description)
            Text(text = event.date)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    MinimalDialog(
        onAdd = {title, price, description, place, date ->

        }
    )
}


@Composable
fun MinimalDialog(
    onAdd: (title: String, price: Double, description: String, place: String, date: String) -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    var title by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0) }
    var description by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = (RoundedCornerShape(12.dp)),
        )
        {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                Text(
                    text = "New Event", textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = price.toString(),
                    onValueChange = { price = it.toDouble().toInt() },
                    label = { Text("Price") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = place,
                    onValueChange = { place = it },
                    label = { Text("Place") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Date") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){
                    TextButton(onClick = onDismissRequest ) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = {
                        onAdd(title, price.toDouble(), description, place, date)
                    }) {
                        Text(text = "Add")
                    }
                }

            }

        }
    }
}
