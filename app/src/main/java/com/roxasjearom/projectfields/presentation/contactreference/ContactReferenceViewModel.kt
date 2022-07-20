package com.roxasjearom.projectfields.presentation.contactreference

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.roxasjearom.projectfields.presentation.contactreference.RegexConstants.REGEX_MOBILE_NUMBER
import com.roxasjearom.projectfields.presentation.contactreference.RegexConstants.REGEX_TEXT_NAME

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
            contactDetails.isFirstNameValid = regexMatches(firstName, Regex(REGEX_TEXT_NAME))
        }
    }

    fun updateLastName(contact: ContactReference, lastName: String) {
        contactReferences.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.lastName = lastName
            contactDetails.isLastNameValid = regexMatches(lastName, Regex(REGEX_TEXT_NAME))
        }
    }

    fun updateMobileNumber(contact: ContactReference, mobileNumber: String) {
        contactReferences.find { it.id == contact.id }?.let { contactDetails ->
            contactDetails.mobileNumber = mobileNumber
            contactDetails.isMobileNumberValid = regexMatches(
                mobileNumber, Regex(
                    REGEX_MOBILE_NUMBER
                )
            )
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

    private fun regexMatches(text: String, regex: Regex): Boolean {
        return regex.matches(text)
    }
}

data class ContactReference(
    val id: Int,
    val headerTitle: String,
) {
    var relationShip: String by mutableStateOf("")
    var firstName: String by mutableStateOf("")
    var isFirstNameValid: Boolean by mutableStateOf(true)
    var lastName: String by mutableStateOf("")
    var isLastNameValid: Boolean by mutableStateOf(true)
    var mobileNumber: String by mutableStateOf("")
    var isMobileNumberValid: Boolean by mutableStateOf(true)
}
