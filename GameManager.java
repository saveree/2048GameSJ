//------------------------------------------------------------------//
// GameManager.java                                                 //
//                                                                  //
// Game Manager for 2048                                            //
//                                                                  //
// Author: Saveree Joshipura A13349228 cs8bwaph   	                //
// Date:    02/3/17                                                 //
//------------------------------------------------------------------//

import java.util.*;
import java.io.*;

public class GameManager {
    private Board board; // The actual 2048 board
    private String outputFileName; // File to save the board to when exiting

    /** generates a new game
      * @param outputBoard, boardSize, random
      */
    public GameManager(String outputBoard, int boardSize, Random random) 
    {
        this.board = new Board(random, boardSize);
        this.outputFileName = outputBoard;
        System.out.println("Generating a New Board");
    }

    /** loads a saved game 
      * @param inputBoard, outputBoard, random
      */
    public GameManager(String inputBoard, String outputBoard, Random random) throws IOException 
    {
        this.board = new Board(random, inputBoard);
        this.outputFileName = outputBoard;
        System.out.println("Loading Board from " + inputBoard);
    }

    // TODO PSA3
    // Main play loop
    // Takes in input from the user to specify moves to execute
    // valid moves are:
    //      k - Move up
    //      j - Move Down
    //      h - Move Left
    //      l - Move Right
    //      q - Quit and Save Board
    //
    //  If an invalid command is received then print the controls
    //  to remind the user of the valid moves.
    //
    //  Once the player decides to quit or the game is over,
    //  save the game board to a file based on the outputFileName
    //  string that was set in the constructor and then return
    //
    //  If the game is over print "Game Over!" to the terminal


    /** performs the game
     */
    public void play() throws IOException 
    {
        printControls();
        String boardPrint = board.toString();
        System.out.println(boardPrint);

        Scanner in = new Scanner(System.in);
        String input; 
        System.out.println("Please enter a valid move.");
       
        while(board.isGameOver() == false)
        {
            input = in.next();
            if(input.equals("k"))
            {
                System.out.println("hi");
                board.move(Direction.UP);
                board.addRandomTile();
                boardPrint = board.toString();
                System.out.println(boardPrint);
            } 

            else if(input.equals("j"))
            {
                board.move(Direction.DOWN);
                board.addRandomTile();
                boardPrint = board.toString();
                System.out.println(boardPrint);
            }

            else if(input.equals("h"))
            {
                board.move(Direction.LEFT);
                board.addRandomTile();
                boardPrint = board.toString();
                System.out.println(boardPrint);
            }

            else if(input.equals("l"))
            {
                board.move(Direction.RIGHT);
                board.addRandomTile();
                boardPrint = board.toString();
                System.out.println(boardPrint);
            }

            else if(input.equals("q") || board.isGameOver())
            {
               System.out.println("Game over!"); 
               board.saveBoard(outputFileName);
               if(board.isGameOver()) 
               {
                System.out.println("Game over!");
               }
               return;
            }   
            else 
            {
                System.out.println("Sorry, that was not a valid move. Please enter a valid move (k, j, h, l, q)");
                input = in.next();
            }
        }

        if(this.board.isGameOver() == true) 
        {
            board.saveBoard(outputFileName);
            System.out.println("Game Over!");
        }
    }

    /** prints the controls for the game
     **/
    private void printControls() 
    {
        System.out.println("  Controls:");
        System.out.println("    k - Move Up");
        System.out.println("    j - Move Down");
        System.out.println("    h - Move Left");
        System.out.println("    l - Move Right");
        System.out.println("    q - Quit and Save Board");
        System.out.println();
    }
}
