package fr.samneo.bookshelf.api

import fr.samneo.bookshelf.model.BookResponse
import retrofit2.http.GET

interface ApiService {
    @GET("volumes?q=science+fiction&maxResults=40")
    suspend fun getScienceFictionBooks(): BookResponse
}