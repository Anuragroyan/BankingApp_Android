package com.example.bankingapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bankingapp.data.BankRepository
import com.example.bankingapp.data.model.AccountHolder
import com.example.bankingapp.data.model.Transaction
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BankViewModel(private val repository: BankRepository) : ViewModel() {

    var accounts by mutableStateOf<List<AccountHolder>>(emptyList())
        private set

    init {
        accounts = repository.loadAccounts()
    }

    fun addAccount(name: String, accountNumber: String) {
        val updated = accounts + AccountHolder(name, accountNumber)
        repository.saveAccounts(updated)
        accounts = updated
    }

    fun deposit(account: AccountHolder, amount: Double) {
        val updatedList = accounts.map {
            if (it.accountNumber == account.accountNumber) {
                it.copy(transactions = (it.transactions + Transaction("Deposit", amount, now())).toMutableList())
            } else it
        }
        repository.saveAccounts(updatedList)
        accounts = updatedList
    }

    fun withdraw(account: AccountHolder, amount: Double) {
        if (account.balance >= amount) {
            val updatedList = accounts.map {
                if (it.accountNumber == account.accountNumber) {
                    it.copy(transactions = (it.transactions + Transaction("Withdrawal", amount, now())).toMutableList())
                } else it
            }
            repository.saveAccounts(updatedList)
            accounts = updatedList
        }
    }

    fun resetData() {
        repository.resetAccounts()
        accounts = emptyList()
    }

    private fun now(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }
}