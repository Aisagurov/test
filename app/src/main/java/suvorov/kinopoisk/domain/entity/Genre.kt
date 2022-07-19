package suvorov.kinopoisk.domain.entity

data class Genre(
    val title: String?,
    var isSelected: Boolean = false
): DataItem()