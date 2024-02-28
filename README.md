Android Notifications App
Overview

This Android application is developed as a task for SamTech. It is a native Android app created in Android Studio targeting the latest Android version (API level 31) with backward compatibility till Android version 8.0 (API level 26). The app integrates Firebase Push Notifications for receiving external notifications and uses a local database (Room) to store notifications locally.
Features

    Firebase Push Notifications:
        The app integrates with Firebase to receive external notifications from the Firebase console.
        Notifications include a title and description.

    Notification Display:
        All received notifications are displayed on a screen with the latest notification on top.
        Users can filter notifications based on the notification description and date/time.

    Animated Screen on Notification Click:
        When a notification is clicked in the notification channel bar, an animated screen is displayed.

    Local Database (Room):
        The app utilizes Room as a local database to store notifications locally.
        CRUD (Create, Read, Update, Delete) operations are implemented for managing local notifications.

    UI/UX:
        The app ensures a responsive and intuitive user interface.
        It uses appropriate UI components such as RecyclerView, Material Controls, etc.
        Navigation between screens is implemented using the Navigation Component.

Tech Stack

    Android Studio
    Kotlin
    Dagger Hilt
    Paging 3
    Room
    Firebase Cloud Messaging (FCM)
    Navigation Component


  Getting Started

    Clone the Repository:

    bash

    git clone https://github.com/your-username/notifications-app.git

    Open in Android Studio:
        Open Android Studio.
        Select "Open an existing Android Studio project" and choose the cloned directory.

    Configure Firebase:
        Follow the Firebase setup guide to add your project to Firebase.

    Run the App:
        Connect an Android device or use an emulator.
        Click on the "Run" button in Android Studio.

Screenshots



Contributions

Contributions are welcome! Feel free to submit issues and pull requests.
License

This project is licensed under the MIT License.
