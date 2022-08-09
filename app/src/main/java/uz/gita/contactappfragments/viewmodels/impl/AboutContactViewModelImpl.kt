package uz.gita.contactappfragments.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.contactappfragments.appdatabase.entities.PhoneNumberEntity
import uz.gita.contactappfragments.repository.impl.ContactRepositoryImpl
import uz.gita.contactappfragments.viewmodels.AboutContactViewModel

class AboutContactViewModelImpl() :AboutContactViewModel, ViewModel() {

    private val repository = ContactRepositoryImpl.getRepository()
    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override var id: Int =0
    override fun back() {
        backLiveData.value = Unit
    }

    override val phoneList: MutableLiveData<List<PhoneNumberEntity>> = MutableLiveData()


    override fun insert(data: PhoneNumberEntity) {
       repository.addAboutContact(data)
       getAll()
    }

    override fun delete(data: PhoneNumberEntity) {
       repository.delete(data)
        getAll()
    }

    override fun update(data: PhoneNumberEntity) {
       repository.update(data)
        getAll()
    }

    override fun getAll() {
        phoneList.value=repository.getAboutContacts(id)
    }

}