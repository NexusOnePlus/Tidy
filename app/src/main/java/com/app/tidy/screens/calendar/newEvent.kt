package com.app.tidy.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.app.tidy.screens.home.EventType
import java.text.SimpleDateFormat
import java.util.Date




@Preview(showBackground = true)
@Composable
fun Preview() {
    MinimalDialog(
        onAdd = { title, price, description, place, date ->

        }
    )
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
