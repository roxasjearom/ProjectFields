package com.roxasjearom.projectfields.presentation.contactreference

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(viewModel: ContactReferenceViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ContactDetailsList(
            contactList = viewModel.contactDetails,
            onFirstNameChanged = { contact, firstName ->
                viewModel.updateFirstName(contact, firstName)
            },
            onLastNameChanged = { contact, lastName ->
                viewModel.updateLastName(contact, lastName)
            },
            onMobileNumberChanged = { contact, mobileNumber ->
                viewModel.updateMobileNumber(contact, mobileNumber)
            },
            onSelectedOptionChanged = { contact, relationship ->
                viewModel.updateRelationship(contact, relationship)
            },
            onCloseClicked = { viewModel.removeContact(it) },
        )

        Spacer(modifier = Modifier.weight(1f))

        if (viewModel.contactDetails.size < 5) {
            Button(
                onClick = { viewModel.addContact() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Add Contact")
            }
        }
    }
}
