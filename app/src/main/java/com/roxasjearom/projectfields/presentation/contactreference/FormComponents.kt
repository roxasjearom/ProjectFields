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
    contactList: List<ContactReference>,
    onSelectedOptionChanged: (ContactReference, String) -> Unit,
    onFirstNameChanged: (ContactReference, String) -> Unit,
    onLastNameChanged: (ContactReference, String) -> Unit,
    onMobileNumberChanged: (ContactReference, String) -> Unit,
    onCloseClicked: (ContactReference) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        for (contact in contactList) {
            FormSection(
                contactReference = contact,
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
    contactReference: ContactReference,
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
                text = contactReference.headerTitle,
                style = MaterialTheme.typography.h6
            )
            if (contactReference.id != 0) {
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
            selectedOptionText = contactReference.relationShip,
            onSelectedOptionChanged = onSelectedOptionChanged,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextInputField(
                label = "First Name",
                isValid = contactReference.isFirstNameValid,
                modifier = Modifier.weight(1f),
                text = contactReference.firstName,
                onTextChanged = onFirstNameChanged,
                hint = "Juan",
            )
            TextInputField(
                label = "Last Name",
                isValid = contactReference.isLastNameValid,
                modifier = Modifier.weight(1f),
                text = contactReference.lastName,
                onTextChanged = onLastNameChanged,
                hint = "Dela Cruz",
            )
        }
        TextInputField(
            label = "Mobile Number",
            isValid = contactReference.isMobileNumberValid,
            modifier = Modifier.fillMaxWidth(),
            text = contactReference.mobileNumber,
            onTextChanged = onMobileNumberChanged,
            hint = "0945 123 4567",
        )
    }
}

@Composable
fun TextInputField(
    label: String,
    isValid: Boolean,
    modifier: Modifier = Modifier,
    hint: String = label,
    text: String,
    onTextChanged: (String) -> Unit,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = text,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            placeholder = { Text(hint) },
            isError = !isValid,
            singleLine = true,
        )
        if (!isValid) {
            Text(
                text = "Invalid input",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }

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
            isValid = true,
            text = "Jamal",
            onTextChanged = {},
            hint = "Juan",
        )
    }
}
