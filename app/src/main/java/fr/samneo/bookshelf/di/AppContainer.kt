package fr.samneo.bookshelf.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import fr.samneo.bookshelf.api.ApiService
import fr.samneo.bookshelf.data.NetworkScienceFictionBooksRepository
import fr.samneo.bookshelf.data.ScienceFictionBooksRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val scienceFictionBooksRepository: ScienceFictionBooksRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.googleapis.com/books/v1/"
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()
    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    override val scienceFictionBooksRepository: ScienceFictionBooksRepository by lazy {
        NetworkScienceFictionBooksRepository(retrofitService)
    }

}