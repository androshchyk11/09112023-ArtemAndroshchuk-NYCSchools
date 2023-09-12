package com.example.a20230911_artemandroschuk_nycshools.repositories

import com.example.a20230911_artemandroschuk_nycshools.data.api.ApiService
import com.example.a20230911_artemandroschuk_nycshools.data.models.schoolDetails.SchoolDetailsModel
import com.example.a20230911_artemandroschuk_nycshools.data.repositories.getSchoolDetails.DefaultGetSchoolDetailsRepository
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolDetails.SchoolDetailsModelMapper
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolsList.SchoolItemModelMapper
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DefaultGetSchoolDetailsRepositoryTest {

    @Mock
    lateinit var apiService: ApiService

    lateinit var schoolsListMapper: SchoolDetailsModelMapper

    private lateinit var defaultGetSchoolsRepository: DefaultGetSchoolDetailsRepository

    @Before
    fun setUp() {
        schoolsListMapper = SchoolDetailsModelMapper()
        defaultGetSchoolsRepository = DefaultGetSchoolDetailsRepository(apiService, schoolsListMapper)
    }

    @Test
    fun `test when list is empty`() {
        runBlocking {
            `when`(apiService.getSchoolDetails("1")).thenReturn(listOf())
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").dbn, SchoolItemModelMapper.NOT_AVAILABLE)
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").schoolName, SchoolItemModelMapper.NOT_AVAILABLE)
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").numOfSatTestTakers, SchoolItemModelMapper.NOT_AVAILABLE)
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").satWritingAvgScore, SchoolItemModelMapper.NOT_AVAILABLE)
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").satMathAvgScore, SchoolItemModelMapper.NOT_AVAILABLE)
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").satCriticalReadingAvgScore, SchoolItemModelMapper.NOT_AVAILABLE)
        }
    }

    @Test
    fun `test when list is not empty`() {
        runBlocking {
            `when`(apiService.getSchoolDetails("1")).thenReturn(
                listOf(
                    SchoolDetailsModel(
                        "1",
                        "1",
                        "1",
                        "1",
                        "1",
                        "1"
                    )
                )
            )
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").dbn, "1")
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").schoolName, "1")
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").numOfSatTestTakers, "1")
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").satWritingAvgScore, "1")
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").satMathAvgScore, "1")
            assertEquals(defaultGetSchoolsRepository.getSchoolDetails("1").satCriticalReadingAvgScore, "1")
        }
    }
}