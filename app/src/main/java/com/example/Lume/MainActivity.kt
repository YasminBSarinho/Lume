package com.example.Lume

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Lume.feature.Cadastro.CadastroScreen
import com.example.Lume.feature.biblioteca.BibliotecaScreen
import com.example.Lume.feature.sorteador.SorteadorScreen
import com.example.Lume.model.LivroViewModel
import com.example.Lume.ui.theme.LilasClaro
import com.example.Lume.ui.theme.LilasPrincipal
import com.example.Lume.ui.theme.LumeTheme
import com.example.Lume.ui.theme.TextoPrincipal
import com.example.Lume.ui.theme.TextoSecundario

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LumeTheme {
                val livroViewModel: LivroViewModel = viewModel()

                LumeApp(
                    livroViewModel = livroViewModel
                )
            }
        }
    }
}

@Composable
fun LumeApp(
    livroViewModel: LivroViewModel
) {
    var telaAtual by rememberSaveable {
        mutableStateOf(AppDestination.LIVROS)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LumeTopBar()
        },
        bottomBar = {
            LumeBottomBar(
                telaAtual = telaAtual,
                onTelaSelecionada = { novaTela ->
                    telaAtual = novaTela
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            AppContent(
                telaAtual = telaAtual,
                livroViewModel = livroViewModel
            )
        }
    }
}

@Composable
fun LumeTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 25.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Lume",
            color = LilasPrincipal,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    }
}

@Composable
fun LumeBottomBar(
    telaAtual: AppDestination,
    onTelaSelecionada: (AppDestination) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp
        )
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            AppDestination.entries.forEach { tela ->
                val selecionado = tela == telaAtual

                NavigationBarItem(
                    selected = selecionado,
                    onClick = {
                        onTelaSelecionada(tela)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = tela.icon),
                            contentDescription = tela.label,
                            modifier = Modifier.size(40.dp)
                        )
                    },
                    label = {
                        Text(
                            text = tela.label,
                            fontSize = 15.sp,
                            fontWeight = if (selecionado) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            }
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = LilasPrincipal,
                        selectedTextColor = LilasPrincipal,
                        unselectedIconColor = TextoSecundario,
                        unselectedTextColor = TextoSecundario,
                        indicatorColor = LilasClaro
                    )
                )
            }
        }
    }
}

@Composable
fun AppContent(
    telaAtual: AppDestination,
    livroViewModel: LivroViewModel
) {
    when (telaAtual) {
        AppDestination.LIVROS -> LivrosScreen(
            livroViewModel = livroViewModel
        )

        AppDestination.HOME -> HomeScreen(
            livroViewModel = livroViewModel
        )

        AppDestination.METAS -> MetasScreen()

        AppDestination.SORTEAR -> SortearScreen(
            livroViewModel = livroViewModel
        )
    }
}

enum class AppDestination(
    val label: String,
    val icon: Int
) {
    HOME("Início", R.drawable.ic_home),
    LIVROS("Biblioteca", R.drawable.ic_book_foreground),
    SORTEAR("Sortear", R.drawable.ic_boladecristal),
    METAS("Metas", R.drawable.ic_stara)
}

@Composable
fun LivrosScreen(
    livroViewModel: LivroViewModel
) {
    BibliotecaScreen(
        viewModel = livroViewModel
    )
}

@Composable
fun SortearScreen(
    livroViewModel: LivroViewModel
) {
    SorteadorScreen(
        livroViewModel = livroViewModel
    )
}

@Composable
fun HomeScreen(
    livroViewModel: LivroViewModel
) {
    CadastroScreen(
        livroViewModel = livroViewModel
    )
}

@Composable
fun MetasScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Tela de Metas",
            color = TextoPrincipal,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LumeTopBarPreview() {
    LumeTheme {
        LumeTopBar()
    }
}