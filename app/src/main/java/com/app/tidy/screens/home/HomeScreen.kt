package com.app.tidy.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.toColorInt
import com.app.tidy.ui.theme.TidyTheme
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.text.format

data class EventItem(
    val title: String,
    val price: Double,
    val description: String,
    val place: String,
    val date: String,
)

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
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Home",
                    style = TextStyle(fontSize = 30.sp)
                )
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notification")
            }
            Text(text = "Status", style = TextStyle(fontSize = 25.sp))
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
                            .shadow(10.dp, RoundedCornerShape(30.dp))
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color("#C5E2FF".toColorInt()))
                            .border(2.dp, Color("#36A4FF".toColorInt()), RoundedCornerShape(30.dp))
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                text = "$15.00",
                                modifier = Modifier.padding(top = 10.dp),
                                style = TextStyle(fontSize = 40.sp),
                                color = Color.Blue
                            )
                            Text(
                                text = "Gas",
                                style = TextStyle(fontSize = 20.sp)
                            )
                            Text(text = "May 28", color = Color.DarkGray)
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
                                .shadow(10.dp, RoundedCornerShape(30.dp))
                                .clip(RoundedCornerShape(30.dp))
                                .background(Color("#FF8490".toColorInt()))
                                .border(
                                    2.dp,
                                    Color("#FF0000".toColorInt()),
                                    RoundedCornerShape(30.dp)
                                )
                        ) {
                            Row(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    color = Color("#EB0C0C".toColorInt()),
                                    text = "1",
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(fontSize = 40.sp),
                                    modifier = Modifier.weight(1f),
                                )
                                Column(
                                    modifier = Modifier.weight(2f),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "Pending", color = Color("#F61515".toColorInt()))
                                    Text(text = "Late", color = Color("#F61515".toColorInt()))
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(8.dp)
                                .shadow(10.dp, RoundedCornerShape(30.dp))
                                .clip(RoundedCornerShape(30.dp))
                                .background(Color("#FFF8E8".toColorInt()))
                                .border(
                                    2.dp,
                                    Color("#F9B75B".toColorInt()),
                                    RoundedCornerShape(30.dp)
                                )
                        ) {
                            Row(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    color = Color("#E3BB17".toColorInt()),
                                    text = "3",
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(fontSize = 40.sp),
                                    modifier = Modifier.weight(1f)
                                )
                                Column(
                                    modifier = Modifier.weight(2f),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "pending",
                                        color = Color("#C0A20D".toColorInt()),
                                    )
                                    Text(text = "this month", color = Color("#C0A20D".toColorInt()))
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
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        items(eventList) { event ->
            EventItemComponent(event)
        }
    }
}

@Composable
fun EventItemComponent(event: EventItem) {
    var isCheckedState by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .shadow(2.dp, RoundedCornerShape(30.dp))
            .clip(RoundedCornerShape(30.dp))
            .background(Color("#F0F0F0".toColorInt()))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = event.title,
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = event.price.toString())
                Text(text = event.date)
            }
        }
        Column(modifier = Modifier.padding(end = 12.dp)) {
            RoundedCheckboxButton(
                isChecked = isCheckedState, onCheckedChange = {
                    isCheckedState = it
                }
            )
        }
    }
}

@Composable
fun RoundedCheckboxButton(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(10.dp))

            .background(Color.LightGray)
            .clickable { onCheckedChange(!isChecked) },
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .padding(2.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.DarkGray)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    MinimalDialog(
        onAdd = { title, price, description, place, date ->

        }
    )
}


enum class EventType {
    NORMAL, TIMER, CUSTOM
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinimalDialog(
    onAdd: (title: String, price: Double, description: String, place: String, date: String) -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    var title by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0) }
    var description by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }

    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    val dataFormatter = remember { SimpleDateFormat("dd/MM/yyyy")}
    var date by remember { mutableStateOf("") }
    var selectedEventType by remember { mutableStateOf(EventType.NORMAL) }
    val eventTypes = EventType.values()


    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePickerDialog = false
                        datePickerState.selectedDateMillis?.let { millis ->
                            date = dataFormatter.format(Date(millis))
                        }
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePickerDialog = false }
                ) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

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
                    onValueChange = { },
                    label = { Text("Date") },
                    placeholder = { Text("Select Date") },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { showDatePickerDialog = true }),
                    trailingIcon = {
                        IconButton(onClick = { showDatePickerDialog = true }) {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "Select Date"
                            )
                        }
                    }
                )

                Text(text = "Type")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    eventTypes.forEach { eventType ->
                        val isSelected = eventType == selectedEventType
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.LightGray)
                                .clickable(onClick = { selectedEventType = eventType })
                                .padding(vertical = 10.dp, horizontal = 12.dp),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = eventType.name.lowercase().capitalize(),
                                color = Color.White,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
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
