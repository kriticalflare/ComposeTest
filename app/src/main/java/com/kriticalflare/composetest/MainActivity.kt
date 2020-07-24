package com.kriticalflare.composetest

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.livedata.observeAsState
import androidx.ui.material.*
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.kriticalflare.composetest.data.*
import com.kriticalflare.composetest.ui.ComposeTestTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<PlaceholderViewModel>()
        setContent {
            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        scaffoldState = ScaffoldState(),
                        topBar = {
                            TopAppBar(title = { Text("Compose test") })
                        }
                    ) {
                        PlaceholderList(viewModel.usersLiveData)
                    }
                }
            }
        }
    }
}

@Composable
fun PlaceholderList(userLiveData: LiveData<List<UserItem>>) {
    val users by userLiveData.observeAsState(initial = emptyList())
    if (users.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
            CircularProgressIndicator()
        }
    } else {
        UserList(userList = users)
    }
}

@Composable
fun UserList(userList: List<UserItem>) {
    LazyColumnItems(items = userList) { user ->
        UserCard(user = user)
    }
}

@Composable
fun UserCard(user: UserItem) {
    var dialogState by state { false }
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        ListItem(text = {
            Text(text = user.name, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif)
        }, secondaryText = {
            Text(text = user.email, style = TextStyle(fontWeight = FontWeight.Light,  fontFamily = FontFamily.SansSerif))
        }, onClick = {
            dialogState = true
        }
        )
    }

    val dismissDialog = { dialogState = false }

    if (dialogState) {
        AlertDialog(
            onCloseRequest = dismissDialog,
            text = { Text(text = user.company.catchPhrase) },
            confirmButton = {
                TextButton(onClick = dismissDialog,) {
                    Text(
                        text = "OK", modifier = Modifier.clickable(onClick = { })
                    )
                }
            })
    }
}

@Preview
@Composable
fun UserListPreview() {
    UserList(userList = userPreviewData)
}

private val userPreviewData = listOf(
    UserItem(
        name = "kriticalflare",
        id = 1,
        phone = "2032290110",
        email = "erjer@gmail.com",
        address = Address(
            city = "Mumbai", zipcode = "400708", geo = Geo(
                "-37.3159",
                "81.1496"
            ),
            street = "Kulas Light",
            suite = "Apt. 556",
        ),
        company = Company(bs = "bullshit", name = "TG", catchPhrase = "Just do it"),
        username = "name@username",
        website = "www.google.com"
    )
)