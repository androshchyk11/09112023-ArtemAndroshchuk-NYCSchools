package com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolDetails

import com.example.a20230911_artemandroschuk_nycshools.data.models.schoolDetails.SchoolDetailsModel
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolDetails.SchoolDetailsEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.Mapper
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolsList.SchoolItemModelMapper.Companion.NOT_AVAILABLE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolDetailsModelMapper @Inject constructor() : Mapper<SchoolDetailsModel, SchoolDetailsEntity> {
    override fun mapFrom(model: SchoolDetailsModel): SchoolDetailsEntity = with(model) {
        SchoolDetailsEntity(
            dbn = dbn ?: NOT_AVAILABLE,
            numOfSatTestTakers = numOfSatTestTakers ?: NOT_AVAILABLE,
            satCriticalReadingAvgScore = satCriticalReadingAvgScore ?: NOT_AVAILABLE,
            satMathAvgScore = satMathAvgScore ?: NOT_AVAILABLE,
            satWritingAvgScore = satWritingAvgScore ?: NOT_AVAILABLE,
            schoolName = schoolName ?: NOT_AVAILABLE
        )
    }

    override fun mapTo(entity: SchoolDetailsEntity): SchoolDetailsModel = with(entity) {
        SchoolDetailsModel(
            dbn = dbn,
            numOfSatTestTakers = numOfSatTestTakers,
            satCriticalReadingAvgScore = satCriticalReadingAvgScore,
            satMathAvgScore = satMathAvgScore,
            satWritingAvgScore = satWritingAvgScore,
            schoolName = schoolName
        )
    }
}