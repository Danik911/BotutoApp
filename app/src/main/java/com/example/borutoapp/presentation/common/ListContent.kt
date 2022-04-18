package com.example.borutoapp.presentation.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.navigtion.Screen
import com.example.borutoapp.presentation.components.RatingWidget
import com.example.borutoapp.ui.theme.*
import com.example.borutoapp.util.Constants.BASE_URL

@Composable
fun ListContent(heroes: LazyPagingItems<Hero>, navController: NavHostController) {

    Log.d("ListContent", heroes.loadState.toString())
    LazyColumn(
        contentPadding = PaddingValues(PADDING_SMALL),
        verticalArrangement = Arrangement.spacedBy(PADDING_SMALL)
    ) {
        items(items = heroes, key = { hero: Hero ->
            hero.id
        }) { hero ->
            hero?.let {
                HeroItem(hero = hero, navController = navController)
            }

        }
    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HeroItem(hero: Hero, navController: NavHostController) {

    val painter = rememberImagePainter(data = "$BASE_URL${hero.image}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Detail.passArgument(heroId = hero.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(PADDING_LARGE)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.hero_image)
            )

        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.5f),
            shape = RoundedCornerShape(bottomStart = PADDING_LARGE, bottomEnd = PADDING_LARGE)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PADDING_MEDIUM)
            ) {
                Text(
                    text = hero.name,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = MaterialTheme.typography.h5.fontSize
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize
                )
                Row(
                    modifier = Modifier.padding(top = PADDING_SMALL),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = PADDING_SMALL),
                        rating = hero.rating
                    )
                    Text(
                        text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )

                }

            }

        }
    }
}

@Preview
@Composable
fun HeroItemPreview() {
    HeroItem(
        hero = Hero(
            id = 2,
            name = "Sasuke",
            image = "",
            about = "Some text about this hero and his family",
            rating = 2.5,
            power = 3,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ), navController = rememberNavController()
    )
}