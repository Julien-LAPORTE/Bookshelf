package fr.samneo.bookshelf.data

import fr.samneo.bookshelf.api.ApiService
import fr.samneo.bookshelf.model.BookResponse

interface ScienceFictionBooksRepository {
    suspend fun getBooks(): BookResponse
}

class NetworkScienceFictionBooksRepository(private val apiService: ApiService) :
    ScienceFictionBooksRepository {
    override suspend fun getBooks(): BookResponse = apiService.getScienceFictionBooks()
}