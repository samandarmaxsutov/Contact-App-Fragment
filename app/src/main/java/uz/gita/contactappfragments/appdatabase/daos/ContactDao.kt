package uz.gita.contactappfragments.appdatabase.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import uz.gita.contactappfragments.appdatabase.entities.ContactEntity


@Dao
interface ContactDao : BaseDao<ContactEntity> {

    @Query("SELECT * FROM CONTACTENTITY")
    fun getAllContact(): LiveData<List<ContactEntity>>
}