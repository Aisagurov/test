package suvorov.kinopoisk.presentation.common.viewHolder

import suvorov.kinopoisk.R
import suvorov.kinopoisk.databinding.ItemGenresListBinding
import suvorov.kinopoisk.domain.entity.Genre
import suvorov.kinopoisk.presentation.common.clickListener.OnGenreClickListener

class GenreViewHolder(
    private val binding: ItemGenresListBinding,
    private val onGenreClick: OnGenreClickListener
): BaseViewHolder<Genre>(binding) {

    override fun bind(item: Genre) {
        binding.apply {
            genreTextView.text = item.title

            genreCardView.setOnClickListener {
                onGenreClick.onGenreClick(item)
            }

            when {
                item.isSelected -> genreCardView.setBackgroundResource(R.drawable.button_box_changed)
                else -> genreCardView.setBackgroundResource(R.drawable.button_box)
            }
        }
    }
}