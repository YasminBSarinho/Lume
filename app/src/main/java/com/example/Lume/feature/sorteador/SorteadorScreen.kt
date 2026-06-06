package com.example.Lume.feature.sorteador

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Lume.feature.biblioteca.components.Status
import com.example.Lume.model.Livro
import com.example.Lume.model.LivroViewModel
import com.example.Lume.ui.theme.BrancoCard
import com.example.Lume.ui.theme.LilasBorda
import com.example.Lume.ui.theme.LilasClaro
import com.example.Lume.ui.theme.LilasPrincipal
import com.example.Lume.ui.theme.LumeTheme
import com.example.Lume.ui.theme.RosaBadge
import com.example.Lume.ui.theme.TextoPrincipal
import com.example.Lume.ui.theme.TextoSecundario

private const val TODOS_OS_GENEROS = "Todos"



@Composable
private fun CardEscolhaGenero(
    generoSelecionado: String,
    generos: List<String>,
    onGeneroSelecionado: (String) -> Unit
) {
    var menuAberto by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = BrancoCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "✦",
                    color = LilasPrincipal,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Escolha um gênero",
                    color = TextoPrincipal,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(LilasClaro.copy(alpha = 0.45f))
                        .border(
                            width = 1.dp,
                            color = LilasBorda,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clickable {
                            menuAberto = true
                        }
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = generoSelecionado,
                        color = TextoPrincipal,
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = "⌄",
                        color = LilasPrincipal,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                DropdownMenu(
                    expanded = menuAberto,
                    onDismissRequest = {
                        menuAberto = false
                    }
                ) {
                    generos.forEach { genero ->
                        DropdownMenuItem(
                            text = {
                                Text(text = genero)
                            },
                            onClick = {
                                onGeneroSelecionado(genero)
                                menuAberto = false
                            }
                        )
                    }
                }
            }

            Text(
                text = "Escolha um gênero para sortear um livro.",
                color = TextoSecundario,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun CardSeusLivros(
    livros: List<Livro>,
    livrosFiltrados: List<Livro>,
    generoSelecionado: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = BrancoCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Seus Livros",
                    color = TextoPrincipal,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                BadgeQuantidade(quantidade = livros.size)
            }

            if (livros.isEmpty()) {
                TextoListaVazia(
                    texto = "Nenhum livro cadastrado ainda. Cadastre um livro para começar o sorteio."
                )
            } else if (livrosFiltrados.isEmpty()) {
                TextoListaVazia(
                    texto = "Nenhum livro encontrado no gênero $generoSelecionado."
                )
            } else {
                livrosFiltrados.forEach { livro ->
                    ItemLivroSorteador(livro = livro)
                }
            }
        }
    }
}

@Composable
private fun BadgeQuantidade(quantidade: Int) {
    val texto = if (quantidade == 1) {
        "1 Livro"
    } else {
        "$quantidade Livros"
    }

    Text(
        text = texto,
        color = LilasPrincipal,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(
                color = LilasClaro,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
    )
}

@Composable
private fun TextoListaVazia(texto: String) {
    Text(
        text = texto,
        color = TextoSecundario,
        fontSize = 13.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = LilasClaro.copy(alpha = 0.35f),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(14.dp)
    )
}

@Composable
private fun ItemLivroSorteador(livro: Livro) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = LilasClaro.copy(alpha = 0.35f),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = livro.titulo,
            color = TextoPrincipal,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(
                        color = RosaBadge,
                        shape = RoundedCornerShape(50.dp)
                    )
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = livro.genero,
                color = TextoSecundario,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun BotaoSorteador(
    texto: String,
    corFundo: Color,
    corTexto: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = corFundo,
            contentColor = corTexto
        )
    ) {
        Text(
            text = texto,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ResultadoSorteioBottomSheet(
    livro: Livro,
    onDismiss: () -> Unit,
    onSortearNovamente: () -> Unit,
    onExcluir: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = "Livro Sorteado!",
                color = LilasPrincipal,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = BrancoCard),
                border = BorderStroke(1.dp, LilasBorda)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = livro.titulo,
                        color = TextoPrincipal,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = livro.genero,
                        color = TextoSecundario,
                        fontSize = 14.sp
                    )
                }
            }

            BotaoSorteador(
                texto = "Sortear Novamente",
                corFundo = LilasPrincipal,
                corTexto = Color.White,
                onClick = onSortearNovamente
            )

            TextButton(
                onClick = onExcluir,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Excluir este livro da lista",
                    color = LilasPrincipal,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CadastroLivroBottomSheet(
    onDismiss: () -> Unit,
    onCadastrar: (titulo: String, genero: String) -> Unit
) {
    val context = LocalContext.current

    var titulo by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }

    var tituloError by remember { mutableStateOf<String?>(null) }
    var generoError by remember { mutableStateOf<String?>(null) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Cadastrar Novo Livro",
                color = LilasPrincipal,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = titulo,
                onValueChange = {
                    titulo = it
                    tituloError = null
                },
                label = {
                    Text(text = "Título do Livro")
                },
                placeholder = {
                    Text(text = "Ex: O Código Da Vinci")
                },
                isError = tituloError != null,
                supportingText = {
                    tituloError?.let {
                        Text(text = it)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = genero,
                onValueChange = {
                    genero = it
                    generoError = null
                },
                label = {
                    Text(text = "Gênero")
                },
                placeholder = {
                    Text(text = "Ex: Romance")
                },
                isError = generoError != null,
                supportingText = {
                    generoError?.let {
                        Text(text = it)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            BotaoSorteador(
                texto = "Confirmar Cadastro",
                corFundo = LilasPrincipal,
                corTexto = Color.White,
                onClick = {
                    val tituloValido = titulo.isNotBlank()
                    val generoValido = genero.isNotBlank()

                    if (tituloValido && generoValido) {
                        onCadastrar(titulo.trim(), genero.trim())
                    } else {
                        tituloError = if (!tituloValido) {
                            "Título é obrigatório"
                        } else {
                            null
                        }

                        generoError = if (!generoValido) {
                            "Gênero é obrigatório"
                        } else {
                            null
                        }

                        Toast.makeText(
                            context,
                            "Preencha todos os campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

