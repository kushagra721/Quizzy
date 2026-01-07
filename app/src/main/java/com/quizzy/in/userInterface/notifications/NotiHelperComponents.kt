package com.quizzy.`in`.userInterface.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quizzy.`in`.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsSettingsScreen(

    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {

            Row(Modifier.fillMaxWidth().height(70.dp), verticalAlignment = Alignment.CenterVertically ){
                IconButton(onClick = {onBackClick()}) {
                    Icon(  painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24), contentDescription = null)
                }
                Spacer(Modifier.weight(1f))

                Text("Notifications & Settings", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

                Spacer(Modifier.weight(1.3f))


            }

        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            item {
                Text(
                    text = "Notifications",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            // notifications list
            item {
                NotificationItem(
                    title = "Missed quiz in physics in yesterday",
                    time = "2 hours ago",
                    accentColor = Color(0xFFfff9f4),
                    barColor = Color(0xFFffb370)
                )
            }
            item {
                NotificationItem(
                    title = "Badge earned",
                    time = "8 hours ago",
                    accentColor = Color(0xFFfaf4ff),
                    barColor = Color(0xFF996eb5)
                )
            }
            item {
                NotificationItem(
                    title = "Teacher Note",
                    time = "1 day ago",
                    accentColor = Color(0xFFf2fff4),
                    barColor = Color(0xFF22c55d)
                )
            }

            item {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Settings",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            // settings rows
            item {
                SettingsRow(
                    icon = R.drawable.switch_,
                    title = "Switch Child",
                    subtitle = "Change active child profile"
                )
            }
            item {
                SettingsRow(
                    icon = R.drawable.lang,
                    title = "Language",
                    subtitle = "English"
                )
            }
            item {
                SettingsRow(
                    icon = R.drawable.logout,
                    title = "Logout",
                    subtitle = "Sign out of your account",
                    iconTint = Color(0xFFff6776),
                    textColor = Color(0xFF000000)
                )
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}


@Composable
fun NotificationItem(
    title: String,
    time: String,
    accentColor: Color,
    barColor : Color
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = accentColor
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){


            Box(
                modifier = Modifier
                    .width(8.dp)
                    .height(60.dp)
                    .background(color = barColor)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

    }
}


@Composable
fun SettingsRow(
    icon: Int,
    title: String,
    subtitle: String,
    iconTint: Color = Color.Black,
    textColor: Color = Color.Black
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(22.dp)
        )

        Spacer(Modifier.width(16.dp))

        Column {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = textColor
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}