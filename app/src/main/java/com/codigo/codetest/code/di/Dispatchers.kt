
package com.codigo.codetest.code.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatcher: MovieDispatcher)

enum class MovieDispatcher {
    Default,
    IO,
}
