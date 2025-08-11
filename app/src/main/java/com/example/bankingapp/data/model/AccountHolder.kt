package com.example.bankingapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountHolder(
    val name: String,
    val accountNumber: String,
    val transactions: MutableList<Transaction> = mutableListOf()
) {
    val balance: Double
        get() = transactions.sumOf { if (it.type == "Deposit") it.amount else -it.amount }
}