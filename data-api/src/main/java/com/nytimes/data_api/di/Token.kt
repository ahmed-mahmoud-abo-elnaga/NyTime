package com.nytimes.data_api.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.VALUE_PARAMETER
)
annotation class Token