package com.example.borutoapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.INFO_BOX_ICON_SIZE
import com.example.borutoapp.ui.theme.PADDING_SMALLEST
import com.example.borutoapp.ui.theme.titleColor
import com.example.borutoapp.ui.theme.topAppBarContentColor

@Composable
fun InfoBox(
    bigText: String,
    smallText: String,
    textColor: Color,
    icon: Painter,
    iconColor: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(end = PADDING_SMALLEST)
                .size(INFO_BOX_ICON_SIZE),
            painter = icon,
            contentDescription = stringResource(R.string.info_icon),
            tint = iconColor
        )
        Column {
            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Black
            )
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                color = textColor,
                fontSize = MaterialTheme.typography.overline.fontSize
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun InfoBoxPreview() {
    InfoBox(
        bigText = "34",
        smallText = "Power",
        textColor = MaterialTheme.colors.titleColor,
        icon = painterResource(
            id = R.drawable.ic_bolt
        ),
        iconColor = MaterialTheme.colors.primary
    )
}