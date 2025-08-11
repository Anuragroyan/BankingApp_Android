package com.example.bankingapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val type: String, // "Deposit" or "Withdrawal"
    val amount: Double,
    val date: String
)
