package com.example.Lume.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.Lume.database.DataStorePreferences
import com.example.Lume.database.LumeDatabase
import com.example.Lume.repository.LivroRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LivroViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LivroRepository = LivroRepository(
        LumeDatabase.getDatabase(application).livroDao()
    )

    private val dataStore = DataStorePreferences(application)

    val generoSorteador: StateFlow<String> = dataStore.generoSorteador
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = "Todos"
        )

    val ultimoLivroSorteadoId: StateFlow<Long> = dataStore.ultimoLivroSorteadoId
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0L
        )

    val livros: StateFlow<List<Livro>> = repository.listarLivros()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun salvarGeneroSorteador(genero: String) {
        viewModelScope.launch {
            dataStore.salvarGeneroSorteador(genero)
        }
    }

    fun salvarUltimoLivroSorteadoId(id: Long) {
        viewModelScope.launch {
            dataStore.salvarUltimoLivroSorteadoId(id)
        }
    }

    fun adicionarLivro(livro: Livro) {
        viewModelScope.launch {
            repository.adicionarLivro(livro)
        }
    }

    fun atualizarLivro(livro: Livro) {
        viewModelScope.launch {
            repository.atualizarLivro(livro)
        }
    }

    fun removerLivro(livro: Livro) {
        viewModelScope.launch {
            repository.removerLivro(livro)
        }
    }
}