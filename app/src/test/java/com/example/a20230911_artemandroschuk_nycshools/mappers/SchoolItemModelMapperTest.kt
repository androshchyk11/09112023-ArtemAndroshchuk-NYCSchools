package com.example.a20230911_artemandroschuk_nycshools.mappers

import com.example.a20230911_artemandroschuk_nycshools.data.models.schoolItem.SchoolItemModel
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolsList.SchoolItemModelMapper
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SchoolItemModelMapperTest {

    @Test
    fun testMapFrom() {
        val schoolItemModelMapper = SchoolItemModelMapper()
        val schoolItemModel = SchoolItemModel(
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
        )
        val result = schoolItemModelMapper.mapFrom(schoolItemModel)
        assertEquals(result.dbn, "1")
        assertEquals(result.bbl, "1")
        assertEquals(result.bin, "1")
        assertEquals(result.addTlInfo, "1")
        assertEquals(result.bus, "1")
        assertEquals(result.academicOpportunities1, "1")
        assertEquals(result.academicOpportunities2, "1")
        assertEquals(result.academicOpportunities3, "1")
        assertEquals(result.admissionsPriority11, "1")
        assertEquals(result.admissionsPriority21, "1")
        assertEquals(result.admissionsPriority31, "1")
        assertEquals(result.attendanceRate, "1")
        assertEquals(result.boro, "1")
        assertEquals(result.borough, "1")
        assertEquals(result.buildingCode, "1")
        assertEquals(result.censusTract, "1")
        assertEquals(result.councilDistrict, "1")
        assertEquals(result.city, "1")
        assertEquals(result.zip, "1")
        assertEquals(result.website, "1")
        assertEquals(result.finalGrades, "1")
        assertEquals(result.grade9SwdApplicants1, "1")
        assertEquals(result.grade9QeApplicants1, "1")
        assertEquals(result.grade9SwdFilledFlag1, "1")
        assertEquals(result.longitude, "1")
    }

    @Test
    fun testMapTo() {
        val schoolItemModelMapper = SchoolItemModelMapper()
        val schoolItemEntity = SchoolItemEntity(
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
        )
        val result = schoolItemModelMapper.mapTo(schoolItemEntity)
        assertEquals(result.dbn, "1")
        assertEquals(result.bbl, "1")
        assertEquals(result.bin, "1")
        assertEquals(result.addTlInfo, "1")
        assertEquals(result.bus, "1")
        assertEquals(result.academicOpportunities1, "1")
        assertEquals(result.academicOpportunities2, "1")
        assertEquals(result.academicOpportunities3, "1")
        assertEquals(result.admissionsPriority11, "1")
        assertEquals(result.admissionsPriority21, "1")
        assertEquals(result.admissionsPriority31, "1")
        assertEquals(result.attendanceRate, "1")
        assertEquals(result.boro, "1")
        assertEquals(result.borough, "1")
        assertEquals(result.buildingCode, "1")
        assertEquals(result.censusTract, "1")
        assertEquals(result.councilDistrict, "1")
        assertEquals(result.city, "1")
        assertEquals(result.zip, "1")
        assertEquals(result.website, "1")
        assertEquals(result.finalGrades, "1")
        assertEquals(result.grade9SwdApplicants1, "1")
        assertEquals(result.grade9QeApplicants1, "1")
        assertEquals(result.grade9SwdFilledFlag1, "1")
        assertEquals(result.longitude, "1")
    }
}