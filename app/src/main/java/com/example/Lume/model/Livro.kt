package com.example.Lume.model

data class Livro(
    val id: Long = 0,
    val titulo: String,
    val autor: String,
    val ano: String,
    val genero: String,
    val status: String,
)