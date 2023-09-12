package com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolDetails

data class SchoolDetailsEntity(
    val dbn: String,
    val numOfSatTestTakers: String,
    val satCriticalReadingAvgScore: String,
    val satMathAvgScore: String,
    val satWritingAvgScore: String,
    val schoolName: String
)