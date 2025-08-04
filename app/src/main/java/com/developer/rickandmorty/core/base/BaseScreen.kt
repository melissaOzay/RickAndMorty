package com.developer.rickandmorty.core.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BaseScreen(
    viewModel: BaseViewModel,
    content : @Composable () -> Unit
){
    val isLoading = viewModel.loadingState.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        content()

        if(isLoading){
            CustomLoadingDialog()
        }
    }

}
@Composable
fun CustomLoadingDialog() {
    CircularProgressIndicator()
}