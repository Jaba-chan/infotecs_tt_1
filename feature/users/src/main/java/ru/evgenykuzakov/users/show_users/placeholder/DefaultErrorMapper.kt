package ru.evgenykuzakov.users.show_users.placeholder

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.evgenykuzakov.common.exception.ApiException
import ru.evgenykuzakov.common.exception.ErrorMapper
import ru.evgenykuzakov.users.R
import java.net.UnknownHostException
import javax.inject.Inject

class DefaultErrorMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : ErrorMapper {
    override fun map(throwable: Throwable): String =
        when (throwable) {
            is ApiException -> mapApiException(throwable)
            is UnknownHostException -> mapUnknownHostException()
            else -> context.getString(R.string.error_default)
        }

    private fun mapApiException(throwable: Throwable): String {
        return when (throwable.message) {
            """
            {
              "error": "Uh oh, something has gone wrong. Please tweet us @randomapi about the issue. Thank you."
            }
            """.trimIndent() -> context.getString(
                R.string.something_has_gone_wrong
            )

            "No users" -> context.getString(R.string.error_no_users)
            else -> context.getString(R.string.error_default)
        }
    }

    private fun mapUnknownHostException(): String {
        return context.getString(R.string.error_no_internet_connection)
    }


}