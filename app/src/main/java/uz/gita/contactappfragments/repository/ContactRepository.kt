package uz.gita.contactappfragments.repository

import androidx.lifecycle.LiveData
import uz.gita.contactappfragments.appdatabase.entities.ContactEntity
import uz.gita.contactappfragments.appdatabase.entities.PhoneNumberEntity


interface ContactRepository {

    fun getContacts():LiveData<List<ContactEntity>>
    fun getAboutContacts(id:Int):List<PhoneNumberEntity>

    fun addContact(contactEntity: ContactEntity)
    fun addAboutContact(phoneNumberEntity: PhoneNumberEntity)



    fun delete(contactEntity: List<ContactEntity>)
    fun delete(phoneNumberEntity: PhoneNumberEntity)

    fun update(contactEntity: ContactEntity)
    fun update(phoneNumberEntity: PhoneNumberEntity)


}