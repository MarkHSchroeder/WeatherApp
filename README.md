# Weather App

## Overview
The Weather App allows users to enter the name of a city and view the current weather conditions for that city. The app fetches real-time data from a weather API and displays key information such as temperature, humidity, weather description, and more. It's a simple and intuitive tool to check the weather forecast for any location.

## Features
- Search for weather data by entering a city name.
- Displays current weather including:
  - Temperature (in Fahrenheit)
  - Weather description icon (e.g., Clear, Rainy)
  - Humidity percentage
  - UV
  - Feels Like Temperature (in Fahrenheit)
    
## Requirements
- **Android Version**: Android 7.0 (=ougat) or higher

## Setup and Installation

1. **Clone the Repository:**
   Clone the repository to your local machine using Git:

   ```bash
   git clone https://github.com/yourusername/weather-app.git```
   
2. **Open the Project:** Open the project in Android Studio

3. **Gradle Sync:** Sync your Gradle files to download dependencies and prepare the app for building.

4. **Build the App:** Click on Build > Make Project to compile the app. Once the build process is complete, you can run the app on an emulator or physical device.

## Usage

1. **Launch the App:** Open the application after installation.

2. **Click on Search Bar:** Click on the search bar on the top of the screen.

3. **Enter City Name:** Type in a city name. An empty field will be ignored.

4. **Press the Keyboard Search Button:** The keyboard button will trigger the weather search.

5. **Select the City:** Search results will display with a city to select. This city will become your default city.

6. **View Weather Information:** The app will display the current weather data for the entered city, including:
  - Temperature (Fahrenheit)
  - Weather description icon (e.g., Clear, Rainy)
  - Humidity percentage
  - UV
  - Feels Like Temperature (Fahrenheit)

7. **Relaunch Application:** The results from your last selected city will load its weather data.

8. **Error Handling:** If the city name is invalid or there is no network connection, the app will show an error message.

## Technologies Used

  - **Android SDK:**: For building the Android application.
  - **Jetpack Compose:**: For UI.
  - **Coroutines:**: For asynchronous tasks.
  - **Hilt:**: For dependancy injection.
  - **Retrofit:**: For network requests to fetch weather data.
  - **JSON:**: For parsing the weather data received from the API.
  - **Glide:**: For loading images (such as weather icons) from URLs.

## API Documentation
The app uses the WeatherAPI.com API to retrieve weather data. Detailed documentation on how to make requests and interpret the responses can be found in the WeatherAPI.com documentation (https://www.weatherapi.com/docs/).

## Support
For any issues, bugs, questions, or suggestions, feel free to open an issue in the repository, and the I will address it as soon as possible.

Thank you for using the Weather App!
