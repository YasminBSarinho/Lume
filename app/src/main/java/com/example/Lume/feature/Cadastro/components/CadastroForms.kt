package com.example.Lume.feature.Cadastro.components

import android.R.attr.fontWeight
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.sp
import com.example.Lume.ui.theme.LilasPrincipal
import com.example.Lume.ui.theme.TextoPrincipal
import com.example.Lume.ui.theme.TextoSecundario


@Composable
fun CadastroForms(){
    
    var tituloLivro by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        CampoCadastro(
            titulo = "Titulo do Livro",
            valor = tituloLivro,
            onValueChange = { tituloLivro = it }
        )
        CampoCadastro(
            titulo = "Autor",
            valor = autor,
            onValueChange = { autor = it }
        )

        CampoCadastro(
            titulo = "Gênero",
            valor = genero,
            onValueChange = { genero = it }
        )

        CampoCadastro(
            titulo = "Ano",
            valor = ano,
            onValueChange = { ano = it }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            ){
            Botao(texto = "cadastrar")
        }
    }
}


@Composable
fun Botao(texto: String, modifier: Modifier = Modifier) {
    Button(
        onClick = {
            println("Livro cadastrado")
        },
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

@Composable
fun CampoCadastro(titulo : String, valor: String, onValueChange: (String) -> Unit ){
    Text(
        text = titulo,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = TextoPrincipal
    )
    TextField(
        value = valor,
        onValueChange = onValueChange,
        label = {
            Text(
            text = "Coloque o valor aqui",
            color = TextoSecundario,

        )},
        modifier = Modifier.fillMaxWidth()
        )
}

@Preview(showBackground = true)
@Composable
fun CadastroFormsPreview(){
    CadastroForms()
}