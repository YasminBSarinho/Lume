package com.example.Lume.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.Lume.entity.LivroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LivroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirLivro(livro: LivroEntity): Long

    @Update
    suspend fun atualizarLivro(livro: LivroEntity)

    @Delete
    suspend fun deletarLivro(livro: LivroEntity)

    @Query("DELETE FROM livros WHERE id = :id")
    suspend fun deletarLivroPorId(id: Long)

    @Query("SELECT * FROM livros ORDER BY titulo ASC")
    fun listarLivros(): Flow<List<LivroEntity>>

    @Query("SELECT DISTINCT genero FROM livros WHERE genero IS NOT NULL AND genero != '' ORDER BY genero ASC")
    fun listarGeneros(): Flow<List<String>>

}