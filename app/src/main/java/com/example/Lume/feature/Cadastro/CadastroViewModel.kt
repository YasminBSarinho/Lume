package com.example.Lume.feature.Cadastro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.Lume.model.Livro
import com.example.Lume.model.LivroViewModel

class CadastroViewModel : ViewModel() {

    var uiState by mutableStateOf(CadastroUiState())
        private set

    fun updateTitulo(titulo: String) {
        uiState = uiState.copy(
            titulo = titulo,
            tituloError = null,
            mensagemSucesso = null
        )
    }

    fun updateAutor(autor: String) {
        uiState = uiState.copy(
            autor = autor,
            autorError = null,
            mensagemSucesso = null
        )
    }

    fun updateAno(ano: String) {
        uiState = uiState.copy(
            ano = ano,
            anoError = null,
            mensagemSucesso = null
        )
    }

    fun updateGenero(genero: String) {
        uiState = uiState.copy(
            genero = genero,
            generoError = null,
            mensagemSucesso = null
        )
    }

    fun cadastrarLivro(livroViewModel: LivroViewModel) {
        val tituloValido = uiState.titulo.isNotBlank()
        val autorValido = uiState.autor.isNotBlank()
        val anoValido = uiState.ano.isNotBlank() && uiState.ano.all { it.isDigit() }
        val generoValido = uiState.genero.isNotBlank()

        if (tituloValido && autorValido && anoValido && generoValido) {
            val novoLivro = Livro(
                titulo = uiState.titulo,
                autor = uiState.autor,
                ano = uiState.ano,
                genero = uiState.genero,
                status = "TBH"
            )
            
            livroViewModel.adicionarLivro(novoLivro)

            uiState = CadastroUiState(
                mensagemSucesso = "Livro '${novoLivro.titulo}' cadastrado com sucesso!")
        } else {
            uiState = uiState.copy(
                tituloError = if (!tituloValido) "Título é obrigatório" else null,
                autorError = if (!autorValido) "Autor é obrigatório" else null,
                anoError = when {
                    uiState.ano.isBlank() -> "Ano é obrigatório"
                    !uiState.ano.all { it.isDigit() } -> "Ano deve conter apenas números"
                    else -> null
                },
                generoError = if (!generoValido) "Gênero é obrigatório" else null,
                mensagemSucesso = null
            )
        }
    }
}

data class CadastroUiState(
    val titulo: String = "",
    val tituloError: String? = null,
    val autor: String = "",
    val autorError: String? = null,
    val ano: String = "",
    val anoError: String? = null,
    val genero: String = "",
    val generoError: String? = null,
    val mensagemSucesso: String? = null
)
