package com.example.a20230911_artemandroschuk_nycshools.usecases

import com.example.a20230911_artemandroschuk_nycshools.data.repositories.getSchoolDetails.DefaultGetSchoolDetailsRepository
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolDetails.SchoolDetailsEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.result.BaseResult
import com.example.a20230911_artemandroschuk_nycshools.domain.usecases.getSchoolDetails.DefaultGetSchoolDetailsUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DefaultGetSchoolDetailsUseCaseTest {
    @Mock
    lateinit var getSchoolDetailsRepository: DefaultGetSchoolDetailsRepository
    lateinit var defaultGetSchoolDetailsUseCase: DefaultGetSchoolDetailsUseCase

    @Before
    fun setUp() {
        defaultGetSchoolDetailsUseCase = DefaultGetSchoolDetailsUseCase(getSchoolDetailsRepository)
    }

    @Test
    fun `test result Success`(){
        runBlocking {
            val mockResult = mock(SchoolDetailsEntity::class.java)
            `when`(getSchoolDetailsRepository.getSchoolDetails("1")).thenReturn(mockResult)
            val result = defaultGetSchoolDetailsUseCase.getSchoolDetails("1")
            assertEquals(result.javaClass, BaseResult.Success<SchoolDetailsEntity>(mockResult).javaClass)
        }
    }

    @Test
    fun `test result Error`() {
        runBlocking {
            given(getSchoolDetailsRepository.getSchoolDetails("1")).willAnswer {
                throw Exception(
                    "abc msg"
                )
            }

            val result = defaultGetSchoolDetailsUseCase.getSchoolDetails("1")
            assertEquals(result.javaClass, BaseResult.Error(mock(Throwable::class.java)).javaClass)
        }
    }
}