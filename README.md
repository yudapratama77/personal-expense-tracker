# personal-expense-tracker
Yuda's Personal Expense Tracker

A modern Android application built with **Jetpack Compose** to help users track daily expenses easily.
This project was created as part of a **Mobile Developer Take-Home Assignment**.

---
# Features
## Core Features

- Add new expense
- View list of expenses
- View expense detail
- Local data persistence using Room
- Total expense summary
- Date picker support
- Input validation
- Empty state handling

---

# Tech Stack
## Language

- Kotlin

## UI

- Jetpack Compose
- Material 3

## Architecture

- MVVM
- Clean Architecture

## Dependency Injection

- Hilt

## Local Storage

- Room Database

## Async & State Management

- Kotlin Coroutines
- StateFlow

## Navigation

- Navigation Compose

---

# Project Structure

```text
com.yuda.personalexpensetracker
├── core
├── data
├── domain
├── presentation
├── di
└── util
```

### Architecture Layers

## Presentation Layer

Responsible for:
- UI
- ViewModel
- Screen state
- Navigation

## Domain Layer

Responsible for:
- Business logic
- Use cases
- Repository contracts

## Data Layer

Responsible for:
- Room database
- DAO
- Repository implementation

---

# Architecture

This project follows:

## MVVM + Clean Architecture

Benefits:
- Better separation of concerns
- Easier testing
- Better scalability
- Maintainable codebase

---

# Screens

## Expense List Screen

- View all expenses
- View total expense summary
- Navigate to detail screen

## Add Expense Screen

- Add expense title
- Add amount
- Select date
- Input validation

## Expense Detail Screen

- View selected expense detail
- Clean information layout

---

# ⚙️ Libraries Used

| Library | Purpose |
|---|---|
| Jetpack Compose | Modern UI Toolkit |
| Navigation Compose | Screen Navigation |
| Hilt | Dependency Injection |
| Room | Local Database |
| Kotlin Coroutines | Async Programming |
| StateFlow | Reactive State Management |

---

# Validation Handling

The app validates:
- Empty title
- Empty amount
- Invalid numeric amount

User feedback is displayed directly in the form.

---

# Data Persistence

Expenses are stored locally using:
- Room Database

Data remains available after app restart.

---

# How To Run

## Requirements

- Android Studio Narwhal or newer
- JDK 17
- Android SDK 24+

---

## Steps

1. Clone repository

```bash
git clone https://github.com/yudapratama77/personal-expense-tracker.git
```

2. Open project in Android Studio

3. Sync Gradle

4. Run app

---

# Author

Developed by:
Yuda Pratama
