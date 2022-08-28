package com.nytimes.example.persentation.screens.mostpopular

import com.nytimes.example.persentation.vm.base.ViewEvent
import com.nytimes.example.persentation.vm.base.ViewSideEffect
import com.nytimes.example.persentation.vm.base.ViewState


const val MOST_POPULAR_FOR_EFFECTS = "popular-listen-to-effects"

class MostPopularContract {
    sealed class Event : ViewEvent {
        data class ClickMostPopular(val MostPopularUiModel: MostPopularUiModel) : Event()
        data class Filter(val orderedChecked: Boolean) : Event()
        data class RetryAgain(val orderedChecked: Boolean) : Event()
    }

    data class State(
        val MostPopularUiModels: List<MostPopularUiModel>,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data class MostPopularClicked(val url: String?) : Effect()
    }
}
