package com.roxasjearom.projectfields

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roxasjearom.projectfields.ui.theme.ProjectFieldsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectFieldsTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ContactDetailsList(
            contactList = viewModel.contactDetails,
            onFirstNameChanged = { contact, text ->
                viewModel.updateFirstName(contact, text)
            },
            onCloseClicked = { viewModel.removeContact(it) }
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

@Composable
fun ContactDetailsList(
    contactList: List<ContactDetails>,
    onFirstNameChanged: (ContactDetails, String) -> Unit,
    onCloseClicked: (ContactDetails) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        for (contact in contactList) {
            FormSection(
                contactDetails = contact,
                onFirstNameChanged = { onFirstNameChanged(contact, it) },
                onCloseClicked = { onCloseClicked(contact) },
            )
        }
    }
}

@Composable
fun FormSection(
    contactDetails: ContactDetails,
    onFirstNameChanged: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (contactDetails.id != 0) {
            IconButton(
                onClick = onCloseClicked,
                modifier = Modifier.align(alignment = Alignment.End)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Close Button")
            }
        }

        DropdownField(
            label = "Relationship",
            list = listOf("Father", "Mother", "Brother", "Sister", "Friend"),
            modifier = Modifier.fillMaxWidth()
        )
        TextInputField(
            label = "First Name",
            modifier = Modifier.fillMaxWidth(),
            text = contactDetails.firstName.value,
            onTextChanged = onFirstNameChanged,
            hint = "Juan",
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
    TextField(
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
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier.fillMaxWidth(),
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
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
                        selectedOptionText = selectionOption
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

@Preview(showBackground = true)
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
