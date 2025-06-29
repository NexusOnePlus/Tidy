package com.app.tidy.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.app.tidy.screens.calendar.MinimalDialog
import com.app.tidy.ui.theme.TidyTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class EventItem(
    val title: String,
    val price: Double,
    val description: String,
    val place: String,
    val date: LocalDate,
    var isDone: Boolean = false
)

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE
    val events = remember {
        mutableStateListOf(
            EventItem(
                "Evento 1",
                100.0,
                "Descripción del evento 1",
                "Lugar 1",
                LocalDate.parse("2025-05-28", formatter)
            ),
            EventItem(
                "Evento 2",
                200.0,
                "Descripción del evento 2",
                "Lugar 2",
                LocalDate.parse("2025-06-28", formatter)
            ),
            EventItem(
                "Evento 3",
                300.0,
                "Descripción del evento 3",
                "Lugar 3",
                LocalDate.parse("2025-05-29", formatter)
            ),
        )
    }
    var showDialog by remember { mutableStateOf(false) }

    val today = remember { LocalDate.now() }

    val pastEvents = events.filter { it.date.isBefore(today) && !it.isDone }


    val thisMonthEvents = remember(events, today) {
        events.filter {
            (it.date.isEqual(today) || it.date.isAfter(today)) &&
                    it.date.month == today.month &&
                    it.date.year == today.year
            !it.isDone
        }
    }
    val displayFormatter = DateTimeFormatter.ofPattern("MMM d")


    val nextEvent = remember(events, today) {
        thisMonthEvents.minByOrNull { it.date }
            ?: events.filter { it.date.isAfter(today) }
                .minByOrNull { it.date }
    }
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
                            .padding(vertical = 8.dp)
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
                                text = nextEvent?.let { "$%.2f".format(it.price) } ?: "$0.00",
                                modifier = Modifier.padding(top = 10.dp),
                                style = TextStyle(fontSize = 38.sp),
                                color = Color.Blue
                            )
                            Text(
                                text =
                                    (nextEvent?.title ?: "Agua"),
                                style = TextStyle(fontSize = 20.sp)
                            )
                            Text(
                                text = nextEvent?.date?.format(displayFormatter) ?: "",
                                color = Color.DarkGray
                            )
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
                                    text = pastEvents.size.toString(),
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
                                    text = thisMonthEvents.size.toString(),
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
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

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
                Text(text = "$${"%.2f".format(event.price)}")
                Text(text = "${event.date.format(formatter)}")
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


enum class EventType {
    NORMAL, TIMER, CUSTOM
}
