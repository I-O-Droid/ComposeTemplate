package com.iodroid.composetemplate.ui.result

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.iodroid.composetemplate.ui.CoffeeViewModel
import com.iodroid.composetemplate.ui.mainActivity
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ResultScreen(
    navigator: DestinationsNavigator,
    viewModel: CoffeeViewModel = hiltViewModel(mainActivity())
) {
    val items = viewModel.remoteCoffeeStateFlow.collectAsState()
    Text(text = "We found ${items.value} recipes.")
}