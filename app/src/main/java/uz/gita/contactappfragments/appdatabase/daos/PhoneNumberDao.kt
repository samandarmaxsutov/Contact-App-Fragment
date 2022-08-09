package uz.gita.contactappfragments.appdatabase.daos

import androidx.room.Dao
import androidx.room.Query
import uz.gita.contactappfragments.appdatabase.entities.PhoneNumberEntity


@Dao
interface PhoneNumberDao : BaseDao<PhoneNumberEntity> {
    @Query ("select * from PhoneNumberEntity where :id=contact_id" )
    fun getAll(id:Int):List<PhoneNumberEntity>


}