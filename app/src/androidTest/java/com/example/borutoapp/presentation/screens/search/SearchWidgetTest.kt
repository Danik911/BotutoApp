package com.example.borutoapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_InputText_AssertText() {

        val text = mutableStateOf("")

        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                         text.value = it
                },
                onSearchClick = {},
                onCloseClick = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("SearchTextField").performTextInput("Test")
        composeTestRule.onNodeWithContentDescription("SearchTextField").assertTextEquals("Test")
    }
    @Test
    fun openSearchWidget_InputText_PressCloseIconOnce_AssertTextEmpty() {

        val text = mutableStateOf("")

        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onSearchClick = {},
                onCloseClick = {

                }
            )
        }
        composeTestRule.onNodeWithContentDescription("SearchTextField").performTextInput("Test")
        composeTestRule.onNodeWithContentDescription("CloseIcon").performClick()
        composeTestRule.onNodeWithContentDescription("SearchTextField").assertTextContains("")
    }
    @Test
    fun openSearchWidget_InputText_PressCloseIconTwice_AssertWidgetDoesNotExist() {

        val text = mutableStateOf("")
        val showWidget = mutableStateOf(true)


            composeTestRule.setContent {
                if (showWidget.value){
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onSearchClick = {},
                    onCloseClick = {
                        showWidget.value = false
                    }
                )
            }
        }


        composeTestRule.onNodeWithContentDescription("SearchTextField").performTextInput("Test")
        composeTestRule.onNodeWithContentDescription("CloseIcon").performClick()
        composeTestRule.onNodeWithContentDescription("CloseIcon").performClick()
        composeTestRule.onNodeWithContentDescription("SearchTextField").assertDoesNotExist()
    }
    @Test
    fun openSearchWidget_InputText_PressCloseIconOnce_AssertWidgetDoesNotExist() {

        val text = mutableStateOf("")
        val showWidget = mutableStateOf(true)


        composeTestRule.setContent {
            if (showWidget.value){
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onSearchClick = {},
                    onCloseClick = {
                        showWidget.value = false
                    }
                )
            }
        }


        composeTestRule.onNodeWithContentDescription("CloseIcon").performClick()
        composeTestRule.onNodeWithContentDescription("SearchTextField").assertDoesNotExist()
    }
}