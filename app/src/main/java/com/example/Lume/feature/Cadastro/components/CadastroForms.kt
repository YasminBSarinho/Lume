package com.example.Lume.feature.Cadastro.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Label
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (mensagemSucesso != null) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E9)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text(
                    text = "✓ $mensagemSucesso",
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        CampoCadastro(
            titulo = "Título do Livro",
            valor = tituloLivro,
            erro = tituloError,
            onValueChange = onTituloChange,
            icone = Icons.Default.Book,
            placeholder = "Ex: O Senhor dos Anéis",
            imeAction = ImeAction.Next,
            modifier = Modifier.fillMaxWidth()
        )

        CampoCadastro(
            titulo = "Autor",
            valor = autor,
            erro = autorError,
            onValueChange = onAutorChange,
            icone = Icons.Default.AccountCircle,
            placeholder = "Nome do autor",
            imeAction = ImeAction.Next,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CampoCadastro(
                titulo = "Ano",
                valor = ano,
                erro = anoError,
                onValueChange = onAnoChange,
                icone = Icons.Default.CalendarToday,
                placeholder = "2024",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                modifier = Modifier.weight(1f)
            )

            CampoCadastro(
                titulo = "Gênero",
                valor = genero,
                erro = generoError,
                onValueChange = onGeneroChange,
                icone = Icons.Default.Label,
                placeholder = "Fantasia",
                imeAction = ImeAction.Done,
                modifier = Modifier.weight(1.5f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Botao(
            texto = "Cadastrar Livro",
            modifier = Modifier.fillMaxWidth().height(56.dp),
            onClick = onCadastrarClick
        )
    }
}

@Composable
fun CampoCadastro(
    titulo: String,
    valor: String,
    erro: String?,
    onValueChange: (String) -> Unit,
    icone: ImageVector,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = valor,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = titulo) },
            placeholder = { Text(text = placeholder, color = Color.Gray.copy(alpha = 0.6f)) },
            leadingIcon = {
                Icon(
                    imageVector = icone,
                    contentDescription = null,
                    tint = if (erro != null) MaterialTheme.colorScheme.error else LilasPrincipal
                )
            },
            isError = erro != null,
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            )
        )
        if (erro != null) {
            Text(
                text = erro,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Composable
fun Botao(texto: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LilasPrincipal
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = texto,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
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
