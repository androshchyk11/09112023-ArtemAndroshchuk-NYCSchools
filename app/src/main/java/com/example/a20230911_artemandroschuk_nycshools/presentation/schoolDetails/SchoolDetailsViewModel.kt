package com.example.a20230911_artemandroschuk_nycshools.presentation.schoolDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolDetails.SchoolDetailsEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolsList.SchoolItemModelMapper.Companion.NOT_AVAILABLE
import com.example.a20230911_artemandroschuk_nycshools.domain.networkManager.ConnectionManager
import com.example.a20230911_artemandroschuk_nycshools.domain.result.BaseResult
import com.example.a20230911_artemandroschuk_nycshools.domain.usecases.getSchoolDetails.DefaultGetSchoolDetailsUseCase
import com.example.a20230911_artemandroschuk_nycshools.presentation.viewState.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(
    private val getSchoolsDetailsUseCase: DefaultGetSchoolDetailsUseCase,
    private val connectionManager: ConnectionManager,
) : ViewModel() {

    private val _schoolDetailsLiveData = MutableLiveData<ViewState<SchoolDetailsEntity>>()
    val schoolDetailsLiveData: LiveData<ViewState<SchoolDetailsEntity>> = _schoolDetailsLiveData

    fun getSchoolDetails(schoolId: String) {
        if (connectionManager.isAvailableConnection()) {
            viewModelScope.launch {
                _schoolDetailsLiveData.postValue(ViewState.Loading)
                _schoolDetailsLiveData.postValue(
                    when (val result = getSchoolsDetailsUseCase.getSchoolDetails(schoolId)) {
                        is BaseResult.Success -> {
                            //workaround in case when we get no information
                            if (result.value.dbn == NOT_AVAILABLE) {
                                ViewState.Error(Throwable("No available info"))
                            } else {
                                ViewState.Success(result.value)
                            }
                        }
                        is BaseResult.Error -> ViewState.Error(result.cause)
                    }
                )
            }
        } else {
            _schoolDetailsLiveData.postValue(ViewState.Error(Throwable("Check your internet connection")))
        }
    }
}