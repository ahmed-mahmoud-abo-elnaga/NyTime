package com.nytimes.example.persentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nytimes.example.persentation.screens.mostpopular.MostPopularScreen
import com.nytimes.example.persentation.vm.MostPopularViewModel
import com.nytimes.example.theme.MostPopularTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch


@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    @OptIn(InternalCoroutinesApi::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MostPopularTheme {
                val MostPopularViewModel: MostPopularViewModel = hiltViewModel()

                val coroutineScope = rememberCoroutineScope()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier,
                    scaffoldState = scaffoldState // attaching `scaffoldState` to the `Scaffold`
                ) {
                    MostPopularScreen(
                        state = MostPopularViewModel.viewState.value,
                        coroutineScope = coroutineScope,
                        onEventSent = { event -> MostPopularViewModel.setEvent(event) },
                        effectFlow = MostPopularViewModel.effect,
                        onLinkClicked = { linkClickedEffect ->

                            coroutineScope.launch(Dispatchers.Main) {
                                val browserIntent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(linkClickedEffect.url))
                                startActivity(browserIntent)
                            }
                        },
                    )
                }

            }

        }
    }
}
