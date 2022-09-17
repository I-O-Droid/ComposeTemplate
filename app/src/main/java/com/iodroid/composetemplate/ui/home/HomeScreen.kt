package com.iodroid.composetemplate.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iodroid.composetemplate.ui.CoffeeViewModel
import com.iodroid.composetemplate.ui.destinations.ResultScreenDestination
import com.iodroid.composetemplate.ui.mainActivity
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: CoffeeViewModel = hiltViewModel(mainActivity())
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Button(onClick = {
            viewModel.getRemoteHotCoffee()
            navigator.navigate(ResultScreenDestination())
        }) {
            Text(text = "Hot")
        }
        Button(onClick = {
            viewModel.getRemoteIcedCoffee()
            navigator.navigate(ResultScreenDestination())
        }) {
            Text(text = "Cold")
        }
    }
}