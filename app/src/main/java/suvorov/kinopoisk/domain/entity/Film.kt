package suvorov.kinopoisk.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Int?,
    val localizedName: String?,
    val name: String?,
    val year: Int?,
    val rating: Float?,
    val imageUrl: String?,
    val description: String?,
    val genres: List<String>?
): Parcelable, DataItem()