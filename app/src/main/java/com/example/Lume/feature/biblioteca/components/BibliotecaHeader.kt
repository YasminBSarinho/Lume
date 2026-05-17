package com.example.Lume.feature.biblioteca.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun BibliotecaHeader(titulo : String, subtitulo : String) {
    Column{
        Text(
            text = titulo,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitulo,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BibliotecaHeaderPreview() {
    BibliotecaHeader(
        titulo = "Minha Biblioteca",
        subtitulo = "5 livros na sua coleção"
    )
}