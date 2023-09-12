package com.example.a20230911_artemandroschuk_nycshools.mappers

import com.example.a20230911_artemandroschuk_nycshools.data.models.schoolDetails.SchoolDetailsModel
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolDetails.SchoolDetailsEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolDetails.SchoolDetailsModelMapper
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SchoolDetailsModelMapperTest {

    @Test
    fun testMapFrom() {
        val schoolDetailsModelMapper = SchoolDetailsModelMapper()
        val schoolDetailsModel = SchoolDetailsModel(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6"
        )
        val result = schoolDetailsModelMapper.mapFrom(schoolDetailsModel)
        assertEquals(result.dbn,"1")
        assertEquals(result.numOfSatTestTakers,"2")
        assertEquals(result.satCriticalReadingAvgScore,"3")
        assertEquals(result.satMathAvgScore,"4")
        assertEquals(result.satWritingAvgScore,"5")
        assertEquals(result.schoolName,"6")
    }

    @Test
    fun testMapTo() {
        val schoolDetailsModelMapper = SchoolDetailsModelMapper()
        val schoolDetailsEntity = SchoolDetailsEntity(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6"
        )
        val result = schoolDetailsModelMapper.mapTo(schoolDetailsEntity)
        assertEquals(result.dbn,"1")
        assertEquals(result.numOfSatTestTakers,"2")
        assertEquals(result.satCriticalReadingAvgScore,"3")
        assertEquals(result.satMathAvgScore,"4")
        assertEquals(result.satWritingAvgScore,"5")
        assertEquals(result.schoolName,"6")
    }
}