package com.nytimes.example.persentation.screens.mostpopular

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.nytimes.example.R
import com.nytimes.example.persentation.screens.common.ErrorAnimation
import com.nytimes.example.theme.AppTypography
import com.nytimes.example.theme.Dark
import com.nytimes.example.theme.Light
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@InternalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun MostPopularScreen(
    state: MostPopularContract.State,
    coroutineScope: CoroutineScope,
    onEventSent: (event: MostPopularContract.Event) -> Unit,
    effectFlow: Flow<MostPopularContract.Effect>?,
    onLinkClicked: (clickableLinkEffect: MostPopularContract.Effect.MostPopularClicked) -> Unit,
    modifier: Modifier = Modifier
) {
    val loadingComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))
    val loadingProgress by animateLottieCompositionAsState(loadingComposition)

    val errorComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error_conection))
    val errorProgress by animateLottieCompositionAsState(errorComposition)

    val noResultsComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_results_animation))
    val noResultsProgress by animateLottieCompositionAsState(noResultsComposition)

    val bodyComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.rocket))
    val bodyProgress by animateLottieCompositionAsState(
        bodyComposition,
        restartOnPlay = false
    )

    val openDialog = remember { mutableStateOf(false) }
    val orderChecked = remember { mutableStateOf(true) }

    EffectsListener(effectFlow, onLinkClicked)

    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(
                if (MaterialTheme.colors.isLight) Light.Background
                else Dark.Background
            )
    ) {
        Text(
            text = stringResource(id = R.string.most_populars_title),
            modifier = modifier.padding(top = 16.dp, start = 16.dp),
            style = AppTypography.h1,
            color = if (MaterialTheme.colors.isLight) Light.TextColorPrimary
            else Dark.TextColorPrimary
        )
        when {
            state.isLoading -> {
                LottieAnimation(
                    composition = loadingComposition,
                    progress = loadingProgress,
                    modifier = modifier.semantics { contentDescription = "Loading Animation" }
                )
            }
            state.isError -> {
                ErrorAnimation(errorComposition, errorProgress, onEventSent, orderChecked)
            }
            else -> {
                if (bodyProgress == 1f) {
                    FilterIcon(
                        modifier = modifier
                            .padding(end = 8.dp, bottom = 8.dp)
                            .align(Alignment.End),
                        onClick = { openDialog.value = true }
                    )
                    if (state.MostPopularUiModels.isNotEmpty()) {
                        MostPopularList(
                            MostPopular = state.MostPopularUiModels,
                            onEventSent = onEventSent,
                            coroutineScope = coroutineScope
                        )
                    } else {
                        NoResultsFeedback(noResultsComposition, noResultsProgress)
                    }
                } else {
                    LottieAnimation(
                        composition = bodyComposition,
                        progress = bodyProgress,
                        modifier = modifier.semantics { contentDescription = "Watchers Animation" }
                    )
                }
            }
        }
        if (openDialog.value) {
            FilterDialog(
                openDialog = openDialog,
                orderChecked = orderChecked,
                onEventSent = onEventSent
            )
        }
    }
}

@Composable
private fun EffectsListener(
    effectFlow: Flow<MostPopularContract.Effect>?,
    onLinkClicked: (clickableLinkEffect: MostPopularContract.Effect.MostPopularClicked) -> Unit
) {
    LaunchedEffect(MOST_POPULAR_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is MostPopularContract.Effect.MostPopularClicked -> onLinkClicked(effect)
            }
        }?.collect()
    }
}

@Composable
private fun NoResultsFeedback(
    noResultsComposition: LottieComposition?,
    noResultsProgress: Float
) {
    Box {
        Text(
            text = stringResource(id = R.string.most_populars_no_results_found),
            style = AppTypography.h3,
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.TopCenter),
            color = if (MaterialTheme.colors.isLight) Light.Accent
            else Dark.Accent
        )
        LottieAnimation(
            composition = noResultsComposition,
            progress = noResultsProgress,
            alignment = Alignment.Center
        )
    }
}

