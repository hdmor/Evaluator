package m.eight.evaluator.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import m.eight.evaluator.presentation.categories.CategoriesScreen
import m.eight.evaluator.presentation.entities.EntitiesScreen
import m.eight.evaluator.presentation.properties.PropertiesScreen
import m.eight.evaluator.presentation.ui.theme.EvaluatorTheme
import m.eight.evaluator.presentation.util.Screens

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvaluatorTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screens.CategoriesScreen.route) {

                    composable(route = Screens.CategoriesScreen.route) { CategoriesScreen(navController = navController) }
                    composable(
                        route = Screens.EntitiesScreen.route + "/{categoryUUID}?categoryName={categoryName}",
                        arguments = listOf(
                            navArgument(name = "categoryUUID") { type = NavType.StringType },
                            navArgument(name = "categoryName") { type = NavType.StringType }
                        )
                    ) { EntitiesScreen(navController = navController) }
                    composable(
                        route = Screens.PropertiesScreen.route + "/{categoryUUID}/{entityUUID}?entityName={entityName}",
                        arguments = listOf(
                            navArgument(name = "categoryUUID") { type = NavType.StringType },
                            navArgument(name = "entityUUID") { type = NavType.StringType },
                            navArgument(name = "entityName") { type = NavType.StringType }
                        )
                    ) { PropertiesScreen(navController = navController) }
                }
            }
        }
    }
}