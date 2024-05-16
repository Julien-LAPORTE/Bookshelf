package fr.samneo.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
   val totalItems: Int = 0, val items: List<Book>? = null
)

@Serializable
data class Book(
    val id: String? = null,
    val volumeInfo: VolumeInfo? = null,
)

@Serializable
data class VolumeInfo(
    val imageLinks: ImageLinks? = null,
)

@Serializable
data class ImageLinks(
    val thumbnail: String? = null
)