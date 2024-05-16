package fr.samneo.bookshelf.model

interface UiState {
    data class Success(
        val bookResponse: BookResponse, var imgSrc: List<String> = listOf()
    ) : UiState {
        init {
            val src: MutableList<String> = mutableListOf()
            for (book in bookResponse.items.orEmpty()) {
                var url = book.volumeInfo?.imageLinks?.thumbnail.orEmpty()
                    .replace("http://", "https://")
                src.add(url)
            }
            imgSrc = src.toList()
        }
    }

    object Error : UiState
    object Loading : UiState

}