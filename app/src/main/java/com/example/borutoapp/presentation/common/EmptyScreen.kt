package com.example.borutoapp.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.ERROR_ICON_SIZE
import com.example.borutoapp.ui.theme.PADDING_SMALL
import com.example.borutoapp.ui.theme.iconColor
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error? = null) {
    var message by remember {
        mutableStateOf("Find your Favorite Hero!")
    }
    var icon by remember {
        mutableStateOf(R.drawable.ic_search_document)
    }
    if (error != null) {
        message = parseError(error = error)
        icon = (R.drawable.ic_network_error)
    }
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val animAlpha by
    animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f, animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(message = message, alpha = animAlpha, icon = icon)


}

@Composable
fun EmptyContent(message: String, alpha: Float, icon: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(ERROR_ICON_SIZE)
                .alpha(alpha = alpha),
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.connection_error_ic),
            tint = MaterialTheme.colors.iconColor

        )
        Text(
            modifier = Modifier
                .padding(top = PADDING_SMALL)
                .alpha(alpha = alpha),
            text = message,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            color = MaterialTheme.colors.iconColor
        )

    }
}

private fun parseError(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> "Server Unavailable"
        is ConnectException -> "Internet Unavailable"
        else -> "Unknown Error"
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyScreen(error = LoadState.Error(SocketTimeoutException()))
}