package com.roxasjearom.projectfields.presentation.contactreference

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class ContactReferenceViewModel : ViewModel() {

    private var _contactReferences = listOf(
        ContactReference(
            id = 0,
            headerTitle = "Primary contact reference",
        )
    ).toMutableStateList()
    val contactReferences: List<ContactReference> = _contactReferences

    fun updateRelationship(contact: ContactReference, relationShip: String) {
        contactReferences.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.relationShip = relationShip
        }
    }

    fun updateFirstName(contact: ContactReference, firstName: String) {
        contactReferences.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.firstName = firstName
        }
    }

    fun updateLastName(contact: ContactReference, lastName: String) {
        contactReferences.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.lastName = lastName
        }
    }

    fun updateMobileNumber(contact: ContactReference, mobileNumber: String) {
        contactReferences.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.mobileNumber = mobileNumber
        }
    }

    fun addContact() {
        _contactReferences.add(
            ContactReference(
                id = _contactReferences.size,
                headerTitle = "Additional contact reference"
            )
        )
    }

    fun removeContact(selectedContact: ContactReference) {
        _contactReferences.remove(selectedContact)
    }
}

data class ContactReference(
    val id: Int,
    val headerTitle: String,
) {
    var relationShip: String by mutableStateOf("")
    var firstName: String by mutableStateOf("")
    var isFirstNameValid: Boolean by mutableStateOf(false)
    var lastName: String by mutableStateOf("")
    var mobileNumber: String by mutableStateOf("")
}
