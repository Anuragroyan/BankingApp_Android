package com.example.bankingapp.data

import com.example.bankingapp.data.model.AccountHolder
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class BankRepository(private val file: File) {
    private val json = Json { prettyPrint = true; ignoreUnknownKeys = true }

    fun loadAccounts(): List<AccountHolder> {
        if (!file.exists()) saveAccounts(emptyList())
        val content = file.readText()
        return if (content.isNotBlank()) json.decodeFromString(content) else emptyList()
    }

    fun saveAccounts(accounts: List<AccountHolder>) {
        file.writeText(json.encodeToString(accounts))
    }

    fun resetAccounts() {
        if (file.exists()) file.delete()
        saveAccounts(emptyList())
    }
}