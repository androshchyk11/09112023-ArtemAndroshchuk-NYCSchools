package com.example.a20230911_artemandroschuk_nycshools.usecases

import com.example.a20230911_artemandroschuk_nycshools.data.repositories.getAllSchools.DefaultGetSchoolsRepository
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.result.BaseResult
import com.example.a20230911_artemandroschuk_nycshools.domain.usecases.getAllSchools.DefaultGetSchoolsUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DefaultGetSchoolsUseCaseTest {
    @Mock
    lateinit var getSchoolsRepository: DefaultGetSchoolsRepository
    private lateinit var defaultGetSchoolsUseCase: DefaultGetSchoolsUseCase

    @Before
    fun setUp() {
        defaultGetSchoolsUseCase = DefaultGetSchoolsUseCase(getSchoolsRepository)
    }

    @Test
    fun `test result Success`(){
        runBlocking {
            val mockResult = listOf(mock(SchoolItemEntity::class.java))
            `when`(getSchoolsRepository.getAllSchools()).thenReturn(mockResult)
            val result = defaultGetSchoolsUseCase.getListOfSchools()
            assertEquals(result.javaClass, BaseResult.Success<List<SchoolItemEntity>>(mockResult).javaClass)
        }
    }

    @Test
    fun `test result Error`(){
        runBlocking {
            BDDMockito.given(getSchoolsRepository.getAllSchools()).willAnswer {
                throw Exception(
                    "abc msg"
                )
            }
            val result = defaultGetSchoolsUseCase.getListOfSchools()
            assertEquals(result.javaClass, BaseResult.Error(mock(Throwable::class.java)).javaClass)
        }
    }
}