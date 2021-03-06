//------------------------------------------------------------------//
// PSA3Tester.java                                                  //
//                                                                  //
// Simple Tester for students to use while doing PSA3 (2048 Part 1) //
//                                                                  //
// Author:  Ujjwal Gulecha, Alan Kuo		                            //
// Date:    0/25/17                                                //
//------------------------------------------------------------------//

/** DO NOT MODIFY */

import java.util.*;
import java.io.*;

public class PSA3Tester
{
    private static int SEED = 2017;
    private static String inputBoard = "psa3Test.board";
    private static String outputBoard = "student.board";
    private static int inputScore = 2481632;
    private static int[][] inputGrid = {{2,128,256,32768},
                                        {2,64,512,16384},
                                        {4,32,1024,8192},
                                        {8,16,2048,4096}};

    // All these test boards are based off the SEED 2017
    private static int[][][] testGrid = {{{0,0,0,0},
                                          {2,0,0,0},    // Grid 0
                                          {0,2,0,0},
                                          {0,0,0,0}},

                                         {{0,0,0,0},
                                          {2,0,0,0},    // Grid 1
                                          {2,2,0,0},
                                          {0,0,0,0}},

                                         {{0,0,0,0},
                                          {2,0,0,0},    // Grid 2
                                          {2,2,0,0},
                                          {2,0,0,0}},

                                         {{0,0,0,0},
                                          {2,0,0,0},    // Grid 3
                                          {2,2,0,0},
                                          {2,0,0,2}},

                                         {{0,0,0,0},
                                          {2,0,0,0},    // Grid 4
                                          {2,2,0,2},
                                          {2,0,0,2}},

                                         {{0,0,0,0},
                                          {2,0,0,2},    // Grid 5
                                          {2,2,0,2},
                                          {2,0,0,2}},

                                         {{0,0,0,0},
                                          {2,0,0,2},    // Grid 6
                                          {2,2,2,2},
                                          {2,0,0,2}}};

    public static void main(String[] args) throws IOException,
                                                  InterruptedException
    {
        System.out.println("Begining Testing PSA3");

        testBoardCtor1();
        testBoardCtor2();
        testAddRandomTile();
        testSaveBoard();
        testFlip();
    }

    private static void testFlip() throws IOException
    {
       System.out.print("Testing flip(int) method..............");
         
       boolean isPassed = true;

       int[][] flipHorizontalAnswer = {{32768,256,128,2},
                                  {16384,512,64,2},
                                  {8192,1024,32,4},
                                  {4096,2048,16,8}}; 

       int[][] flipVerticalAnswer = {{8,16,2048,4096},
                                  {4,32,1024,8192},
                                  {2,64,512,16384},
                                  {2,128,256,32768}};         

       int[][] clockWiseAnswer = {{8,4,2,2},
                                  {16,32,64,128},
                                  {2048,1024,512,256},
                                  {4096,8192,16384,32768}};

       int[][] counterClockWiseAnswer = {{32768,16384,8192,4096},
                                         {256,512,1024,2048},
                                         {128,64,32,16},
                                         {2,2,4,8}};
       try
       {
          //Creating four boards for testing flipping
          Board studentBoard1 = new Board(new Random(SEED),inputBoard);
          studentBoard1.flip(3);
          Board studentBoard2 = new Board(new Random(SEED),inputBoard);
          studentBoard2.flip(4);
          Board studentBoard3 = new Board(new Random(SEED),inputBoard);
          studentBoard3.flip(1);
          Board studentBoard4 = new Board(new Random(SEED),inputBoard);
          studentBoard4.flip(2);
          if(!Arrays.deepEquals(studentBoard3.getGrid(),flipHorizontalAnswer))
          {
              System.out.println("FAILED!");
             //If clockwise rotations do not match
              System.out.println("Your flip method failed to flip the grid horizontally correctly for test input");
              System.out.println("This is your grid after flipping horizontally:");
              print2DArray(studentBoard3.getGrid());
              System.out.println("This should be the grid after flipping horizontally:");
              print2DArray(flipHorizontalAnswer);
              isPassed = false;
          }
          if(!Arrays.deepEquals(studentBoard4.getGrid(),flipVerticalAnswer))
          {
              System.out.println("FAILED!");
             //If clockwise rotations do not match
              System.out.println("Your flip method failed to flip the grid vertically correctly for test input");
              System.out.println("This is your grid after flipping vertically:");
              print2DArray(studentBoard4.getGrid());
              System.out.println("This should be the grid after flipping vertically:");
              print2DArray(flipVerticalAnswer);
              isPassed = false;
          }
          if(!Arrays.deepEquals(studentBoard1.getGrid(),clockWiseAnswer))
          {
              System.out.println("FAILED!");
             //If clockwise rotations do not match
              System.out.println("Your flip method failed to rotate the grid clockwise correctly for test input");
              System.out.println("This is your grid after clockwise rotation:");
              print2DArray(studentBoard1.getGrid());
              System.out.println("This should be the grid after clockwise rotation:");
              print2DArray(clockWiseAnswer);
              isPassed = false;
          }
          if(!Arrays.deepEquals(studentBoard2.getGrid(),counterClockWiseAnswer))
          {
             //If counter-clockwise rotations do not match
             System.out.println("Your rotate method failed to rotate the grid counter-clockwise correctly for test input");
             System.out.println("This is your grid after counter-clockwise rotation:");
             print2DArray(studentBoard2.getGrid());
             System.out.println("This should be the grid after counter-clockwise rotation:");
             print2DArray(counterClockWiseAnswer);
             isPassed = false;
          }
          if(isPassed == true)
          {
             System.out.println("Passed!");
          }
            
       }
       catch (Exception e)
       {
          System.out.println("FAILED!");
          System.out.println("An Exception was thrown while testing flip() method!");
          e.printStackTrace();
          return;
       }
    }

