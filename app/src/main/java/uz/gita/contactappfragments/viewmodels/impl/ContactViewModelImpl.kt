package uz.gita.contactappfragments.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.contactappfragments.appdatabase.entities.ContactEntity
import uz.gita.contactappfragments.repository.impl.ContactRepositoryImpl
import uz.gita.contactappfragments.viewmodels.ContactViewModel

class ContactViewModelImpl:ContactViewModel,ViewModel() {
    private val repository = ContactRepositoryImpl.getRepository()

    override val contactsLiveData: LiveData<List<ContactEntity>> = repository.getContacts()
    override val openDetailLiveData: MutableLiveData<ContactEntity> = MutableLiveData()
    override fun delete(data: List<ContactEntity>) {
        repository.delete(data)
    }

    override fun addContact(contactEntity: ContactEntity) {
        repository.addContact(contactEntity)
    }

    override fun openDetail(contactEntity: ContactEntity) {
        openDetailLiveData.value = contactEntity
    }
}