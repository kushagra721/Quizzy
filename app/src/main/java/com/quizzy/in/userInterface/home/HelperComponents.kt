package com.quizzy.`in`.userInterface.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quizzy.`in`.R

@Composable
fun QuizzyHomeScreen(
    state: HomeUiState,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFFffffff)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(Modifier.height(5.dp)) }

            // Header
            item {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                    Column {
                        Text(
                            text = "Hello ${state?.response?.student?.name}!",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.Black
                        )
                        state?.response?.student?.`class`?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }

                    Spacer(Modifier.weight(1f))

                    IconButton(onClick = { onNotificationClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.noti),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.padding(start = 15.dp).size(24.dp)

                        )
                    }

                }

            }

            // Status + bell
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    StatusChip(
                        title = "Availability",
                        subtitle = state?.response?.student?.availability?.status ?: "",
                        borderColor = Color(0xFF59d486),
                        bgColor = Color(0xFFf2fff4),
                        icon = R.drawable.group_
                    )
                    StatusChip(
                        title = "Quiz",
                        subtitle = "${state?.response?.student?.quiz?.attempts ?: ""} Attempt",
                        borderColor = Color(0xFFfeb56c),
                        bgColor = Color(0xFFfffcf9),
                        icon =R.drawable.quiz
                    )
                    StatusChip(
                        title = "Accuracy",
                        subtitle = state?.response?.student?.accuracy?.current ?: "",
                        borderColor = Color(0xFFff7b7b),
                        bgColor = Color(0xFFfff6f6),
                        icon = R.drawable.accuracy
                    )



                }
            }

            // Today's summary card
            item {
                Text(
                    text = "Today Summary",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000),
                    fontSize = 16.sp
                )

                SummaryCard(
                    title = state?.response?.todaySummary?.mood ?: "",
                    message = "“${ state?.response?.todaySummary?.description ?: ""}”",
                    buttonText =  state?.response?.todaySummary?.recommendedVideo?.actionText ?: ""
                )
            }

            // Weekly overview card (full block: streak + accuracy + topic performance)
            item {
                Text(
                    text = "Weekly Overview",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000),
                    fontSize = 16.sp
                )
                WeeklyOverviewCard(state)
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun StatusChip(
    title: String,
    subtitle: String,
    borderColor: Color,
    bgColor: Color,
    icon: Int
) {
    Surface(
        modifier = Modifier
            .width( 100.dp)
            .heightIn(min = 72.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, borderColor),
        color = bgColor
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = borderColor,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun SummaryCard(
    title: String,
    message: String,
    buttonText: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(top=10.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        tonalElevation = 1.dp,
        //   shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color = Color(0xFFa57fbe)),
    ) {
        Column(
            modifier = Modifier.background(color = Color(0xFFfcf7ff))
                .padding(horizontal = 18.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // top illustration
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // replace with your image
                /* Image( painterResource(R.drawable.summary_elephant), null ) */
                Image(
                    painter = painterResource(R.drawable.focus),
                    contentDescription = "",
                    // tint = Color(0xFF7C4DFF),
                    modifier = Modifier.size(62.dp)
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF996eb5),
                fontSize = 20.sp
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { /* watch */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text(buttonText, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun WeeklyOverviewCard(state: HomeUiState) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(top=10.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        tonalElevation = 1.dp,
        border = BorderStroke(1.dp, color = Color(0xFF7b7f86)),
    ) {
        Column(
            modifier = Modifier.background(color = Color.White)
                .padding(horizontal = 18.dp, vertical = 16.dp)
        ) {


            Spacer(Modifier.height(12.dp))

            // Quiz streak row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {

                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                        Text("Quiz Streak", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        Spacer(Modifier.weight(1f))
                        Image(
                            painter = painterResource(R.drawable.group),
                            contentDescription = "",
                            modifier = Modifier.size(35.dp)

                        )

                    }

                    Spacer(Modifier.height(10.dp))
                    Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                        state?.response?.weeklyOverview?.quizStreak?.forEach { quizStreak ->

                            if (quizStreak.status == "done") {

                                Image(
                                    painter = painterResource(R.drawable.check),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )

                            } else {

                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(
                                            color = Color(0xFFE0E0E0), // pending bg
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = quizStreak.day, // or "P"
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.DarkGray
                                    )
                                }
                            }




                        }


                    }
                }

            }

            Spacer(Modifier.height(16.dp))
            Divider()

            // Accuracy row
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                        Text("Accuracy", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        Spacer(Modifier.weight(1f))
                        Image(
                            painter = painterResource(R.drawable.target),
                            contentDescription = "",
                            modifier = Modifier.size(36.dp))

                    }

                    Text(state?.response?.weeklyOverview?.overallAccuracy?.label ?: "", color = Color.Black,fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                }

            }

            Spacer(Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = state?.response?.weeklyOverview?.overallAccuracy?.percentage?.toFloat() ?: 0f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = Color(0xFFFF6B6B),
                trackColor = Color(0xFFFFCDD2)
            )

            Spacer(Modifier.height(16.dp))
            Divider()

            // Performance by topic
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Performance by Topic", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Image(
                    painter = painterResource(R.drawable.bars),
                    contentDescription = "",
                    modifier = Modifier.size(34.dp))
            }

            Spacer(Modifier.height(12.dp))

            state?.response?.weeklyOverview?.performanceByTopic?.forEach { topic ->
                TopicRow("${topic?.topic}",  if(topic?.trend == "up") R.drawable.outline_arrow_circle_up_24 else  R.drawable.outline_arrow_circle_down_24 , if(topic?.trend == "up") Color(0xFF00B37A) else Color(0xFFff6776) )

            }



            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { /* more details */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("More Details")
            }
        }
    }
}

@Composable
fun TopicRow(
    title: String,
    icon: Int,
    iconColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = iconColor
        )
    }
}