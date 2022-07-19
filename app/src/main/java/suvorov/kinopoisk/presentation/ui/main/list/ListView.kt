package suvorov.kinopoisk.presentation.ui.main.list

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import suvorov.kinopoisk.domain.entity.DataItem

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ListView: MvpView {
    fun showDataItem(list: List<DataItem>)
    fun showProgress(show: Boolean)
    fun showErrorMessage(show: Boolean)
}