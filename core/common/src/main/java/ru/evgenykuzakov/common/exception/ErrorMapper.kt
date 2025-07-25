package ru.evgenykuzakov.common.exception

interface ErrorMapper {
    fun map(throwable: Throwable): String
}
