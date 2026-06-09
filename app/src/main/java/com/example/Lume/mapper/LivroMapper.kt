package com.example.Lume.mapper

import com.example.Lume.entity.LivroEntity
import com.example.Lume.model.Livro

fun LivroEntity.toModel(): Livro {
    return Livro(
        id = id,
        titulo = titulo,
        autor = autor,
        ano = ano,
        genero = genero,
        status = status
    )
}

fun Livro.toEntity(): LivroEntity {
    return LivroEntity(
        id = id,
        titulo = titulo,
        autor = autor,
        ano = ano,
        genero = genero,
        status = status
    )
}