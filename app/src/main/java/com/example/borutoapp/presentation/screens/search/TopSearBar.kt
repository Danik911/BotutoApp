package com.example.borutoapp.presentation.screens.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.APP_BAR_HEIGHT
import com.example.borutoapp.ui.theme.topAppBarBackgroundColor
import com.example.borutoapp.ui.theme.topAppBarContentColor

@Composable
fun TopSearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClick: (String) -> Unit,
    onCloseClick: () -> Unit
) {
    SearchWidget(
        text = text,
        onTextChange = onTextChange,
        onSearchClick = onSearchClick,
        onCloseClick = onCloseClick
    )
}

@Composable
fun SearchWidget(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClick: (String) -> Unit,
    onCloseClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(APP_BAR_HEIGHT)
            .semantics {
                contentDescription = "SearchField"
            },
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription = "SearchTextField"
                },
            value = text,
            textStyle = TextStyle(color = MaterialTheme.colors.topAppBarContentColor),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topAppBarContentColor
            ),
            onValueChange = { onTextChange(it) },
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    text = stringResource(R.string.placeholder_search),
                    color = MaterialTheme.colors.topAppBarContentColor
                )
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.semantics {
                        contentDescription = "CloseIcon"
                    }, onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClick()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.search_icon),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClick(text) })
        )

    }

}

@Preview(showBackground = true)
@Composable
fun SearAppBarPreview() {
    TopSearchBar(text = "", onTextChange = {}, onSearchClick = {}, onCloseClick = {})
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearAppBarNightPreview() {
    TopSearchBar(text = "", onTextChange = {}, onSearchClick = {}, onCloseClick = {})
}