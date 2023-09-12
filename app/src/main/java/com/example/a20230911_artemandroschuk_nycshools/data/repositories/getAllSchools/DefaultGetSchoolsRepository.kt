package com.example.a20230911_artemandroschuk_nycshools.data.repositories.getAllSchools

import com.example.a20230911_artemandroschuk_nycshools.data.api.ApiService
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolsList.SchoolsListMapper
import com.example.a20230911_artemandroschuk_nycshools.domain.repositories.getAllSchools.GetSchoolsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultGetSchoolsRepository @Inject constructor(
    private val api: ApiService,
    private val schoolsListMapper: SchoolsListMapper
) : GetSchoolsRepository {
    override suspend fun getAllSchools(): List<SchoolItemEntity> =
        schoolsListMapper.mapFrom(api.getAllSchools())
}