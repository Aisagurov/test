package suvorov.kinopoisk.presentation.ui.main.list

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import suvorov.kinopoisk.domain.common.Result
import suvorov.kinopoisk.domain.entity.DataItem
import suvorov.kinopoisk.domain.entity.Film
import suvorov.kinopoisk.domain.entity.Genre
import suvorov.kinopoisk.domain.repository.FilmsRepository
import suvorov.kinopoisk.util.DataItemCreateHelper
import javax.inject.Inject

@InjectViewState
class ListPresenter @Inject constructor(
    private val repository: FilmsRepository
    ): MvpPresenter<ListView>() {

    private var dataItem = listOf<DataItem>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFilms()
    }

    fun getFilms() {
        viewState.showErrorMessage(false)
        viewState.showProgress(true)
        CoroutineScope(Dispatchers.Main).launch {
            when (val result = repository.getFilms()) {
                is Result.Success -> {
                    val films = result.data.sortedBy { it.localizedName }
                    dataItem = DataItemCreateHelper.createDataItem(films)
                    viewState.showDataItem(dataItem)
                    viewState.showProgress(false)
                    Result.Success(true)
                }
                is Result.Error -> {
                    Result.Error(result.message)
                    viewState.showProgress(false)
                    viewState.showErrorMessage(true)
                }
            }
        }
    }

    fun sortDataItem(genre: Genre) {
        val sortedDataItem = arrayListOf<DataItem>().apply {
            dataItem.forEach { item ->
                when (item) {
                    is Genre -> {
                        item.isSelected = item == genre
                        add(item)
                    }
                    is Film -> {
                        if (item.genres?.contains(genre.title) == true)
                            add(item)
                    }
                    else -> add(item)
                }
            }
        }
        viewState.showDataItem(sortedDataItem)
    }
}