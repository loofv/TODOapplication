# TODO Application
#### Video demo 
https://youtu.be/tSiqsQL7-c8?si=4VGF06OEXht_gbIG
#### Description
This is a simple todo-list application that I coded in Kotlin using the latest technologies.
#### Technologies used
- Kotlin
- Jetpack Compose
- Dagger
- Hilt
- Room database
- Enter and exit animations
#### What the app does
The app lets the user create tasks on a todo-list. The user can set a priority for the tasks, delete tasks, sort them and store them in the app. The app has support for dark mode, letting the user use the native built-in theme setting to choose between light and dark mode.

#### My goals with the app
There are probably thousands of todo-list applications out there today. The reason I chose to make my own was that it was a good real world problem to solve, and that also let me focus on other aspects of the project.

My main goal was to learn more about Jetpack Compose, Dagger/Hilt, clean architecture and MVVM (Model View Viewmodel). I have now learned to use those technologies and have gained knowledge about their advantages and drawbacks.

#### Structure
MainActivity is (as always) the entry point for the application. After that, navigation is handled by the file called Navigation, which directs the user between the 3 different screens (splash screen, task screen and list screen). At a later stage, I decided to use the native splash screen instead but I kept the files for the old one for reference.

#### Design decisions
The app uses Composable functions for building the UI, which is good for performance since they are only redrawn when their state changes. This lets you only redraw part of the UI which saves performance. 

I decided to forego unit tests for this project, mostly due to lack of time and because I wanted to focus more on the architecture and functionality of the app. But unit tests and end-to-end tests would be a good next step. And after that maybe extend the functionality to be able to create more complex and varied tasks (for instance a gym workout or a shopping list).


#### Prerequisites
- Android Studio. Available here: https://developer.android.com/studio
- Optional: Physical Android device
#### Installation
1. Clone the repo
2. Open the root folder of the repo in Android Studio.
3. Build the application.
4. Run the application on an emulator or an Android device of your choice.
5. That's it!

#### Final notes
Feel free to clone this repo and continue building on it on your own branch, or look around in the code to get a feel for how Android-style Clean architecture looks with Dagger and Hilt looks. Personally, I feel that Clean architecture has good intentions but can easily increase the complexity of a project, as well as generating a bit more boilerplate code. 
