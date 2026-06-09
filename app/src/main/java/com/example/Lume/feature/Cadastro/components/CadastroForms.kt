package com.example.Lume.feature.Cadastro.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Lume.ui.theme.LilasPrincipal

@Composable
fun CadastroForms(
    tituloLivro: String,
    tituloError: String?,
    onTituloChange: (String) -> Unit,
    autor: String,
    autorError: String?,
    onAutorChange: (String) -> Unit,
    ano: String,
    anoError: String?,
    onAnoChange: (String) -> Unit,
    genero: String,
    generoError: String?,
    onGeneroChange: (String) -> Unit,
    mensagemSucesso: String?,
    onCadastrarClick: () -> Unit
) {

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        if (mensagemSucesso != null) {
            Text(
                text = mensagemSucesso,
                color = Color(0xFF2E7D32), // Verde escuro
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        CampoCadastro(
            titulo = "Título do Livro",
            valor = tituloLivro,
            erro = tituloError,
            onValueChange = onTituloChange,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            CampoCadastro(
                titulo = "Autor",
                valor = autor,
                erro = autorError,
                onValueChange = onAutorChange,
                modifier = Modifier.weight(1f)
            )

            CampoCadastro(
                titulo = "Ano",
                valor = ano,
                erro = anoError,
                onValueChange = onAnoChange,
                modifier = Modifier.width(120.dp)
            )
        }

        CampoCadastro(
            titulo = "Gênero",
            valor = genero,
            erro = generoError,
            onValueChange = onGeneroChange,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Botao(
                texto = "Cadastrar",
                onClick = onCadastrarClick
            )
        }
    }
}

@Composable
fun CampoCadastro(titulo: String, valor: String, erro: String?,
                  onValueChange: (String) -> Unit, modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = titulo)
        },
        isError = erro != null,
        supportingText = {
            if (erro != null) {
                Text(
                    text = erro,
                    color = MaterialTheme.colorScheme.error
                )
            }
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
    CadastroForms(
        tituloLivro = "",
        tituloError = "Erro no título",
        onTituloChange = {},
        autor = "",
        autorError = null,
        onAutorChange = {},
        ano = "",
        anoError = null,
        onAnoChange = {},
        genero = "",
        generoError = null,
        onGeneroChange = {},
        mensagemSucesso = "Livro cadastrado!",
        onCadastrarClick = {}
    )
}
