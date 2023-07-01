package com.project.aimers.StackChat.view.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.aimers.StackChat.view.Appbar
import com.project.aimers.StackChat.view.Buttons
import com.project.aimers.StackChat.view.TextFormField
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.project.aimers.StackChat.R

/**
 * The Register view which will be helpful for the user to register themselves into
 * our database and go to the home screen to see and send messages.
 */

@Composable
fun RegisterView(
    home: () -> Unit,
    back: () -> Unit,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val email: String by registerViewModel.email.observeAsState("")
    val password: String by registerViewModel.password.observeAsState("")
    val loading: Boolean by registerViewModel.loading.observeAsState(initial = false)
    var ischecked by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog1 by remember { mutableStateOf(false) }
    var showToast by remember { mutableStateOf(false) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val backgroundImage = painterResource(R.drawable.valorant)
        val imageAlpha = 0.1f

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = backgroundImage,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                alpha = imageAlpha
            )

            if (loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Appbar(
                    title = "Register",
                    action = back
                )
                TextFormField(
                    value = email,
                    onValueChange = { registerViewModel.updateEmail(it) },
                    label = "Email",
                    keyboardType = KeyboardType.Email,
                    visualTransformation = VisualTransformation.None,
                    textStyle = TextStyle(color = Color.Magenta)
                )
                TextFormField(
                    value = password,
                    onValueChange = { registerViewModel.updatePassword(it) },
                    label = "Password",
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = TextStyle(color = Color.Magenta)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(horizontalArrangement = Arrangement.Start) {
                    Box() {
                        Checkbox(checked = ischecked, onCheckedChange = { ischecked = it })
                    }
                    Text(text = "Click to read Terms & Condition",
                        fontSize = 15.sp,
                        color = Color.Blue,
                        modifier = Modifier.clickable { showDialog = true })

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { },
                            title = {
                                Text(text = "Terms & Conditions")
                            },
                            text = {
                                LazyColumn {
                                    item {
                                        Text(text = "• StackChat is an app which is operated under Companies Act, 2013")
                                    }
                                    item {
                                        Text(text = "\u2022 StackChat takes the private nature of your personal information very seriously.")
                                    }
                                    item {
                                        Text(text = "\u2022 You will not text on the App, or transmit to other users of the App, any defamatory, abusive, profane, offensive, threatening, harassing, rude, racially offensive comments")
                                    }
                                    // Add more bullet points as needed
                                }
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        ischecked = true
                                        showDialog = false
                                    },
                                    content = {
                                        Text(text = "Comply")
                                    }
                                )
                            })
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))


                Buttons(
                    title = "Register",
                    onClick = {
                        if (ischecked && email.isNotBlank() && password.isNotBlank()) {
                            showDialog1 = true
                            registerViewModel.registerUser(home = home)
                        } else {
                            showToast = true
                        }
                    },
                    enabled = ischecked && email.isNotBlank() && password.isNotBlank(),
                    backgroundColor = Color.Blue
                )


                if (showToast) {
                    LaunchedEffect(Unit) {
                        launch {
                            delay(2000)
                            showToast = false
                        }
                    }

                    Snackbar(
                        content = { Text(text = "Check all fields before proceeding") }
                    )
                }


                if (showDialog1) {
                    AlertDialog(
                        onDismissRequest = { },
                        title = {
                            Text(
                                text = "You can now Login and start texting!!",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                            )
                        },

                        confirmButton = {
                            Button(
                                onClick = { showDialog1 = false },
                                content = {
                                    Text(text = "Close")
                                }
                            )
                        }
                    )
                }

            }
        }
    }
}
