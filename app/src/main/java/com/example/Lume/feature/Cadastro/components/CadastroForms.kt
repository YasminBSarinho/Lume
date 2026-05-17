package com.example.Lume.feature.Cadastro.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Lume.model.Livro
import com.example.Lume.model.LivroViewModel
import com.example.Lume.ui.theme.LilasPrincipal

@Composable
fun CadastroForms(viewModel: LivroViewModel) {

    var tituloLivro by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        CampoCadastro(
            titulo = "Título do Livro",
            valor = tituloLivro,
            onValueChange = { tituloLivro = it },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            CampoCadastro(
                titulo = "Autor",
                valor = autor,
                onValueChange = { autor = it },
                modifier = Modifier.weight(1f)
            )

            CampoCadastro(
                titulo = "Ano",
                valor = ano,
                onValueChange = { ano = it },
                modifier = Modifier.width(120.dp)
            )
        }

        CampoCadastro(
            titulo = "Gênero",
            valor = genero,
            onValueChange = { genero = it },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Botao(texto = "Cadastrar", onClick = {
                val livro = Livro(
                    titulo = tituloLivro,
                    autor = autor,
                    ano = ano,
                    genero = genero,
                    status = "LENDO"
                )
                viewModel.adicionarLivro(livro)
            })
        }
    }
}

@Composable
fun CampoCadastro(titulo: String, valor: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = titulo)
        },
        singleLine = true
    )
}

@Composable
fun Botao(texto: String, modifier: Modifier = Modifier, onClick: () -> Unit) {

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = LilasPrincipal
        )
    ) {

        Text(
            text = texto,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroFormsPreview() {
    CadastroForms(LivroViewModel())
}