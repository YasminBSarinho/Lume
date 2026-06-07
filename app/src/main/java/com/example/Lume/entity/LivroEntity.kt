package com.example.Lume.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.Lume.model.StatusLivro

@Entity(
    tableName = "livros",
    indices = [
        Index(value = ["genero"]),
        Index(value = ["status"])
    ]
)
data class LivroEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "titulo")
    val titulo: String,

    @ColumnInfo(name = "autor")
    val autor: String,

    @ColumnInfo(name = "ano")
    val ano: String,

    @ColumnInfo(name = "genero")
    val genero: String,

    @ColumnInfo(name = "status")
    val status: String = StatusLivro.TBR.texto,

    @ColumnInfo(name = "data_cadastro")
    val dataCadastro: Long = System.currentTimeMillis()
)