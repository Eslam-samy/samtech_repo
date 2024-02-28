# User Android App

## Overview

The User Android App is a native application developed in Android Studio, targeting the latest Android version while ensuring backward compatibility with Android version 8.0. It integrates Firebase Push Notifications for receiving external notifications and utilizes a local database (Room) to store notifications locally. The UI/UX is designed to be responsive and intuitive, featuring appropriate UI components and navigation between screens.

## Features

1. **Firebase Push Notifications:**
   - Integrated Firebase Push Notifications for receiving external notifications from the Firebase console.
   - Notifications include a title and description.

2. **Notification Display:**
   - Displays all notifications on a screen, with the latest notifications appearing at the top.
   - Allows filtering of notifications based on the notification description and date/time.

3. **Animated Notification Screen:**
   - Displays an animated screen when a notification is clicked in the notification channel bar.

4. **Local Database (Room):**
   - Utilizes Room as a local database to store notifications locally.
   - Implements CRUD (Create, Read, Update, Delete) operations for managing local notifications.

5. **UI/UX:**
   - Ensures a responsive and intuitive user interface.
   - Uses appropriate UI components such as RecyclerView, Material Controls, etc.
   - Implements navigation between screens using Dagger Hilt, Paging 3, and Navigation Component.

## Tech Stack

- Android Studio
- Kotlin
- Dagger Hilt
- Paging 3
- Room
- Firebase Cloud Messaging (FCM)
- Navigation Component

## Getting Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Eslam-samy/samtech_repo.git
