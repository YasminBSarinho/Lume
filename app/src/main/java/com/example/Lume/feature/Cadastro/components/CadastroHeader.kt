package com.example.Lume.feature.Cadastro.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.Lume.ui.theme.TextoPrincipal
import com.example.Lume.ui.theme.TextoSecundario


@Composable
fun CadastroHeader(titulo : String, subtitulo: String){
    Column{
        Text(
            text = titulo,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextoPrincipal
        )
        Text(
            text = subtitulo,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextoSecundario
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroHeaderPreview(){
    CadastroHeader(
        titulo = "Cadastre um Livro",
        subtitulo = "Escolha um livro para cadastrar nos sorteios"
    )
}