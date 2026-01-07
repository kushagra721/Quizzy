package com.quizzy.`in`.data.modal

data class WeeklyOverview(
    val quizStreak: List<QuizStreak>,
    val overallAccuracy: OverallAccuracy,
    val performanceByTopic: List<PerformanceByTopic>
)
