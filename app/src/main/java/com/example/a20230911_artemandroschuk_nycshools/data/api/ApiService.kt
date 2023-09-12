package com.example.a20230911_artemandroschuk_nycshools.data.api

import com.example.a20230911_artemandroschuk_nycshools.data.models.schoolDetails.SchoolDetailsModel
import com.example.a20230911_artemandroschuk_nycshools.data.models.schoolItem.SchoolItemModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("s3k6-pzi2.json")
    suspend fun getAllSchools(): List<SchoolItemModel>

    @GET("f9bf-2cp4.json")
    suspend fun getSchoolDetails(
        @Query("dbn") dbn: String
    ): List<SchoolDetailsModel>
}