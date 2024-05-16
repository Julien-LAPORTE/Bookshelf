package fr.samneo.bookshelf

import android.app.Application
import fr.samneo.bookshelf.di.AppContainer
import fr.samneo.bookshelf.di.DefaultAppContainer

class BookshelfApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}