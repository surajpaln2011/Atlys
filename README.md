# 🎬 Movie App - Android Take Home Assignment - Atlys

https://github.com/user-attachments/assets/20a2b9ab-5be5-4d39-95cb-edcc92ac5f62


Welcome to the Android Take-Home Assignment for Atlys. This project is a movie listing app built using **Kotlin** and **Jetpack Compose**.
The app fetches movie data from the **TMDB API** and allows users to search for and view movie details.

## 📱 Features

- Display a list of trending movies fetched from the [TMDB API](https://www.themoviedb.org/movie).
- Search functionality to find movies from the list.
- Movie detail screen with detailed information.
- Handles loading, empty, and error states.
- Follows Android development best practices.

## 🛠 Tech Stack

- **Kotlin**: Language for building Android apps.
- **Jetpack Compose**: Modern toolkit for building native UI.
- **Compose Navigation**: For navigation between screens.
- **TMDB API**: To fetch movie data.
- **Third-party libraries**: (You can list any libraries you used for network calls or image loading here, e.g., Retrofit, Coil).

## 🎨 Design

The UI is based on the design provided in the following [Figma link](https://www.figma.com/design/6nwpyma1zVevcAST5Bec4k/Atlys-Android-Engineer-Assignment?node-id=0-1&t=CGg2V2kmpB28VarI-0).

## 🚀 Getting Started

### Prerequisites

- Register on [TMDB](https://www.themoviedb.org/) to obtain an API key.
- Add your API key to the project to fetch data.

### API Key Setup

1. Obtain your API key from TMDB.
2. Insert your API key into the appropriate configuration file or environment variable in the project.

```kotlin
const val API_KEY = "your_api_key_here"
