package com.babynames.babynames.core.presentation.observers

import io.reactivex.observers.DisposableObserver

abstract class DefaultObserver<T, V>(val view: V?) : DisposableObserver<T>()