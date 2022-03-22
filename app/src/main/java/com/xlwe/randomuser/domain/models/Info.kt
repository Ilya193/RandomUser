package com.xlwe.randomuser.domain.models

data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)