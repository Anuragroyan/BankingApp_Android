package com.example.bankingapp.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.bankingapp.data.model.AccountHolder
import com.example.bankingapp.viewmodel.BankViewModel
import androidx.compose.foundation.text.KeyboardOptions



@Composable
fun AccountDetailsScreen(account: AccountHolder, onBack: () -> Unit, vm: BankViewModel) {
    var depositAmount by remember { mutableStateOf("") }
    var withdrawAmount by remember { mutableStateOf("") }

    BackHandler(onBack = onBack)

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(account.name, style = MaterialTheme.typography.titleLarge)
        Text("Account No: ${account.accountNumber}")
        Text("Balance: ₹${account.balance}")

        Spacer(Modifier.height(16.dp))
        Row {
            OutlinedTextField(
                value = depositAmount,
                onValueChange = { depositAmount = it },
                label = { Text("Deposit") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    depositAmount.toDoubleOrNull()?.let { vm.deposit(account, it) }
                    depositAmount = ""
                },
                Modifier.padding(start = 8.dp)
            ) { Text("Add") }
        }

        Row {
            OutlinedTextField(
                value = withdrawAmount,
                onValueChange = { withdrawAmount = it },
                label = { Text("Withdraw") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    withdrawAmount.toDoubleOrNull()?.let { vm.withdraw(account, it) }
                    withdrawAmount = ""
                },
                Modifier.padding(start = 8.dp)
            ) { Text("Take") }
        }

        Spacer(Modifier.height(16.dp))
        Text("Transactions", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(account.transactions.reversed()) { tx ->
                Text("${tx.date}: ${tx.type} ₹${tx.amount}")
            }
        }
    }
}