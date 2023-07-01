package com.project.aimers.StackChat.view.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.project.aimers.StackChat.Constants
import com.project.aimers.StackChat.nav.Action
import com.project.aimers.StackChat.view.Appbar
import com.project.aimers.StackChat.view.SingleMessage

/**
 * The home view which will contain all the code related to the view for HOME.
 *
 * Here we will show the list of chat messages sent by user.
 * And also give an option to send a message and logout.
 */

@Composable
fun HomeView() {

    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        topBar = { AppBar(back = actions.authenticationOption) },
        content = { h -> MainContent(h) },
        bottomBar = { TextBar() },
        scaffoldState = scaffoldState
    )
}

@Composable
fun MainContent(h: PaddingValues, homeViewModel: HomeViewModel = viewModel()) {

    val messages: List<Map<String, Any>> by homeViewModel.messages.observeAsState(
        initial = emptyList<Map<String, Any>>().toMutableList()
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        reverseLayout = true
    ) {
        items(messages) { message ->
            val isCurrentUser = message[Constants.IS_CURRENT_USER] as Boolean

            SingleMessage(
                message = message[Constants.MESSAGE].toString(),
                isCurrentUser = isCurrentUser
            )
        }
    }
}

@Composable
fun TextBar(homeViewModel: HomeViewModel = viewModel()) {

    val message: String by homeViewModel.message.observeAsState(initial = "")

    OutlinedTextField(
        value = message,
        onValueChange = {
            homeViewModel.updateMessage(it)
        },
        label = {
            Text(
                "Type Your Message"
            )
        },
        maxLines = 1,
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 1.dp)
            .fillMaxWidth()
            .height(70.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        textStyle = TextStyle(color = Color.White),
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = {
                    homeViewModel.addMessage()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send Button"
                )
            }
        }
    )
}

@Composable
fun AppBar(back: () -> Unit) {
    Appbar(
        title = " ",
        action = back
    )
}
