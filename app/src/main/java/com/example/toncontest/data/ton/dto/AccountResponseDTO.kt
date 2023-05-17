package com.example.toncontest.data.ton.dto

data class AccountResponseDTO(
    val status: String,
    val balance: Long,
    val address: String,
    val code: String? = null,
    val data: String? = null
)