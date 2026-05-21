package com.example.Lume

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.Lume.feature.Cadastro.CadastroScreen
import com.example.Lume.feature.biblioteca.BibliotecaScreen
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
                LumeApp()
            }
        }
    }
}

@Composable
fun LumeApp() {

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
            AppContent(telaAtual = telaAtual)
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
    telaAtual: AppDestination
) {
    when (telaAtual) {
        AppDestination.LIVROS -> LivrosScreen()
        AppDestination.SORTEAR -> SortearScreen()
        AppDestination.HOME -> HomeScreen()
        AppDestination.METAS -> MetasScreen()
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
fun LivrosScreen() {
    BibliotecaScreen()
}

@Composable
fun SortearScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Tela de Sorteio",
            color = TextoPrincipal,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Aqui depois entra a tela de sortear livro.",
            color = TextoPrincipal
        )
    }
}

@Composable
fun HomeScreen() {
    CadastroScreen()
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
fun LumeAppPreview() {
    LumeTheme {
        LumeApp()
    }
}