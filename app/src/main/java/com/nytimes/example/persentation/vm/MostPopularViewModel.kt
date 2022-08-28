package com.nytimes.example.persentation.vm

import androidx.lifecycle.viewModelScope
import com.nytimes.domain.usecases.GetMostPopular
import com.nytimes.example.persentation.screens.mostpopular.MostPopularContract
import com.nytimes.example.persentation.vm.base.BaseViewModel
import com.nytimes.example.persentation.vm.mapper.MostPopularDomainModelToMostPopularUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MostPopularViewModel @Inject constructor(
    private val getMostPopular: GetMostPopular,
    private val MostPopularDomainModelToMostPopularUiModel: MostPopularDomainModelToMostPopularUiModel,

    ) :
    BaseViewModel<MostPopularContract.Event, MostPopularContract.State, MostPopularContract.Effect>() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }

    init {
        MostPopular()
    }

    override fun setInitialState(): MostPopularContract.State =
        MostPopularContract.State(
            MostPopularUiModels = emptyList(),
            isLoading = true,
            isError = false
        )

    override fun handleEvents(event: MostPopularContract.Event) {
        when (event) {
            is MostPopularContract.Event.ClickMostPopular -> setEffect {
                MostPopularContract.Effect.MostPopularClicked(
                    event.MostPopularUiModel.url
                )
            }

            is MostPopularContract.Event.Filter -> {
                MostPopular(event.orderedChecked)
            }
            is MostPopularContract.Event.RetryAgain -> {
                setState {
                    copy(
                        isLoading = true,
                        isError = false
                    )
                }
                MostPopular(event.orderedChecked)
            }
        }
    }

    fun MostPopular(ascendantOrder: Boolean = true) {
        viewModelScope.launch(errorHandler) {
            try {
                getMostPopular.execute(ascendantOrder)
                    .catch { throwable ->
                        handleExceptions(throwable)
                    }
                    .collect { watchersDomainModel ->
                        MostPopularDomainModelToMostPopularUiModel.toUiModel(watchersDomainModel)
                            .let { watchers ->
                                setState {
                                    copy(
                                        MostPopularUiModels = watchers,
                                        isLoading = false
                                    )
                                }
                            }
                    }
            } catch (throwable: Throwable) {
                handleExceptions(throwable)
            }
        }
    }

    private fun handleExceptions(throwable: Throwable) {
        Timber.e(throwable)
        setState {
            copy(
                isLoading = false,
                isError = true
            )
        }
    }
}
