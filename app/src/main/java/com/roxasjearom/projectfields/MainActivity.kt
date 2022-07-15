package com.roxasjearom.projectfields

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.roxasjearom.projectfields.presentation.contactreference.MainScreen
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
