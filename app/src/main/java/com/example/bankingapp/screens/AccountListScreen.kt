package com.example.bankingapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bankingapp.data.model.AccountHolder
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults

@Composable
fun AccountListScreen(
    accounts: List<AccountHolder>,
    onSelect: (AccountHolder) -> Unit
) {
    if (accounts.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("No accounts. Add one!")
        }
    } else {
        LazyColumn {
            items(accounts) { acc ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onSelect(acc) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(acc.name, style = MaterialTheme.typography.titleLarge)
                        Text("Acc No: ${acc.accountNumber}")
                        Text("Balance: ₹${acc.balance}")
                    }
                }

            }
        }
    }
}