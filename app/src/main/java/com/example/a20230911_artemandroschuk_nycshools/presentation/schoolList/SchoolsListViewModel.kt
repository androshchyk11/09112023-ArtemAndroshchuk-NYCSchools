package com.example.a20230911_artemandroschuk_nycshools.presentation.schoolList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.networkManager.ConnectionManager
import com.example.a20230911_artemandroschuk_nycshools.domain.result.BaseResult
import com.example.a20230911_artemandroschuk_nycshools.domain.usecases.getAllSchools.DefaultGetSchoolsUseCase
import com.example.a20230911_artemandroschuk_nycshools.presentation.viewState.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsListViewModel @Inject constructor(
    private val getSchoolsUseCase: DefaultGetSchoolsUseCase,
    private val connectionManager: ConnectionManager,
) : ViewModel() {

    private val _schoolsListLiveData = MutableLiveData<ViewState<List<SchoolItemEntity>>>()
    val schoolsListLiveData: LiveData<ViewState<List<SchoolItemEntity>>> = _schoolsListLiveData

    fun getSchools() {
        if (connectionManager.isAvailableConnection()) {
            viewModelScope.launch {
                _schoolsListLiveData.postValue(ViewState.Loading)
                _schoolsListLiveData.postValue(
                    when (val result = getSchoolsUseCase.getListOfSchools()) {
                        is BaseResult.Success -> {
                            ViewState.Success(result.value)
                        }
                        is BaseResult.Error -> ViewState.Error(result.cause)
                    }
                )
            }
        } else {
            _schoolsListLiveData.postValue(ViewState.Error(Throwable("Check your internet connection")))
        }
    }
}