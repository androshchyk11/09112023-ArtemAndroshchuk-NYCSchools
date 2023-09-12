package com.example.a20230911_artemandroschuk_nycshools.data.repositories.getSchoolDetails

import com.example.a20230911_artemandroschuk_nycshools.data.api.ApiService
import com.example.a20230911_artemandroschuk_nycshools.data.models.schoolDetails.SchoolDetailsModel
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolDetails.SchoolDetailsEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolDetails.SchoolDetailsModelMapper
import com.example.a20230911_artemandroschuk_nycshools.domain.repositories.getSchoolDetails.GetSchoolDetailsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultGetSchoolDetailsRepository @Inject constructor(
    private val api: ApiService,
    private val schoolDetailsModelMapper: SchoolDetailsModelMapper
) : GetSchoolDetailsRepository {
    override suspend fun getSchoolDetails(schoolId: String): SchoolDetailsEntity {
        val result = if (api.getSchoolDetails(schoolId).isNotEmpty()) run {
            api.getSchoolDetails(schoolId).first()
        } else {
            SchoolDetailsModel()
        }
        return schoolDetailsModelMapper.mapFrom(result)
    }
}