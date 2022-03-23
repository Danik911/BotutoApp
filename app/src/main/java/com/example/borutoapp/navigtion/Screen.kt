package com.example.borutoapp.navigtion

sealed class Screen(val route: String){

    object Splash: Screen("splash_screen")

    object Welcome: Screen("welcome_screen")

    object Home: Screen("home_screen")

    object Detail: Screen("detail_screen/{heroId}"){
        fun passArgument(heroId: String): String{
            return "detail/$heroId"
        }
    }
    object Search: Screen("search_screen")
}
