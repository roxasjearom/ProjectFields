package com.roxasjearom.projectfields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var _contactDetails = listOf(ContactDetails(0)).toMutableStateList()
    val contactDetails: List<ContactDetails> = _contactDetails

    fun updateFirstName(contact: ContactDetails, firstName: String) {
        contactDetails.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.firstName.value = firstName
        }
    }

    fun updateRelationship(contact: ContactDetails, relationShip: String) {
        contactDetails.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.relationShip.value = relationShip
        }
    }

    fun addContact() {
        _contactDetails.add(ContactDetails(id = _contactDetails.size))
    }

    fun removeContact(selectedContact: ContactDetails) {
        _contactDetails.remove(selectedContact)
    }
}

data class ContactDetails(
    val id: Int,
    val firstName: MutableState<String> = mutableStateOf(""),
    val relationShip: MutableState<String> = mutableStateOf(""),
)
