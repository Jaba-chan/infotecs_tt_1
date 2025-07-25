package ru.evgenykuzakov.designsystem.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

@Composable
fun BodyLargeText(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = MaterialTheme.colorScheme.onSurface,
){
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun ActionText(
    heading: String,
    underlined: String,
    onClick: () -> Unit,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = MaterialTheme.colorScheme.onSurface,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    Row(
        modifier = Modifier.clip(CircleShape)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = style.toSpanStyle()) {
                    append("$heading ")
                }
                withStyle(
                    style = style.toSpanStyle().copy(textDecoration = TextDecoration.Underline)
                ) {
                    append(underlined)
                }
            },
            modifier = Modifier
                .clickable { onClick() },
            color = color,
            textAlign = textAlign,
        )
    }

}

@Composable
fun LabelSmallText(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onSurface
){
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = color,
        textAlign = textAlign
    )
}