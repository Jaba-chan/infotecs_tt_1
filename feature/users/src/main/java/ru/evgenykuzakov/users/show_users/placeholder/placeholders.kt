package ru.evgenykuzakov.users.show_users.placeholder

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.evgenykuzakov.designsystem.ui.BodyLargeText

@Composable
internal fun StyledRow(
    @DrawableRes iconResId: Int,
    text: String
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        StyledIcon(resId = iconResId)
        Spacer(modifier = Modifier.width(8.dp))
        BodyLargeText(text = text)
    }
}


@Composable
internal fun StyledIcon(
    @DrawableRes resId: Int,
    color: Color = MaterialTheme.colorScheme.onSurface
){
    Icon(
        painter = painterResource(resId),
        contentDescription = null,
        tint = color
    )
}