@Composable
fun FilterDialog(
    openDialog: MutableState<Boolean>,
    orderChecked: MutableState<Boolean>,
    onEventSent: (event: MostPopularContract.Event) -> Unit
) {

    AlertDialog(
        shape = RoundedCornerShape(12.dp),
        backgroundColor = if (MaterialTheme.colors.isLight) Light.DialogWindowBackground else Dark.DialogWindowBackground,
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(
                text = stringResource(id = R.string.dialog_title),
                style = AppTypography.h2,
                color = if (MaterialTheme.colors.isLight) Light.TextColorPrimary else Dark.TextColorPrimary
            )
        },
        text = {
            Row {
                Column {
                    Text(
                        text = stringResource(id = R.string.dialog_order_criteria),
                        style = AppTypography.body1,
                        color = if (MaterialTheme.colors.isLight) Light.TextColorPrimary else Dark.TextColorPrimary
                    )
                    Switch(
                        checked = orderChecked.value,
                        onCheckedChange = { orderChecked.value = it },
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = if (MaterialTheme.colors.isLight) Light.Accent else Dark.Accent,
                            checkedThumbColor = if (MaterialTheme.colors.isLight) Light.Accent else Dark.Accent,
                            uncheckedTrackColor = if (MaterialTheme.colors.isLight) Light.TextColorSecondary else Dark.TextColorSecondary,
                            uncheckedThumbColor = if (MaterialTheme.colors.isLight) Light.TextColorSecondary else Dark.TextColorSecondary
                        )
                    )
                }
            }
        },
        confirmButton = { ConfirmButton(openDialog, onEventSent, orderChecked) },
        dismissButton = { DismissButton(openDialog) }
    )
}

@Composable
private fun DismissButton(openDialog: MutableState<Boolean>) {
    TextButton(
        onClick = {
            openDialog.value = false
        }
    ) {
        Text(
            text = stringResource(id = R.string.dialog_cancel_button),
            style = AppTypography.subtitle2,
            color = if (MaterialTheme.colors.isLight) Light.TextColorSecondary else Dark.TextColorSecondary
        )
    }
}

@Composable
private fun ConfirmButton(
    openDialog: MutableState<Boolean>,
    onEventSent: (event: MostPopularContract.Event) -> Unit,
    orderChecked: MutableState<Boolean>
) {
    TextButton(
        modifier = Modifier.semantics { contentDescription = "OK Dialog Button" },
        onClick = {
            openDialog.value = false
            onEventSent(MostPopularContract.Event.Filter(orderChecked.value))
        }
    ) {
        Text(
            stringResource(id = R.string.dialog_ok_button),
            style = AppTypography.subtitle2,
            color = if (MaterialTheme.colors.isLight) Light.Accent else Dark.Accent
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun MostPopularList(
    MostPopular: List<MostPopularUiModel>,
    onEventSent: (event: MostPopularContract.Event) -> Unit,
    coroutineScope: CoroutineScope
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(MostPopular) { MostPopular ->
            MostPopularItemRow(
                MostPopularUiModelItem = MostPopular,
                onEventSent = onEventSent,
                coroutineScope = coroutineScope
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MostPopularItemRow(
    MostPopularUiModelItem: MostPopularUiModel,
    onEventSent: (event: MostPopularContract.Event) -> Unit,
    coroutineScope: CoroutineScope
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        backgroundColor = if (MaterialTheme.colors.isLight) Light.ItemBackground
        else Dark.ItemBackground,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .semantics { contentDescription = "Item-${MostPopularUiModelItem.title}" }
            .clickable {
                onEventSent(MostPopularContract.Event.ClickMostPopular(MostPopularUiModelItem))
            }
    ) {
        Row(Modifier.animateContentSize()) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 8.dp)
            ) {
                MostPopularItemThumbnail(MostPopularUiModelItem.image)
            }

            MostPopularItemContent(
                uiModelItem = MostPopularUiModelItem,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                    .align(Alignment.CenterVertically)
            )

        }
    }
}

@Composable
private fun FilterIcon(modifier: Modifier, onClick: () -> Unit) {
    Box(modifier) {
        IconButton(
            onClick = onClick
        ) {
            Image(
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = "Filter Button"
            )
        }
    }
}


@Composable
private fun MostPopularItemContent(
    uiModelItem: MostPopularUiModel,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        WatcherItemContentText(uiModelItem.title)
        uiModelItem.subtitle?.let {
        WatcherItemContentSubText(uiModelItem.subtitle)
        }
    }
}

@Composable
fun MostPopularItemThumbnail(thumbnailUrl: String?) {

    val painter = rememberImagePainter(data = thumbnailUrl)
    Image(
        painter = painter,
        contentDescription = "MostPopular item thumbnail picture",
        modifier = Modifier
            .width(110.dp)
            .height(110.dp)

    )
    if (painter.state !is ImagePainter.State.Success) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.size(110.dp)
        )
    }

}


@Composable
private fun WatcherItemContentText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Start,
        style = AppTypography.subtitle2,
        maxLines = 2,
        color = if (MaterialTheme.colors.isLight) Light.TextHighlighted
        else Dark.TextHighlighted
    )
}

@Composable
private fun WatcherItemContentSubText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Start,
        style = AppTypography.caption,
        maxLines = 2,
        color = if (MaterialTheme.colors.isLight) Light.TextColorSecondary
        else Dark.TextColorSecondary
    )
}