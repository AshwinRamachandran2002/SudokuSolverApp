# SudokuSolverApp
Android App that solves Sudoku through Image Capture

# Functionalities
The app can scan the image of a sudoku and convert it to a sudoku board by sending the image to a api hosted on heroku
The api sends back the digits it detected in a string and then we configure that into a GUI table made up by a grid

The user is free to make any correcctions and the corrections are sent back to the api and it retrains the model to be able 
detect this(Online LEarning)

# The algorithm
I have tried to explore the strategies that one could use including hidden pairs , nake pairs and simple coloring

The third is a work still in progress

Inspiration : https://medium.com/@davidcarmel/solving-sudoku-by-heuristic-search-b0c2b2c5346e


# OpenCV Algorithm
https://colab.research.google.com/drive/1vJDPYOZ8_Cl5-0ZmBICJcwpeTwFoBIuf?usp=sharing


# App at play
(Results may not be accurate since the model is not a 100% accurate and my dataset and the user given image may differ(Hence the ONLINE LEARNING))
![Screenshot_20211009-015254_Camera](https://user-images.githubusercontent.com/77834936/136621266-4b146548-9f7f-4ab0-b549-e20367c623f7.jpg)
![Screenshot_20211009-015226_Ashwin's Sudoku Solver](https://user-images.githubusercontent.com/77834936/136621330-64b4af8d-b428-4428-8e54-fc7bf67589c5.jpg)
![Screenshot_20211009-015828_Ashwin's Sudoku Solver](https://user-images.githubusercontent.com/77834936/136621339-b629ab17-cd41-4652-91a1-358115d3d1c0.jpg)
![WhatsApp Image 2021-10-09 at 18 54 46](https://user-images.githubusercontent.com/77834936/136659600-a85616d4-fcaf-4b59-96e7-3f318b5bfad8.jpeg)
