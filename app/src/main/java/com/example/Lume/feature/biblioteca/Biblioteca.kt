package com.example.Lume.feature.biblioteca

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Lume.feature.biblioteca.components.BibliotecaHeader
import com.example.Lume.feature.biblioteca.components.BibliotecaStatus
import com.example.Lume.feature.biblioteca.components.CardLivro
import com.example.Lume.feature.biblioteca.components.Status
import com.example.Lume.model.LivroViewModel
import com.example.Lume.ui.theme.LumeTheme

@Composable
fun BibliotecaScreen(viewModel: LivroViewModel) {

    var statusEscolhido by remember {
        mutableStateOf(Status.TODOS)
    }

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        BibliotecaHeader(
            titulo = "Minha Biblioteca",
            subtitulo = "5 livros na sua coleção"
        )

        Spacer(modifier = Modifier.height(16.dp))

        BibliotecaStatus(
            statusEscolhido = statusEscolhido,
            onStatusSelecionado = { novoStatus ->
                statusEscolhido = novoStatus
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(viewModel.livros) { livro ->
                CardLivro(
                    titulo = livro.titulo,
                    genero = livro.genero,
                    status = livro.status
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BibliotecaScreenPreview() {
    LumeTheme{
        BibliotecaScreen(LivroViewModel())
    }
}