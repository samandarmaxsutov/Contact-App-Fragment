package uz.gita.contactappfragments.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.contactappfragments.appdatabase.entities.ContactEntity
import uz.gita.contactappfragments.appdatabase.entities.PhoneNumberEntity

interface ContactViewModel {
    val contactsLiveData: LiveData<List<ContactEntity>>
    val openDetailLiveData: LiveData<ContactEntity>


    fun delete(data: List<ContactEntity>)
    fun addContact(contactEntity: ContactEntity)

    fun openDetail(contactEntity: ContactEntity)
}