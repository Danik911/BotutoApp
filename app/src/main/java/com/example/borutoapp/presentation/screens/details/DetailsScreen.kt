package com.example.borutoapp.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.borutoapp.util.Constants.BASE_URL
import com.example.borutoapp.util.PaletteGenerator.convertStringToBitmap
import com.example.borutoapp.util.PaletteGenerator.extractColorFromBitmap
import kotlinx.coroutines.flow.collect

@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorsPalette by detailsViewModel.colorPalette
    val context = LocalContext.current

    if (colorsPalette.isNotEmpty()) {
        DetailsContent(
            navHostController = navHostController,
            selectedHero = selectedHero, colors = colorsPalette
        )
    } else {
        detailsViewModel.startUiEvent()
        detailsViewModel.setPalette(colors = colorsPalette)
    }
    LaunchedEffect(key1 = true) {

        detailsViewModel.uiEvent.collect { state ->
            when (state) {
                UiState.GenerateColorPalette -> {

                    val bitMap = convertStringToBitmap(
                        imageUrl = "$BASE_URL${selectedHero?.image}",
                        context = context
                    )
                    Log.d("DetailsScreen", "$bitMap")
                    if (bitMap != null) {
                        detailsViewModel.setPalette(
                            extractColorFromBitmap(bitmap = bitMap)
                        )
                    }
                }
            }

        }
    }
}