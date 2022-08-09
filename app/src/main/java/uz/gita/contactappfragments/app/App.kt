package uz.gita.contactappfragments.app

import android.app.Application
import uz.gita.contactappfragments.appdatabase.database.ContactDatabase
import uz.gita.contactappfragments.repository.impl.ContactRepositoryImpl

class App:Application() {
    override fun onCreate() {
        super.onCreate()

        ContactDatabase.init(this)
        ContactRepositoryImpl.init()
    }
}