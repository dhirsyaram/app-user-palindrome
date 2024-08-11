# Palindrome Checker and User List App

## Overview

This application demonstrates two core functionalities:
1. **Palindrome Checker**: Users can input a sentence to check if it is a palindrome.
2. **User List**: Displays a list of users fetched from an API.

## Features

- **Palindrome Checking**: Enter a sentence to determine if it reads the same backward as forward.
- **User List Display**: Fetches and displays a paginated list of users from a remote API.
- **Navigation**: Navigate between different activities with smooth transitions and data passing.

## Demo

Check out the demo of the application: [Demo App](https://github.com/user-attachments/assets/97364f54-a407-44e3-ba32-43f647c9f785)

## Screenshots

| ![Main Activity](https://github.com/user-attachments/assets/c027edf7-5d31-432a-92de-a01e8142d00d) | ![Palindrome Checker](https://github.com/user-attachments/assets/47184984-33fe-4b29-ae0f-7e981663ca62) |
|:-----------------------------------------:|:---------------------------------------------------:|
| *Main Activity showing palindrome check and username input* | *Validation result for a palindrome check* |

| ![Second Activity](https://github.com/user-attachments/assets/76a4c7eb-1dc1-4d6e-b039-0bbde962c7ad) | ![Third Activity](https://github.com/user-attachments/assets/8b6f779a-95a8-4041-8df5-e4828440d457) |
|:---------------------------------------------:|:---------------------------------------------:|
| *Second Activity displaying username passed from MainActivity and ThirdActivity* | *Third Activity displaying fetched user data* |

## How to Use

1. **Clone the Repository**: 
   ```bash
   git clone https://github.com/your-repo/palindrome-checker-user-list-app.git
2. **Build & Run**:
   Open the project in Android Studio, sync the Gradle files, and run the application on an emulator or physical device.
3. **Check Palindrome**:
   In the main activity, input a sentence to see if it's a palindrome.
4. **View User List**:
   Navigate to the user list activity to browse through users fetched from the API.

## Configuration

To keep sensitive information secure and to allow easy configuration, the base URL for the API is stored in the `local.properties` file. This approach prevents hardcoding the URL in the codebase and allows for different environments (e.g., development, production).

### Steps to Set Up

1. Open the `local.properties` file in the root directory of your project.
2. Add the following line, replacing `API_USER_KEY` with your actual API base URL:
   ```properties
   API_USER_KEY=https://your-api-url.com/

## Technologies Used

- **Kotlin**: The app is built using Kotlin, leveraging its modern features for concise and safe code.
- **Retrofit**: Used for making API calls to fetch user data from a remote server.
- **ViewModel and LiveData**: Implemented for efficient data management and UI updates, ensuring the app responds to data changes smoothly.
- **RecyclerView**: Utilized for displaying the list of users with efficient scrolling and data handling.
- **Paging**: Integrated to handle large datasets by loading user data in pages, improving the performance of the user list display.
  
## Contact

For any inquiries or issues, please contact:

- **Email**: dhirsyaramadhanpattah02@gmail.com or ramadhandhirsya@gmail.com
- **GitHub**: [My GitHub Profile](https://github.com/dhirsyaram)
- **LinkedIn**: [My LinkedIn Profile](https://linkedin.com/in/dhirsyarp)

