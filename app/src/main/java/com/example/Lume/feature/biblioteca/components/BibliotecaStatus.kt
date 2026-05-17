package com.example.Lume.feature.biblioteca.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.Lume.ui.theme.LilasPrincipal

enum class Status(val texto: String){
    TODOS("Todos"),
    TBR("TBR"),
    LENDO("Lendo"),
    LIDO("Lido")
}

@Composable
fun BibliotecaStatus(statusEscolhido: Status, onStatusSelecionado: (Status) -> Unit) {
    Row (horizontalArrangement = Arrangement.spacedBy(8.dp)){
        Status.entries.forEach { status ->
            StatusItem(
                texto = status.texto,
                selecionado = status == statusEscolhido,
                onClick = { onStatusSelecionado(status) }
            )
        }
    }
}

@Composable
private fun StatusItem(texto: String, selecionado: Boolean, onClick: () -> Unit){
    val formato = RoundedCornerShape(50.dp)

    val corTexto = if (selecionado){
        Color.White
    } else {
        LilasPrincipal
    }

    val corBackground = if (selecionado){
        LilasPrincipal
    } else {
        Color.White
    }

    val corBorda = LilasPrincipal


    Text(
        text = texto,
        color = corTexto,
        modifier = Modifier
            .background(
                color = corBackground,
                shape = formato
            )
            .border(
                width = 1.dp,
                color = corBorda,
                shape = formato
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .clickable {
                onClick()
            }
    )
}

@Preview(showBackground = true)
@Composable
fun StatusPreview() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        StatusItem(
            texto = "Todos",
            selecionado = true,
            onClick = {}
        )

        StatusItem (
            texto = "Lendo",
            selecionado = false,
            onClick = {}
        )
    }
}
