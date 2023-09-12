package com.example.a20230911_artemandroschuk_nycshools.domain.usecases.getAllSchools

import com.example.a20230911_artemandroschuk_nycshools.data.repositories.getAllSchools.DefaultGetSchoolsRepository
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.result.BaseResult
import com.example.a20230911_artemandroschuk_nycshools.domain.result.foldResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultGetSchoolsUseCase @Inject constructor(
    private val getSchoolsRepository: DefaultGetSchoolsRepository
): GetSchoolsUseCase {
    override suspend fun getListOfSchools(): BaseResult<List<SchoolItemEntity>> =
        runCatching { getSchoolsRepository.getAllSchools() }.foldResult()
}