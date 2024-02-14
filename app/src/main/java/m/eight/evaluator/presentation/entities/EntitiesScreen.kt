package m.eight.evaluator.presentation.entities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import m.eight.evaluator.presentation.entities.components.AddOrUpdateEntityFormDialog
import m.eight.evaluator.presentation.entities.components.EntityListItem
import m.eight.evaluator.presentation.shared.ItemEvent
import m.eight.evaluator.presentation.shared.components.DeleteConfirmationFormDialog
import m.eight.evaluator.presentation.util.Screens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EntitiesScreen(navController: NavController, viewModel: EntitiesViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = state.categoryName + " entities") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(ItemEvent.ChangeAddOrUpdateFormDialogVisibility(title = "Add new entity")) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
            if (state.items.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = lazyListState,
                    contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 88.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items = state.items, key = { it.uuid }) { entity ->
                        EntityListItem(
                            modifier = Modifier.combinedClickable(
                                onLongClick = {
                                    viewModel.onEvent(
                                        ItemEvent.ChangeDeleteConfirmationFormDialogVisibility(
                                            item = entity,
                                            promptMessage = "Are you sure to delete ${entity.name} entity?"
                                        )
                                    )
                                },
                                onClick = {
                                    navController.navigate(Screens.PropertiesScreen.route + "/${entity.categoryUUID}/${entity.uuid}?entityName=${entity.name}")
                                }
                            ),
                            onEditIconClicked = {
                                viewModel.onEvent(
                                    ItemEvent.ChangeAddOrUpdateFormDialogVisibility(
                                        title = "Edit ${entity.name} entity",
                                        item = entity
                                    )
                                )
                            },
                            entity = entity
                        )
                    }
                }
            } else Text(text = "Empty list", style = MaterialTheme.typography.bodyLarge)
            if (state.addOrUpdateFromDialogVisibility)
                AddOrUpdateEntityFormDialog(
                    onSaveButtonClicked = { viewModel.onEvent(ItemEvent.OnSaveButtonClicked(it)) },
                    onDismissedDialog = { viewModel.onEvent(ItemEvent.ChangeAddOrUpdateFormDialogVisibility()) },
                    state = state
                )
            if (state.deleteConfirmationFormDialogVisibility)
                state.tempItem?.let {
                    DeleteConfirmationFormDialog(
                        onDeleteButtonClicked = { viewModel.onEvent(ItemEvent.OnDeleteButtonClicked(it)) },
                        onCancelButtonClicked = { viewModel.onEvent(ItemEvent.ChangeDeleteConfirmationFormDialogVisibility()) },
                        message = state.deleteConfirmationFormDialogMessage
                    )
                }
        }
    }
}