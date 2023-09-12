package com.example.a20230911_artemandroschuk_nycshools.domain.repositories.getAllSchools

import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity

interface GetSchoolsRepository {
    suspend fun getAllSchools():List<SchoolItemEntity>
}