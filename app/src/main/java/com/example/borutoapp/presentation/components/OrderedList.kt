package com.example.borutoapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.ui.theme.PADDING_SMALL
import com.example.borutoapp.ui.theme.titleColor

@Composable
fun OrderedList(
    title: String,
    textColor: Color,
    infoList: List<String>
) {
    Column {
        Text(
            modifier = Modifier.padding(bottom = PADDING_SMALL),
            text = title,
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
        infoList.forEachIndexed { index, item ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index + 1}. $item",
                color = textColor,
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OrderedListPreview() {
    OrderedList(
        title = "Family",
        textColor = MaterialTheme.colors.titleColor,
        infoList = listOf("Mother", "Father", "Some relatives")
    )
}