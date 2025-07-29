package com.benhurqs.test.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benhurqs.test.viewmodel.CarViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.benhurqs.test.data.UserData
import com.benhurqs.test.mvi.CarAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormDialog(
    viewModel: CarViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.showDialog) {
        DialogContent(
            onDismiss = { viewModel.dispatch(CarAction.OnCloseDialog) },
            onSave = { name, email ->
                viewModel.dispatch(CarAction.OnSaveUser(UserData(name, email)))
                viewModel.dispatch(CarAction.OnCloseDialog)
            }
        )
    }
}

@Composable
fun DialogContent(
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {

                Text(
                    text = "Dados Para Compra",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )


                TextFieldWithTitle(
                    title = "Nome",
                    value = name,
                    onValueChange = { name = it }
                )

                Spacer(modifier = Modifier.height(24.dp))


                TextFieldWithTitle(
                    title = "Email",
                    value = email,
                    onValueChange = { email = it }
                )

                Spacer(modifier = Modifier.height(10.dp))


                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onSave(name, email)
                    }
                ) {
                    Text(text = "Salvar", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun TextFieldWithTitle(
    title: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        androidx.compose.material3.TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(apiLevel = 34)
@Composable
fun PreviewDialogContent() {
    DialogContent(
        onDismiss = { println("Dialog fechado") },
        onSave = { name, email ->
            println("Salvo: Nome = $name, Email = $email")
        }
    )
}
