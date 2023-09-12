package com.example.a20230911_artemandroschuk_nycshools.domain.usecases.getAllSchools

import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.result.BaseResult

interface GetSchoolsUseCase {
    suspend fun getListOfSchools(): BaseResult<List<SchoolItemEntity>>
}