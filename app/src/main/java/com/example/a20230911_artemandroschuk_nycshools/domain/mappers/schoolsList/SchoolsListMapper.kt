package com.example.a20230911_artemandroschuk_nycshools.domain.mappers.schoolsList

import com.example.a20230911_artemandroschuk_nycshools.data.models.schoolItem.SchoolItemModel
import com.example.a20230911_artemandroschuk_nycshools.domain.entities.schoolItem.SchoolItemEntity
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.Mapper
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.mapListFrom
import com.example.a20230911_artemandroschuk_nycshools.domain.mappers.mapListTo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolsListMapper @Inject constructor(
    private val schoolItemModelMapper: SchoolItemModelMapper
) : Mapper<List<SchoolItemModel>, List<SchoolItemEntity>> {
    override fun mapFrom(model: List<SchoolItemModel>): List<SchoolItemEntity> =
        model.mapListFrom(schoolItemModelMapper)


    override fun mapTo(entity: List<SchoolItemEntity>): List<SchoolItemModel> =
        entity.mapListTo(schoolItemModelMapper)
}