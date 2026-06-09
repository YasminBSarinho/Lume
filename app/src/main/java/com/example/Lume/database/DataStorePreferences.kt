package com.example.Lume.database


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "lume_preferences"
)

class DataStorePreferences(
    private val context: Context
) {
    private object Keys {
        val GENERO_SORTEADOR = stringPreferencesKey("genero_sorteador")
        val ULTIMO_LIVRO_SORTEADO_ID = longPreferencesKey("ultimo_livro_sorteado_id")
    }

    val generoSorteador: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[Keys.GENERO_SORTEADOR] ?: "Todos"
    }

    val ultimoLivroSorteadoId: Flow<Long> = context.dataStore.data.map { preferences ->
        preferences[Keys.ULTIMO_LIVRO_SORTEADO_ID] ?: 0L
    }

    suspend fun salvarGeneroSorteador(genero: String) {
        context.dataStore.edit { preferences ->
            preferences[Keys.GENERO_SORTEADOR] = genero
        }
    }

    suspend fun salvarUltimoLivroSorteadoId(id: Long) {
        context.dataStore.edit { preferences ->
            preferences[Keys.ULTIMO_LIVRO_SORTEADO_ID] = id
        }
    }
}