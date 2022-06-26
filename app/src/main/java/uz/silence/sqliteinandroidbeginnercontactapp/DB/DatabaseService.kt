package uz.silence.sqliteinandroidbeginnercontactapp.DB

import uz.silence.sqliteinandroidbeginnercontactapp.CLASS.Contact

interface DatabaseService {

    fun addContact(contact: Contact)

    fun deleteContact(contact: Contact)

    fun getAllContacts():ArrayList<Contact>

    fun updateContact(contact: Contact):Int

    fun getContactById(id:Int):Contact

}