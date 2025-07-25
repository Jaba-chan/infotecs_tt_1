package ru.evgenykuzakov.users.user_detail_info

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.evgenykuzakov.common.Resource
import ru.evgenykuzakov.common.util.toStringDate
import ru.evgenykuzakov.designsystem.ui.ActionText
import ru.evgenykuzakov.designsystem.ui.BodyLargeText
import ru.evgenykuzakov.designsystem.ui.HeadingCard
import ru.evgenykuzakov.designsystem.ui.LabelSmallText
import ru.evgenykuzakov.designsystem.ui.LoadingScreen
import ru.evgenykuzakov.users.R

@Composable
fun UserDetailInfoScreen(
    paddingValues: PaddingValues,
    viewModel: UserDetailInfoViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    when (val user = state.user) {
        is Resource.Error -> {}
        is Resource.Loading -> {
            LoadingScreen()
        }

        is Resource.Success -> {
            val userData = user.data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 12.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    onClick = onBackClicked
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                BodyLargeText(text = "${stringResource(R.string.id)} ${userData.idCard.name}: ${userData.idCard.value}")
                AsyncImage(
                    model = userData.picture.large,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally)
                )
                LabelSmallText(text = "${userData.name.title} ${userData.name.first} ${userData.name.last}, ${userData.gender}")
                BodyLargeText(
                    text = "${stringResource(R.string.nationality)} ${userData.nat}",
                    textAlign = TextAlign.Center
                )
                HeadingCard(
                    headingText = stringResource(R.string.contacts)
                ) {
                    ActionText(
                        heading = stringResource(R.string.email),
                        underlined = userData.email,
                        onClick = {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:${userData.email}")
                            }
                            context.startActivity(intent)
                        }
                    )
                    ActionText(
                        heading = stringResource(R.string.phone),
                        underlined = userData.phone,
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${userData.phone}")
                            }
                            context.startActivity(intent)
                        }
                    )
                    ActionText(
                        heading = stringResource(R.string.cell),
                        underlined = userData.cell,
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${userData.cell}")
                            }
                            context.startActivity(intent)
                        }
                    )
                }
                Row {
                    HeadingCard(
                        modifier = Modifier.weight(1f),
                        headingText = stringResource(R.string.registered)
                    ) {
                        BodyLargeText(text = "${stringResource(R.string.date)} ${userData.registered.date.toStringDate()}")
                        BodyLargeText(text = "${stringResource(R.string.registered_age)} ${userData.registered.age}")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    HeadingCard(
                        modifier = Modifier.weight(1f),
                        headingText = stringResource(R.string.dob)
                    ) {
                        BodyLargeText(text = "${stringResource(R.string.date)} ${userData.dob.date.toStringDate()}")
                        BodyLargeText(text = "${stringResource(R.string.person_age)} ${userData.dob.age}")
                    }
                }
                HeadingCard(
                    headingText = stringResource(R.string.location)
                ) {
                    BodyLargeText(text = "${stringResource(R.string.country)} ${userData.location.country}, ${userData.location.state}")
                    BodyLargeText(text = "${stringResource(R.string.address)} ${userData.location.street.name}, ${userData.location.street.number}")
                    BodyLargeText(text = "${stringResource(R.string.postcode)} ${userData.location.postcode}")
                    BodyLargeText(text = "${stringResource(R.string.timezone)} ${userData.location.timezone.description}, ${userData.location.timezone.offset}")
                    ActionText(
                        heading = stringResource(R.string.coordinates),
                        underlined = "${userData.location.coordinates.latitude}, ${ userData.location.coordinates.longitude }",
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("geo:${userData.location.coordinates.latitude},${userData.location.coordinates.longitude}" +
                                        "?q=${userData.location.coordinates.latitude},${userData.location.coordinates.longitude}")
                            }
                            context.startActivity(intent)
                        }
                    )
            }
            HeadingCard(
                headingText = stringResource(R.string.login)
            ) {
                BodyLargeText(text = "${stringResource(R.string.username)} ${userData.login.username}")
                BodyLargeText(text = "${stringResource(R.string.password)} ${userData.login.password}")
                BodyLargeText(text = "${stringResource(R.string.uuid)} ${userData.login.uuid}")
            }
        }
    }
}
}
