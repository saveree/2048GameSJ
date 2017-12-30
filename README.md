 *  Name: Saveree Joshipura
 *  Login: cs8bwaph
 *  Date: February 7th, 2017
 *  File: README.md 
 *  Sources of Help: Tutors! 
 *  CSE8B Assignment 4. 
 *  Name: README
 *  Purpose: Summary of PSA3

Part 1: Unix and Java Questions 

1. cp cd ../../../fubar.java is the full command. 
2. Man cat shows the online "manual" for the cat command.

Java Related Questions:
1. You need to have an access modifier (public, private, or protected). You also need to have the name of the class that the constructor is contained in. 
2. Static in regard to variables means that a varaible that is marked static is not available at the class level. So, you don't need to create instances to access the method.

Part 3 - Program Descriptions: 

Board.java
Board.java is a program that sets up the 2048 game. For example, it has every small detail inside, like how to add a random tile, or how to check if the game is over or not. It is important because it is the reason the code runs the way it does. It also has the code for making a move, and for setting up the boards, from the last PSA. 

GameManager.java
Game Manager has one of the most important parts of the code - the code for how to play the game. When we run this part (method), of the program, it executes the 2048 game. 


Description on Testing:
Because we didn't have a tester given to us, we had to test our methods ourselves. I tested the edge cases while I was playing the game. First, I made sure that if I was already on the lowest tile, or the top most tile, or the tile on the farthest left or right, that the program would not give an error but simply add a random tile to the board to complete the move. Then, I made sure I did not simply add all the tiles in a row or column together as I made a move, but instead shifted them up and only added them together if they were the same tile. Then, I tested the quit method by ensuring the program did not ask user for further input. I also tested to see what would happen if I pressed a command that did not shift any of the tiles. 


