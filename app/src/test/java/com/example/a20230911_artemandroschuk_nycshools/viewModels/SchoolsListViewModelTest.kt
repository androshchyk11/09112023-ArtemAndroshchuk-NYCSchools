package com.example.a20230911_artemandroschuk_nycshools.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.networkManager.ConnectionManager
import com.example.a20230911_artemandroschuk_nycshools.domain.result.BaseResult
import com.example.a20230911_artemandroschuk_nycshools.domain.usecases.getAllSchools.DefaultGetSchoolsUseCase
import com.example.a20230911_artemandroschuk_nycshools.presentation.schoolList.SchoolsListViewModel
import com.example.a20230911_artemandroschuk_nycshools.presentation.viewState.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SchoolsListViewModelTest {

    @Mock
    lateinit var getSchoolsUseCase: DefaultGetSchoolsUseCase

    @Mock
    lateinit var connectionManager: ConnectionManager

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    lateinit var schoolsListViewModel: SchoolsListViewModel
    private lateinit var viewStates: MutableList<ViewState<*>>

    @Before
    fun setUp() {
        `when`(connectionManager.isAvailableConnection()).thenReturn(true)
        Dispatchers.setMain(dispatcher)
        viewStates = mutableListOf()
        schoolsListViewModel = SchoolsListViewModel(getSchoolsUseCase, connectionManager)
        schoolsListViewModel.schoolsListLiveData.observeForever {
            viewStates.add(it)
        }
    }

    @Test
    fun `test no internet connection`() {
        `when`(connectionManager.isAvailableConnection()).thenReturn(false)
        schoolsListViewModel.getSchools()

        assert(viewStates[0] is ViewState.Error)
    }

    @Test
    fun `test Success`() {
        runBlocking {
            val successResult = listOf(mock(SchoolItemEntity::class.java))
            `when`(connectionManager.isAvailableConnection()).thenReturn(true)
            `when`(getSchoolsUseCase.getListOfSchools()).thenReturn(BaseResult.Success(successResult))

            schoolsListViewModel.getSchools()

            assert(viewStates[0] is ViewState.Loading)
            assert(viewStates[1] is ViewState.Success)
        }
    }

    @Test
    fun `test Error`() {
        runBlocking {
            `when`(connectionManager.isAvailableConnection()).thenReturn(true)
            `when`(getSchoolsUseCase.getListOfSchools()).thenReturn(BaseResult.Error(Throwable()))

            schoolsListViewModel.getSchools()

            assert(viewStates[0] is ViewState.Loading)
            assert(viewStates[1] is ViewState.Error)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}