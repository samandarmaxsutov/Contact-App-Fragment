package uz.gita.contactappfragments.appdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.contactappfragments.appdatabase.daos.ContactDao
import uz.gita.contactappfragments.appdatabase.daos.PhoneNumberDao
import uz.gita.contactappfragments.appdatabase.entities.ContactEntity
import uz.gita.contactappfragments.appdatabase.entities.PhoneNumberEntity

@Database(entities = [ContactEntity::class, PhoneNumberEntity::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    abstract fun phoneNumberDao(): PhoneNumberDao

    companion object {
        private var instance: ContactDatabase? = null

        fun init(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, ContactDatabase::class.java, "appdatabse")
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getDatabase() = instance!!
    }
}