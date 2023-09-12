package com.example.a20230911_artemandroschuk_nycshools.domain.mappers

interface Mapper<Model, Entity> {

    fun mapFrom(model: Model): Entity

    fun mapTo(entity: Entity): Model

}

fun <Model, Entity> List<Model>.mapListFrom(mapper: Mapper<Model, Entity>): List<Entity> = map { mapper.mapFrom(it) }

fun <Model, Entity> List<Entity>.mapListTo(mapper: Mapper<Model, Entity>): List<Model> = map { mapper.mapTo(it) }