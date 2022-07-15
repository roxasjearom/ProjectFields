package com.roxasjearom.projectfields.presentation.contactreference

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roxasjearom.projectfields.ui.theme.ProjectFieldsTheme

@Composable
fun ContactDetailsList(
    contactList: List<ContactDetails>,
    onSelectedOptionChanged: (ContactDetails, String) -> Unit,
    onFirstNameChanged: (ContactDetails, String) -> Unit,
    onLastNameChanged: (ContactDetails, String) -> Unit,
    onMobileNumberChanged: (ContactDetails, String) -> Unit,
    onCloseClicked: (ContactDetails) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        for (contact in contactList) {
            FormSection(
                contactDetails = contact,
                onSelectedOptionChanged = { onSelectedOptionChanged(contact, it) },
                onFirstNameChanged = { onFirstNameChanged(contact, it) },
                onLastNameChanged = { onLastNameChanged(contact, it) },
                onMobileNumberChanged = { onMobileNumberChanged(contact, it) },
                onCloseClicked = { onCloseClicked(contact) }
            )
        }
    }
}

@Composable
fun FormSection(
    contactDetails: ContactDetails,
    onSelectedOptionChanged: (String) -> Unit,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onMobileNumberChanged: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = contactDetails.headerTitle,
                style = MaterialTheme.typography.h6
            )
            if (contactDetails.id != 0) {
                IconButton(
                    onClick = onCloseClicked,
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Close Button")
                }
            }
        }


        DropdownField(
            label = "Relationship",
            list = listOf("Father", "Mother", "Brother", "Sister", "Friend"),
            modifier = Modifier.fillMaxWidth(),
            selectedOptionText = contactDetails.relationShip.value,
            onSelectedOptionChanged = onSelectedOptionChanged,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextInputField(
                label = "First Name",
                modifier = Modifier.weight(1f),
                text = contactDetails.firstName.value,
                onTextChanged = onFirstNameChanged,
                hint = "Juan",
            )
            TextInputField(
                label = "Last Name",
                modifier = Modifier.weight(1f),
                text = contactDetails.lastName.value,
                onTextChanged = onLastNameChanged,
                hint = "Dela Cruz",
            )
        }
        TextInputField(
            label = "Mobile Number",
            modifier = Modifier.fillMaxWidth(),
            text = contactDetails.mobileNumber.value,
            onTextChanged = onMobileNumberChanged,
            hint = "0945 123 4567",
        )
    }
}

@Composable
fun TextInputField(
    label: String,
    modifier: Modifier = Modifier,
    hint: String = label,
    text: String,
    onTextChanged: (String) -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onTextChanged(it) },
        label = { Text(label) },
        placeholder = { Text(hint) },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownField(
    label: String,
    list: List<String>,
    selectedOptionText: String,
    onSelectedOptionChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = modifier.fillMaxWidth(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            list.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        onSelectedOptionChanged(selectionOption)
                        expanded = false
                    },
                ) {
                    Text(
                        text = selectionOption,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun DefaultPreview() {
    ProjectFieldsTheme {
        TextInputField(
            label = "First Name",
            text = "Jamal",
            onTextChanged = {},
            hint = "Juan",
        )
    }
}
