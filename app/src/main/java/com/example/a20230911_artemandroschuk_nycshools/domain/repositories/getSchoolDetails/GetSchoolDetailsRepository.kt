package com.example.a20230911_artemandroschuk_nycshools.domain.repositories.getSchoolDetails

import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolDetails.SchoolDetailsEntity

interface GetSchoolDetailsRepository {
    suspend fun getSchoolDetails(schoolId: String): SchoolDetailsEntity
}