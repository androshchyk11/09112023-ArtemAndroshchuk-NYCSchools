package com.example.a20230911_artemandroschuk_nycshools.repositories

import com.example.a20230911_artemandroschuk_nycshools.data.api.ApiService
import com.example.a20230911_artemandroschuk_nycshools.data.models.schoolItem.SchoolItemModel
import com.example.a20230911_artemandroschuk_nycshools.data.repositories.getAllSchools.DefaultGetSchoolsRepository
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolsList.SchoolsListMapper
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DefaultGetSchoolsRepositoryTest {
    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var schoolsListMapper: SchoolsListMapper

    private lateinit var defaultGetSchoolsRepository: DefaultGetSchoolsRepository

    @Before
    fun setUp() {
        defaultGetSchoolsRepository = DefaultGetSchoolsRepository(apiService, schoolsListMapper)
    }

    @Test
    fun `size of list is equal after mapper convertion`() {
        runBlocking {
            val returnedList = listOf(mock(SchoolItemModel::class.java))
            `when`(apiService.getAllSchools()).thenReturn(returnedList)
            `when`(schoolsListMapper.mapFrom(returnedList)).thenReturn(listOf(mock(SchoolItemEntity::class.java)))

            val result = defaultGetSchoolsRepository.getAllSchools().size

            assertEquals(1, result)
        }
    }
}