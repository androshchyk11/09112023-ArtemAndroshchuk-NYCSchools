package com.example.a20230911_artemandroschuk_nycshools.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolDetails.SchoolDetailsEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.networkManager.ConnectionManager
import com.example.a20230911_artemandroschuk_nycshools.domain.result.BaseResult
import com.example.a20230911_artemandroschuk_nycshools.domain.usecases.getSchoolDetails.DefaultGetSchoolDetailsUseCase
import com.example.a20230911_artemandroschuk_nycshools.presentation.schoolDetails.SchoolDetailsViewModel
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
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SchoolDetailsViewModelTest {

    @Mock
    lateinit var getSchoolsDetailsUseCase: DefaultGetSchoolDetailsUseCase

    @Mock
    lateinit var connectionManager: ConnectionManager

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    lateinit var schoolDetailsViewModel: SchoolDetailsViewModel
    private lateinit var viewStates: MutableList<ViewState<*>>

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewStates = mutableListOf()
        schoolDetailsViewModel = SchoolDetailsViewModel(getSchoolsDetailsUseCase, connectionManager)
        schoolDetailsViewModel.schoolDetailsLiveData.observeForever {
            viewStates.add(it)
        }
    }

    @Test
    fun `test no internet connection`() {
        `when`(connectionManager.isAvailableConnection()).thenReturn(false)
        schoolDetailsViewModel.getSchoolDetails("1")

        assert(viewStates[0] is ViewState.Error)
    }

    @Test
    fun `test Success`() {
        runBlocking {
            val successResult = SchoolDetailsEntity(
                "1",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
            `when`(connectionManager.isAvailableConnection()).thenReturn(true)
            `when`(getSchoolsDetailsUseCase.getSchoolDetails("1")).thenReturn(BaseResult.Success(successResult))

            schoolDetailsViewModel.getSchoolDetails("1")

            assert(viewStates[0] is ViewState.Loading)
            assert(viewStates[1] is ViewState.Success)
        }
    }

    @Test
    fun `test Success but no info`() {
        runBlocking {
            val successResult = SchoolDetailsEntity(
                "Not available",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
            `when`(connectionManager.isAvailableConnection()).thenReturn(true)
            `when`(getSchoolsDetailsUseCase.getSchoolDetails("1")).thenReturn(BaseResult.Success(successResult))

            schoolDetailsViewModel.getSchoolDetails("1")

            assert(viewStates[0] is ViewState.Loading)
            assert(viewStates[1] is ViewState.Error)
        }
    }

    @Test
    fun `test Error`() {
        runBlocking {
            `when`(connectionManager.isAvailableConnection()).thenReturn(true)
            `when`(getSchoolsDetailsUseCase.getSchoolDetails("1")).thenReturn(BaseResult.Error(Throwable()))

            schoolDetailsViewModel.getSchoolDetails("1")

            assert(viewStates[0] is ViewState.Loading)
            assert(viewStates[1] is ViewState.Error)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}