package com.example.a20230911_artemandroschuk_nycshools.domain.usecases.getSchoolDetails

import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolDetails.SchoolDetailsEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.result.BaseResult

interface GetSchoolDetailsUseCase {
    suspend fun getSchoolDetails(schoolId:String): BaseResult<SchoolDetailsEntity>
}