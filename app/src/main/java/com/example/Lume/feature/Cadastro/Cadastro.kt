package com.example.Lume.feature.Cadastro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Lume.feature.Cadastro.components.CadastroForms
import com.example.Lume.feature.Cadastro.components.CadastroHeader

@Composable
fun CadastroScreen(){
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CadastroHeader(
            "Cadastro",
            "Cadastre um livro na sua biblioteca"
        )
        Spacer(modifier = Modifier.height(16.dp))

        CadastroForms()
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreenPreview(){
   CadastroScreen()
}