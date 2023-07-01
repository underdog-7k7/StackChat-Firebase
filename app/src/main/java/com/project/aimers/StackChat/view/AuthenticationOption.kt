package com.project.aimers.StackChat.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.aimers.StackChat.ui.theme.StackChatTheme
import com.project.aimers.StackChat.R
import kotlinx.coroutines.delay

/**
 * The authentication view which will give the user an option to choose between
 * login and register.
 */

@Composable
fun AuthenticationView(register: () -> Unit, login: () -> Unit) {
    StackChatTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val imageList = listOf(
                    R.drawable.sage,
                    R.drawable.reyna,
                    R.drawable.kj,
                    R.drawable.cypher,
                    R.drawable.jett
                )
                var imageIndex by remember { mutableStateOf(0) }

                val backgroundImage = painterResource(imageList[imageIndex])
                val imageAlpha = 0.3f // Set the opacity of the image (0.0f - fully transparent, 1.0f - fully opaque)

                Image(
                    painter = backgroundImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    alpha = imageAlpha
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Transparent)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Title(title = "StackChat")
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround){

    val ischecked = false
    Buttons(
        title = "Register",
        onClick = register,
        backgroundColor = Color.Magenta,
        enabled = ischecked
    )
        Spacer(modifier = Modifier.width(5.dp))

        Buttons(
        title = "Login",
        onClick = login,
        backgroundColor = Color.Cyan,
        enabled = ischecked
    )
}
 //                   Spacer(modifier = Modifier.height(100.dp))
//                    Text(text = "Developed By", color = Color.Black)
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Text(text = "• Animesh Pandey 20BCT0114", color = Color.Black)
//                    Text(text = "• Divyansh Sharma 20BCT0138", color = Color.Black)
//                    Text(text = "• Om Dhapodkar 20BCT0142", color = Color.Black)
//                    Text(text = "• Vipin Bhati 20BAI1252", color = Color.Black)
                }

                LaunchedEffect(Unit) {
                    while (true) {
                        delay(2500)
                        imageIndex = (imageIndex + 1) % imageList.size
                    }
                }
            }
        }
    }
}