    private static void testBoardCtor1() throws IOException
    {
        Board studentBoard;

        // Test the Constructor which generates a new board
        System.out.print("Testing Board(random, int) Constructor......");

        try
        {
            studentBoard = new Board(new Random(SEED), 4);

            int[][] studentGrid = studentBoard.getGrid();
            if(studentGrid == null)
            {
                System.out.println("FAILED!");
                System.out.println("Your grid (2D int array) is null " +
                                   "and hasn't been initialized!");
                return;
            }

            if(studentBoard.GRID_SIZE != 4)
            {
                System.out.println("FAILED!");
                System.out.println("You didn't set the GRIDSIZE correctly");
                System.out.println("Should be: " + 4 +
                                   " Yours is: " + studentBoard.GRID_SIZE);
                return;
            }

            if(studentBoard.getScore() != 0)
            {
                System.out.println("FAILED!");
                System.out.println("Score is incorrect");
                System.out.println("Should be: " + 0 +
                                   " Yours is: " + studentBoard.getScore());
                return;
            }

            if(!Arrays.deepEquals(testGrid[0], studentBoard.getGrid()))
            {
                System.out.println("FAILED!");
                System.out.println("Your 2D grid isn't correct");
                System.out.println("This may be due to your " +
                                   "addRandomTile Method");
                System.out.println("Should be: ");
                print2DArray(testGrid[0]);
                System.out.println("Yours is: ");
                print2DArray(studentBoard.getGrid());
                return;
            }

            System.out.println("Passed!");
        }
        catch(Exception e)
        {
            System.out.println("FAILED!");
            System.out.println("Exception was thrown while trying " +
                               "to create a new Board");
            e.printStackTrace();
            return;
        }
    }

