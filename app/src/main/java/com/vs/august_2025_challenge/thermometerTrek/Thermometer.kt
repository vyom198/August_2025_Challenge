package com.vs.august_2025_challenge.thermometerTrek

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ThermometerRoot(
    viewModel: ThermometerViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ThermometerScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ThermometerScreen(
    state: ThermometerState,
    onAction: (ThermometerAction) -> Unit,
) {

}

//@Preview
//@Composable
//private fun Preview() {
//    August_2025_ChallengeTheme {
//        ThermometerScreen(
//            state = ThermometerState(),
//            onAction = {}
//        )
//    }
//}