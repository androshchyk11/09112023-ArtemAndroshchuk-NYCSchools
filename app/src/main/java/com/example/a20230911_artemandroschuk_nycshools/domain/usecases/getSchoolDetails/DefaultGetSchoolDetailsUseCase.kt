package com.example.a20230911_artemandroschuk_nycshools.domain.usecases.getSchoolDetails

import com.example.a20230911_artemandroschuk_nycshools.data.repositories.getSchoolDetails.DefaultGetSchoolDetailsRepository
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolDetails.SchoolDetailsEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.result.BaseResult
import com.example.a20230911_artemandroschuk_nycshools.domain.result.foldResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultGetSchoolDetailsUseCase @Inject constructor(
    private val getSchoolDetailsRepository: DefaultGetSchoolDetailsRepository
) : GetSchoolDetailsUseCase {
    override suspend fun getSchoolDetails(schoolId: String): BaseResult<SchoolDetailsEntity> =
        runCatching { getSchoolDetailsRepository.getSchoolDetails(schoolId) }.foldResult()
}