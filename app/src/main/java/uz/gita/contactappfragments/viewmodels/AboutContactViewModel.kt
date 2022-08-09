package uz.gita.contactappfragments.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.contactappfragments.appdatabase.entities.PhoneNumberEntity

interface AboutContactViewModel {
    val backLiveData: LiveData<Unit>

    var id:Int
    fun back()
    val phoneList :LiveData<List<PhoneNumberEntity>>
    fun insert(data:PhoneNumberEntity)
    fun delete(data: PhoneNumberEntity)
    fun update(data: PhoneNumberEntity)
    fun getAll()


}