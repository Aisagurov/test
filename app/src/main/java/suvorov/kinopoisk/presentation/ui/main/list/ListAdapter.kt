package suvorov.kinopoisk.presentation.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import suvorov.kinopoisk.databinding.ItemFilmsListBinding
import suvorov.kinopoisk.databinding.ItemGenresListBinding
import suvorov.kinopoisk.databinding.ItemHeadersListBinding
import suvorov.kinopoisk.domain.entity.DataItem
import suvorov.kinopoisk.domain.entity.Film
import suvorov.kinopoisk.domain.entity.Genre
import suvorov.kinopoisk.domain.entity.Header
import suvorov.kinopoisk.presentation.common.clickListener.OnFilmClickListener
import suvorov.kinopoisk.presentation.common.clickListener.OnGenreClickListener
import suvorov.kinopoisk.presentation.common.viewHolder.BaseViewHolder
import suvorov.kinopoisk.presentation.common.viewHolder.FilmViewHolder
import suvorov.kinopoisk.presentation.common.viewHolder.GenreViewHolder
import suvorov.kinopoisk.presentation.common.viewHolder.HeaderViewHolder
import suvorov.kinopoisk.util.Constants.ITEM_VIEW_TYPE_FILM
import suvorov.kinopoisk.util.Constants.ITEM_VIEW_TYPE_GENRE
import suvorov.kinopoisk.util.Constants.ITEM_VIEW_TYPE_HEADER

class ListAdapter(
    private val onFilmClick: OnFilmClickListener,
    private val onGenreClick: OnGenreClickListener
    ): RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val dataItem = arrayListOf<DataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder(ItemHeadersListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false))

            ITEM_VIEW_TYPE_GENRE -> GenreViewHolder(ItemGenresListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false), onGenreClick)

            ITEM_VIEW_TYPE_FILM -> FilmViewHolder(ItemFilmsListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false), onFilmClick)

            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = dataItem[position]
        when(holder) {
            is HeaderViewHolder -> {
                val nameItem = item as Header
                holder.bind(nameItem)
            }
            is GenreViewHolder -> {
                val genreItem = item as Genre
                holder.bind(genreItem)
            }
            is FilmViewHolder -> {
                val filmItem = item as Film
                holder.bind(filmItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataItem[position]) {
            is Header -> ITEM_VIEW_TYPE_HEADER
            is Genre -> ITEM_VIEW_TYPE_GENRE
            is Film -> ITEM_VIEW_TYPE_FILM
            else -> throw IllegalArgumentException("Unknown type of item $position")
        }
    }

    override fun getItemCount(): Int {
        return dataItem.size
    }

    fun updateList(list: List<DataItem>) {
        dataItem.clear()
        dataItem.addAll(list)
        notifyDataSetChanged()
    }
}