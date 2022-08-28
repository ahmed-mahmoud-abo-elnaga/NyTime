package com.nytimes.example.persentation.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.nytimes.example.R
import com.nytimes.example.persentation.screens.mostpopular.MostPopularContract
import com.nytimes.example.theme.AppTypography
import com.nytimes.example.theme.Dark
import com.nytimes.example.theme.Light


@Composable
fun ErrorAnimation(
    errorComposition: LottieComposition?,
    errorProgress: Float,
    onEventSent: (event: MostPopularContract.Event) -> Unit,
    orderChecked: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.most_populars_error_occurred),
            style = AppTypography.h3,
            modifier = modifier
                .padding(top = 40.dp),
            color = if (MaterialTheme.colors.isLight) Light.Accent
            else Dark.Accent
        )
        LottieAnimation(
            composition = errorComposition,
            progress = errorProgress,
            alignment = Alignment.BottomCenter,
            modifier = modifier.semantics { contentDescription = "404 Animation" }
        )

        Button(
            modifier = Modifier
                .semantics { contentDescription = "Retry Button" }
                .padding(top = 50.dp),
            onClick = {
                onEventSent(MostPopularContract.Event.RetryAgain(orderChecked.value))
            }
        ) {
            Text(
                stringResource(id = R.string.retry_again),
                style = AppTypography.h4,
                color = if (MaterialTheme.colors.isLight) Light.Accent else Dark.Accent
            )
        }

    }
}