    private static void testBoardCtor2() throws IOException
    {
        Board studentBoard;
        // Test the Constructor which Loads a saved board
        System.out.print("Testing Board(random, string) Constructor...");

        try
        {
            studentBoard = new Board(new Random(SEED),inputBoard);

            int[][] studentGrid = studentBoard.getGrid();
            if(studentGrid == null)
            {
                System.out.println("FAILED!");
                System.out.println("Your grid (2D int array) is null " +
                                   "and hasn't been initialized!");
                return;
            }

            if(studentBoard.GRID_SIZE != 4)
            {
                System.out.println("FAILED!");
                System.out.println("You didn't set the GRIDSIZE correctly");
                System.out.println("Should be: " + 4 +
                                   " Yours is: " + studentBoard.GRID_SIZE);
                return;
            }

            if(studentBoard.getScore() != inputScore)
            {
                System.out.println("FAILED!");
                System.out.println("Score is incorrect");
                System.out.println("Should be: " + inputScore +
                                   " Yours is: " + studentBoard.getScore());
                return;
            }

            if(!Arrays.deepEquals(inputGrid, studentBoard.getGrid()))
            {
                System.out.println("FAILED!");
                System.out.println("Your 2D grid isn't correct");
                System.out.println("You didn't load the board properly");
                System.out.println("Should be: ");
                print2DArray(inputGrid);
                System.out.println("Yours is: ");
                print2DArray(studentBoard.getGrid());
                return;
            }

            System.out.println("Passed!");
        }
        catch(Exception e)
        {
            System.out.println("FAILED!");
            System.out.println("Exception was thrown while trying " +
                               "to load a board from a file");
            e.printStackTrace();
            return;
        }
        catch(OutOfMemoryError e)
        {
            System.out.println("FAILED!");
            System.out.println("Check how you are parsing the input " +
                               "file.  You are probably using the score\n" +
                               "as the GRID_SIZE which causes an exception " +
                               "when trying to create the grid array.");
            e.printStackTrace();
            return;
        }
    }

    private static void testAddRandomTile()
    {
        System.out.print("Testing addRandomTile Method................");

        try
        {
            Board studentBoard = new Board(new Random(SEED), 4);

            if(studentBoard.getGrid() == null)
            {
                System.out.println("FAILED!");
                System.out.println("Your grid (2D int array) is null " +
                                   "and hasn't been initialized!");
                return;
            }

            for(int i = 0; i < testGrid.length; i++)
            {
                if(!Arrays.deepEquals(testGrid[i], studentBoard.getGrid()))
                {
                    System.out.println("FAILED!");
                    System.out.println("Your 2D grid isn't correct");
                    System.out.println("Your addRandomTile method didn't " +
                                       "add the proper tile in the correct " +
                                       "location");
                    System.out.println("Should be: ");
                    print2DArray(testGrid[i]);
                    System.out.println("Yours is: ");
                    print2DArray(studentBoard.getGrid());
                    return;
                }

                studentBoard.addRandomTile();
            }

            System.out.println("Passed!");
        }
        catch (Exception e)
        {
            System.out.println("FAILED!");
            System.out.println("Exception was thrown while trying to " +
                               "run the addRandomTile Method");
            e.printStackTrace();
            return;
        }
    }

    private static void testSaveBoard() throws IOException,
                                               InterruptedException
    {
        System.out.print("Testing saveBoard Method....................");

        try
        {
            Board studentBoard = new Board(new Random(SEED), inputBoard);

            studentBoard.saveBoard(outputBoard);

            String command = "diff " + outputBoard + " " + inputBoard;
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();

            if(p.exitValue() == 0)
            {
                System.out.println("Passed!");
            }
            else
            {
                System.out.println("FAILED!");
                System.out.println("Your saveBoard method is incorrect");
                System.out.println("Run the following command to see the " +
                                   "differences:\n\t" + command);
                return;
            }
        }
        catch (Exception e)
        {
            System.out.println("FAILED!");
            System.out.println("Exception was thrown while trying to " +
                               "save the board to a file");
            e.printStackTrace();
            return;
        }
        catch(OutOfMemoryError e)
        {
            System.out.println("FAILED!");
            System.out.println("Check how you are parsing the input " +
                               "file.  You are probably using the score\n" +
                               "as the GRID_SIZE which causes an exception " +
                               "when trying to create the grid array.");
            e.printStackTrace();
            return;
        }
    }

    private static void print2DArray(int[][] array)
    {
       for(int i = 0; i < array.length; i++)
           System.out.println(Arrays.toString(array[i]));
    }
}
