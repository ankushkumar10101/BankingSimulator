# Java Banking Simulator (Console Application)

## Overview
A simple, terminal-based **banking system simulator** built in **Java**. This project simulates essential banking operations such as account creation, secure login with PIN verification, deposits, withdrawals, and transaction history management. The system utilizes a **file-based** storage mechanism (CSV format) for persistent data storage, providing a lightweight approach without the need for a database.

## Features
- **Account Management**: Create new accounts and store details securely.
- **Secure Login**: Authenticate users with a 4-digit PIN.
- **Transactions**: Deposit and withdraw funds to/from accounts.
- **Transaction History**: View a mini bank statement displaying recent transactions.
- **Persistence**: Accounts and transaction history are stored in a CSV file for data persistence between sessions.
- **Terminal-Based UI**: User interactions through simple terminal commands for a minimalist banking experience.

## Project Structure

```plaintext
BankingSimulator/
├── Account.java            # Class defining Account model and operations
├── Bank.java               # Core business logic for account management
├── BankingApp.java         # Entry point for application logic and user interface
├── accounts.csv            # File-based storage for account information (CSV format)
├── txn_xyz.txt             # Holds the transaction history of a particular account
└── README.md               # Project documentation
