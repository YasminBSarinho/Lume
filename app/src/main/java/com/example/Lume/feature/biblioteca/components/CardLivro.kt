package com.example.Lume.feature.biblioteca.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Lume.ui.theme.LilasClaro
import com.example.Lume.ui.theme.LilasPrincipal
import com.example.Lume.ui.theme.TextoPrincipal
import com.example.Lume.ui.theme.TextoSecundario
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

@Composable
fun CardLivro(titulo : String, genero : String, status: String){
    val formato = RoundedCornerShape(50.dp)

    val corStatusBackground = if (status == "Lendo"){
        Color.White
    } else {
        LilasPrincipal
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ){
            CapaLivro()

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        text = titulo,
                        fontSize = 15.sp,
                        color = TextoPrincipal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = genero,
                        color = TextoSecundario,
                        fontWeight = FontWeight.W400
                    )
                }

                Text(
                    text = status,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(
                            color = LilasPrincipal,
                            shape = formato
                        )
                        .border(
                            width = 1.dp,
                            color = LilasClaro,
                            shape = formato
                        )
                        .padding(
                            horizontal = 12.dp,
                            vertical = 3.dp
                        )
                )
            }
        }
    }
}

@Composable
private fun CapaLivro(){
    Column(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(LilasClaro)
    ) { }
}

@Preview(showBackground = true)
@Composable
fun CardLivroPreview() {
    CardLivro(
        titulo = "O Morro dos Ventos Uivantes",
        genero = "Romance",
        status = "Lendo"
    )
}