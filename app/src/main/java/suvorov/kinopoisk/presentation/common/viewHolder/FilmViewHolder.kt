package suvorov.kinopoisk.presentation.common.viewHolder

import suvorov.kinopoisk.databinding.ItemFilmsListBinding
import suvorov.kinopoisk.domain.entity.Film
import suvorov.kinopoisk.presentation.common.clickListener.OnFilmClickListener
import suvorov.kinopoisk.util.setImage

class FilmViewHolder(
    private val binding: ItemFilmsListBinding,
    private val onFilmClick: OnFilmClickListener
): BaseViewHolder<Film>(binding) {

    override fun bind(item: Film) {
        binding.apply {
            posterImageView.setImage(item.imageUrl ?: "")
            nameTextView.text = item.localizedName

            posterImageView.setOnClickListener {
                onFilmClick.onFilmClick(item)
            }
        }
    }
}