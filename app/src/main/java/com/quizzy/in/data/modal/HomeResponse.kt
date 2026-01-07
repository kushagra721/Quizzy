package com.quizzy.`in`.data.modal

data class HomeResponse(
    val student: Student,
    val todaySummary: TodaySummary,
    val weeklyOverview: WeeklyOverview
)