package com.example.Lume.feature.Cadastro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Lume.feature.Cadastro.components.CadastroForms
import com.example.Lume.feature.Cadastro.components.CadastroHeader
import com.example.Lume.model.LivroViewModel

@Composable
fun CadastroScreen(
    livroViewModel: LivroViewModel,
    cadastroViewModel: CadastroViewModel = viewModel()
){
    val uiState = cadastroViewModel.uiState

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CadastroHeader(
            "Cadastro",
            "Cadastre um livro na sua biblioteca"
        )
        Spacer(modifier = Modifier.height(16.dp))

        CadastroForms(
            tituloLivro = uiState.titulo,
            tituloError = uiState.tituloError,
            onTituloChange = { cadastroViewModel.updateTitulo(it) },
            autor = uiState.autor,
            autorError = uiState.autorError,
            onAutorChange = { cadastroViewModel.updateAutor(it) },
            ano = uiState.ano,
            anoError = uiState.anoError,
            onAnoChange = { cadastroViewModel.updateAno(it) },
            genero = uiState.genero,
            generoError = uiState.generoError,
            onGeneroChange = { cadastroViewModel.updateGenero(it) },
            mensagemSucesso = uiState.mensagemSucesso,
            onCadastrarClick = { cadastroViewModel.cadastrarLivro(livroViewModel) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreenPreview() {
    CadastroScreen(LivroViewModel())
}
