package m.eight.evaluator.presentation.util

sealed class Screens(val route: String) {

    data object CategoriesScreen : Screens("categories")
    data object EntitiesScreen : Screens("entities")
    data object PropertiesScreen : Screens("properties")
}