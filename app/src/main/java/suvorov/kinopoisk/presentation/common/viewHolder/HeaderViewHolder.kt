package suvorov.kinopoisk.presentation.common.viewHolder

import suvorov.kinopoisk.databinding.ItemHeadersListBinding
import suvorov.kinopoisk.domain.entity.Header

class HeaderViewHolder(
    private val binding: ItemHeadersListBinding
): BaseViewHolder<Header>(binding) {

    override fun bind(item: Header) {
        binding.titleTextView.text = item.title
    }
}