package com.quizzy.`in`.data.modal

data class Student(
    val name: String,
    val `class`: String, // `class` is a Kotlin keyword
    val availability: Availability,
    val quiz: Quiz,
    val accuracy: Accuracy
)
