package uz.gita.contactappfragments.repository.impl

import androidx.lifecycle.LiveData
import uz.gita.contactappfragments.appdatabase.database.ContactDatabase
import uz.gita.contactappfragments.appdatabase.entities.ContactEntity
import uz.gita.contactappfragments.appdatabase.entities.PhoneNumberEntity
import uz.gita.contactappfragments.repository.ContactRepository


class ContactRepositoryImpl private constructor() : ContactRepository {
    private val contactDao = ContactDatabase.getDatabase().contactDao()
    private val phoneNumberDao = ContactDatabase.getDatabase().phoneNumberDao()

    companion object {
        private var contactRepository: ContactRepository? = null

        fun init() {
            if (contactRepository == null) {
                contactRepository = ContactRepositoryImpl()
            }
        }

        fun getRepository() = contactRepository!!
    }

    override fun getContacts(): LiveData<List<ContactEntity>> = contactDao.getAllContact()
    override fun getAboutContacts(id: Int): List<PhoneNumberEntity> {
        return phoneNumberDao.getAll(id)
    }

    override fun addContact(contactEntity: ContactEntity) {
        contactDao.insert(contactEntity)
    }

    override fun addAboutContact(phoneNumberEntity: PhoneNumberEntity) {
        phoneNumberDao.insert(phoneNumberEntity)
    }

    override fun delete(contactEntity: List<ContactEntity>) {
        contactDao.deleteAll(contactEntity)
    }

    override fun delete(phoneNumberEntity: PhoneNumberEntity) {
        phoneNumberDao.delete(phoneNumberEntity)
    }

    override fun update(contactEntity: ContactEntity) {
        contactDao.insert(contactEntity)
    }

    override fun update(phoneNumberEntity: PhoneNumberEntity) {
        phoneNumberDao.insert(phoneNumberEntity)
    }
}