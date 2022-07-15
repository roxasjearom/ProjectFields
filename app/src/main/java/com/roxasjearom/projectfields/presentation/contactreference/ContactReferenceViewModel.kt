package com.roxasjearom.projectfields.presentation.contactreference

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ContactReferenceViewModel : ViewModel() {

    private var _contactDetails = listOf(
        ContactDetails(
            id = 0,
            headerTitle = "Primary contact reference",
        )
    ).toMutableStateList()
    val contactDetails: List<ContactDetails> = _contactDetails

    fun updateRelationship(contact: ContactDetails, relationShip: String) {
        contactDetails.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.relationShip.value = relationShip
        }
    }

    fun updateFirstName(contact: ContactDetails, firstName: String) {
        contactDetails.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.firstName.value = firstName
        }
    }

    fun updateLastName(contact: ContactDetails, lastName: String) {
        contactDetails.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.lastName.value = lastName
        }
    }

    fun updateMobileNumber(contact: ContactDetails, mobileNumber: String) {
        contactDetails.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.mobileNumber.value = mobileNumber
        }
    }

    fun addContact() {
        _contactDetails.add(
            ContactDetails(
                id = _contactDetails.size,
                headerTitle = "Additional contact reference"
            )
        )
    }

    fun removeContact(selectedContact: ContactDetails) {
        _contactDetails.remove(selectedContact)
    }
}

data class ContactDetails(
    val id: Int,
    val headerTitle: String,
    val relationShip: MutableState<String> = mutableStateOf(""),
    val firstName: MutableState<String> = mutableStateOf(""),
    val isFirstNameValid: MutableState<Boolean> = mutableStateOf(false),
    val lastName: MutableState<String> = mutableStateOf(""),
    val mobileNumber: MutableState<String> = mutableStateOf(""),
)
