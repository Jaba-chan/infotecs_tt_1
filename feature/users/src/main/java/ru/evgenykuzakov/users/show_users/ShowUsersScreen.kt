package ru.evgenykuzakov.users.show_users

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.evgenykuzakov.common.Resource
import ru.evgenykuzakov.designsystem.ui.ErrorScreen
import ru.evgenykuzakov.designsystem.ui.LabelSmallText
import ru.evgenykuzakov.designsystem.ui.LoadingScreen
import ru.evgenykuzakov.users.R
import ru.evgenykuzakov.users.show_users.placeholder.StyledRow

@Composable
fun ShowUsersScreen(
    viewModel: ShowUsersViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onUserClick: (Long) -> Unit,
    onRefreshClick: (() -> Unit) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { granted ->
                if (granted)
                    sendNotification(context,  (uiState.users as? Resource.Error)?.message ?: "")
            }
        )

    LaunchedEffect(Unit) {
        onRefreshClick { viewModel.refreshUsers() }
    }

    LaunchedEffect(uiState.users) {
        createNotificationChannel(context)
        val users = uiState.users
        if (users is Resource.Error) {
            val msg = users.message ?: ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            } else {
                sendNotification(context, msg)
            }
        }
    }

    when (val users = uiState.users) {
        is Resource.Error -> {
            val msg = users.message ?: ""
            ErrorScreen(errorText = msg)
        }

        is Resource.Loading -> {
            LoadingScreen()
        }

        is Resource.Success ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(items = users.data) { user ->
                    Card(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onClick = { onUserClick(user.id) },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = user.picture.large,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(120.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                LabelSmallText(
                                    text = "${user.name.first} ${user.name.last}",
                                )
                                StyledRow(
                                    text = user.phone,
                                    iconResId = R.drawable.ic_call,
                                )
                                StyledRow(
                                    text = "${user.location.city}, ${user.location.street.name}, ${user.location.street.number}",
                                    iconResId = R.drawable.ic_home,
                                )
                            }
                        }
                    }
                }
            }
    }

}

const val CHANNEL_MSG_ID = "error_msg"
const val NOTIFICATION_ID = 1

private fun createNotificationChannel(
    context: Context,
) {
    val channel = NotificationChannel(
        CHANNEL_MSG_ID,
        getString(context, R.string.errors_notification_channel_name),
        NotificationManager.IMPORTANCE_DEFAULT
    )
    channel.description = getString(context, R.string.errors_notification_channel_desc)
    with(NotificationManagerCompat.from(context)){
        createNotificationChannel(channel)
    }
}

@SuppressLint("MissingPermission")
private fun sendNotification(context: Context, msg: String) {
    val builder = NotificationCompat.Builder(context, CHANNEL_MSG_ID)
        .setSmallIcon(R.drawable.ic_warning)
        .setContentTitle(getString(context, R.string.error))
        .setContentText(msg)
    val notification = builder.build()
    with(NotificationManagerCompat.from(context)){
        notify(NOTIFICATION_ID, notification)
    }
}


