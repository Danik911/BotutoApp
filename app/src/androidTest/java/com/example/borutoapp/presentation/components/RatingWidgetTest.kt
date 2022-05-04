package com.example.borutoapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import com.example.borutoapp.ui.theme.PADDING_SMALL
import org.junit.Rule
import org.junit.Test

class RatingWidgetTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun ratingNegativeNumber_Assert_FiveEmptyStars(){

        composeRule.setContent {
            RatingWidget(modifier = Modifier.padding(PADDING_SMALL), rating = -1.6)

        }

        composeRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(5)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(0)
    }
    @Test
    fun ratingInvalidNumber_Assert_FiveEmptyStars(){

        composeRule.setContent {
            RatingWidget(modifier = Modifier.padding(PADDING_SMALL), rating = 5.1)

        }

        composeRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(5)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(0)
    }
    @Test
    fun ratingOnePointFour_Assert_ThreeEmptyStars_OneHalfEmptyStar_OneFilledStar(){

        composeRule.setContent {
            RatingWidget(modifier = Modifier.padding(PADDING_SMALL), rating = 1.4)

        }

        composeRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(3)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(1)
        composeRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(1)
    }
    @Test
    fun ratingOnePointSix_Assert_ThreeEmptyStars_TwoFilledStar(){

        composeRule.setContent {
            RatingWidget(modifier = Modifier.padding(PADDING_SMALL), rating = 1.6)

        }

        composeRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(3)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(2)
    }
    @Test
    fun ratingFour_Assert_OneEmptyStars_FourFilledStar(){

        composeRule.setContent {
            RatingWidget(modifier = Modifier.padding(PADDING_SMALL), rating = 4.0)

        }

        composeRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(1)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(0)
        composeRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(4)
    }
    @Test
    fun ratingZeroPointFour_Assert_FourEmptyStars_OneHalfEmptyStar(){

        composeRule.setContent {
            RatingWidget(modifier = Modifier.padding(PADDING_SMALL), rating = 0.4)

        }

        composeRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(4)
        composeRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(1)
        composeRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(0)
    }
}