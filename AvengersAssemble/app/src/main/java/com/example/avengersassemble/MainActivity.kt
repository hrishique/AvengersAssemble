package com.example.avengersassemble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.avengersassemble.ui.theme.AvengersAssembleTheme
import com.example.avengersassemble.view.CharacterDetailScreen
import com.example.avengersassemble.view.CollectionScreen
import com.example.avengersassemble.view.LibraryScreen

sealed class Destination(val route:String){
    object Library: Destination("library")
    object Collection: Destination("collection")
    object CharacterDetail: Destination("character/{charactedId}"){
        fun createRoute(characterId: Int?) ="character/$characterId"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvengersAssembleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController= rememberNavController()
                    CharactersScaffold(navController = navController)
                }
            }
        }
    }
}


@Composable
fun CharactersScaffold(navController: NavHostController){

    val scaffoldState  = rememberScaffoldState()

    Scaffold(
        scaffoldState=scaffoldState,
        bottomBar = {}
    ) {paddingValues ->
        NavHost(navController = navController, startDestination = Destination.Library.route){
            composable(Destination.Library.route){
                LibraryScreen()
            }
            composable(Destination.Collection.route){
                CollectionScreen()
            }
            composable(Destination.CharacterDetail.route){ navBackStackEntry ->
            }
        }


    }




}
