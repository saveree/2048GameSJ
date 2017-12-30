//------------------------------------------------------------------//
// Board.java                                                       //
//                                                                  //
// Class used to represent a 2048 game board                        //
//                                                                  //
// Author:  Saveree Joshipura A13349228 cs8bwaph                    //
// Date:    02/7/17                                                //
//------------------------------------------------------------------//

/**
 * Sample Board
 * <p/>
 * 0   1   2   3
 * 0   -   -   -   -
 * 1   -   -   -   -
 * 2   -   -   -   -
 * 3   -   -   -   -
 * <p/>
 * The sample board shows the index values for the columns and rows
 * Remember that you access a 2D array by first specifying the row
 * and then the column: grid[row][column]
 */

import java.util.*;
import java.io.*;

public class Board 
{
    public final int NUM_START_TILES = 2;
    public final int TWO_PROBABILITY = 90;
    public final int GRID_SIZE;

    private final Random random;
    private int[][] grid;
    private int score;

    /**Constructs a fresh board with random tiles
    * @param random, boardSize
    **/
    public Board(Random random, int boardSize) 
    {
        this.random = random; 
        this.score = score;
        GRID_SIZE = boardSize;
        grid = new int[boardSize][boardSize];
        addRandomTile();
        addRandomTile();
    }
 
    /** Construct a board based off of an input file
    * @param random, inputBoard
    * */
    public Board(Random random, String inputBoard) throws IOException 
    {
        this.random = random;
        Scanner input = new Scanner(new File(inputBoard));
        GRID_SIZE = input.nextInt();
        score = input.nextInt();
        grid = new int[GRID_SIZE][GRID_SIZE];
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                grid[i][j] = input.nextInt();
            }
        }
    }

    /** Saves the current board to a file
    * @param outputBoard
    * @return void 
    * */
    public void saveBoard(String outputBoard) throws IOException 
    {
        PrintWriter printFile = new PrintWriter(new File(outputBoard));
        printFile.println(grid.length);
        printFile.println(score);

        for(int i = 0 ; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                printFile.print(grid[i][j] + " ");
            }
            printFile.println();
        }
        printFile.close();   
    }

    // 
    /** Adds a random tile (of value 2 or 4) to a
      random empty space on the board
    * @return void 
    * */
    public void addRandomTile() 
    {
        int count = 0; 

        //count # of available tiles
        for(int i = 0 ; i < grid.length; i++) 
        {
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 0)
                {
                    count++;
                }
            }
        }
            
        //exit if count is 0 
        if(count == 0)
        {
            return;
        }
        
        //generates random # between 0 and count-1
        //generates a random # between 0 and 99
        int high = count;
        int location = random.nextInt(high);
        high = 100;
        int value = random.nextInt(high);
        int countEmpty = 0;

        //keeps count of empty spaces
        for(int i = 0 ; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] ==0)
                {
                    countEmpty++;
                }
                //when you hit the i'th random int, place new tile
                if(countEmpty==location+1)
                {
                    if(value < TWO_PROBABILITY)
                    {
                        grid[i][j] = 2;
                    }
                    else
                    {
                        grid[i][j] = 4;
                    }
                }   
            }
        }
    }

    /** Flips board horizontally or vertically, rotates it 
    clockwise or counterclockwise
    * @return void 
    * */
    public void flip(int change) 
    {
        //flip horizontally
        if(change == 1)
        {
            for(int i = 0; i <= GRID_SIZE-1; i++)
            {
                for(int j = 0; j <= (grid[i].length-1)/2;j++)
                {
                    int temp = grid[i][j];
                    grid[i][j] = grid[i][GRID_SIZE - j -1];
                    grid[i][GRID_SIZE - j - 1] = temp;
                }
            }
        }

        //flip vertically
        if(change == 2)
        {
            for(int i = 0; i <= (GRID_SIZE-1)/2; i++)
            {
                for(int j = 0; j <= grid[i].length-1; j++)
                {
                    int temp = grid[i][j];
                    grid[i][j] = grid[GRID_SIZE-i-1][j];
                    grid[GRID_SIZE-i-1][j] = temp;
                }
            }
        }

        //rotate clockwise 
        if(change ==3)
        {
            int arr[][] = new int[GRID_SIZE][GRID_SIZE];
    
            for(int i = 0; i <= GRID_SIZE-1; i++)
            {
                for(int j = 0; j <= GRID_SIZE-1; j++)
                {
                    arr[i][j] = grid[GRID_SIZE-j-1][i];

                }
            }
            for(int i = 0; i <= GRID_SIZE-1; i++)
            {
                for(int j = 0; j <= GRID_SIZE-1; j++)
                {
                    grid[i][j] = arr[i][j];
                }
            }
        }

        //rotate counterclockwise 
        if(change == 4)
        {
           int arr[][] = new int[GRID_SIZE][GRID_SIZE];
    
            for(int i = 0; i <= GRID_SIZE-1; i++)
            {
                for(int j = 0; j <= GRID_SIZE-1; j++)
                {
                    arr[i][j] = grid[j][GRID_SIZE-i-1];

                }
            }
            for(int i = 0; i <= GRID_SIZE-1; i++)
            {
                for(int j = 0; j <= GRID_SIZE-1; j++)
                {
                    grid[i][j] = arr[i][j];
                }
            }
        }
    }

    //Complete this method ONLY if you want to attempt at getting the extra credit
    //Returns true if the file to be read is in the correct format, else return
    //false
    public static boolean isInputFileCorrectFormat(String inputFile) {
        //The try and catch block are used to handle any exceptions
        //Do not worry about the details, just write all your conditions inside the
        //try block
        try {
            //write your code to check for all conditions and return true if it satisfies
            //all conditions else return false
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

   /** determines if board can moved in passed direction
    * @return boolean
    * @param direction 
    * */
   public boolean canMove(Direction direction)
   {

        if(direction == Direction.UP)
        {
            for(int i = 1 ; i < GRID_SIZE; i++) 
            {
                for(int j = 0; j < GRID_SIZE; j++) 
                {
                    if(grid[i-1][j] == 0 || grid[i-1][j] == grid[i][j])
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        else if(direction == Direction.DOWN)
        {
            for(int i = 0 ; i < grid.length-1; i++) 
            {
                for(int j = 0; j < grid[i].length; j++) 
                {
                    if(grid[i+1][j] == 0 || grid[i+1][j] == grid[i][j])
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        else if(direction == Direction.LEFT)
        {
            for(int i = 0 ; i < grid.length; i++) 
            {
                for(int j = 1; j < grid[i].length; j++) 
                {
                    if(grid[i][j-1] == 0 || grid[i][j-1] == grid[i][j])
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        else if(direction == Direction.RIGHT)
        {
            for(int i = 0 ; i < grid.length-1; i++) 
            {
                for(int j = 0; j < grid[i].length-1; j++) 
                {
                    if(grid[i][j+1] == 0 || grid[i][j+1] == grid[i][j])
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    /** moves the board up
    **/
    private void moveUp()
    {
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int j = 0; j < GRID_SIZE; j++) 
            {
                for(int i = 1; i < GRID_SIZE; i++) 
                {   
                    if(grid[i][j] != 0)
                    {
                        if(grid[i-1][j] == 0) 
                        {       
                            grid[i-1][j] = grid[i][j];
                            grid[i][j] = 0;
                        }
                    }
                }
            }
        }
    
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int j = 0; j < GRID_SIZE; j++) 
            {
                for(int i = 1; i < GRID_SIZE; i++) 
                {     
                    if(grid[i-1][j] == grid[i][j])
                    {
                        grid[i-1][j] = grid[i-1][j] + grid[i][j];
                        grid[i][j] = 0; 
                        score+= grid[i-1][j];
                    }
                }
            }
        }

        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int j = 0; j < GRID_SIZE; j++) 
            {
                for(int i = 1; i < GRID_SIZE; i++) 
                {   
                    for(int b = 0; b < GRID_SIZE-1; b++)
                    {
                        if(grid[i-1][j] == 0) 
                        {       
                            grid[i-1][j] = grid[i][j];
                            grid[i][j] = 0;
                        }
                    }
                }
            }
        }
    }
    /** moves the board down
    **/
    private void moveDown()
    {
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int j = 0; j < GRID_SIZE; j++) 
            {
                for(int i = 0; i < GRID_SIZE-1; i++) 
                {
                    if(grid[i][j] != 0)
                    {
                        if(grid[i+1][j] == 0) 
                        {
                            grid[i+1][j] = grid[i][j];
                            grid[i][j] = 0;
                        } 
                    }
                }
            }
        }
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int j = 0; j < GRID_SIZE; j++) 
            {
                for(int i = 0; i < GRID_SIZE-1; i++) 
                {
                    if(grid[i+1][j] == grid[i][j])
                    {
                        grid[i+1][j] = grid[i+1][j] + grid[i][j];
                        grid[i][j] = 0; 
                        score+= grid[i+1][j];
                    }
                }
            }
        }
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int j = 0; j < GRID_SIZE; j++) 
            {
                for(int i = 0; i < GRID_SIZE-1; i++) 
                {
                    if(grid[i+1][j] == 0) 
                    {
                        grid[i+1][j] = grid[i][j];
                        grid[i][j] = 0;
                    }
                }
            }
        }
    }
    /** moves the board left
    **/
    private void moveLeft()
    {
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int i = 0; i < GRID_SIZE; i++) 
            {
                for(int j = 1; j < GRID_SIZE; j++) 
                {
                    if(grid[i][j] != 0)
                    {
                        if(grid[i][j-1] == 0) 
                        {
                            grid[i][j-1] = grid[i][j];
                            grid[i][j] = 0;
                        }
                    }
                }
            }
        }
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int i = 0; i < GRID_SIZE; i++) 
            {
                for(int j = 1; j < GRID_SIZE; j++) 
                {
                    if(grid[i][j-1] == grid[i][j])
                    {
                        grid[i][j-1] = grid[i][j-1] + grid[i][j];
                        grid[i][j] = 0; 
                        score+= grid[i][j-1];
                    }
                }
            }
        }
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int i = 0; i < GRID_SIZE; i++) 
            {
                for(int j = 1; j < GRID_SIZE; j++) 
                {
                    if(grid[i][j] != 0)
                    {
                        if(grid[i][j-1] == 0) 
                        {
                            grid[i][j-1] = grid[i][j];
                            grid[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    /** moves the board right
    **/
    private void moveRight()
    {
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int i = 0; i < GRID_SIZE; i++) 
            {
                for(int j = 1; j < GRID_SIZE-1; j++) 
                {
                    if(grid[i][j] != 0)
                    {
                        if(grid[i][j+1] == 0) 
                        {
                            grid[i][j+1] = grid[i][j];
                            grid[i][j] = 0;
                        }
                    }
                }
            }
        }
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int i = 0; i < GRID_SIZE; i++) 
            {
                for(int j = 1; j < GRID_SIZE-1; j++) 
                {
                    if(grid[i][j+1] == grid[i][j])
                    {
                        grid[i][j+1] = grid[i][j+1] + grid[i][j];
                        grid[i][j] = 0; 
                        score+= grid[i][j+1];
                    }
                }
            }
        }
        for(int a = 0; a < GRID_SIZE-1; a++)
        {
            for(int i = 0; i < GRID_SIZE; i++) 
            {
                for(int j = 1; j < GRID_SIZE-1; j++) 
                {
                    if(grid[i][j] != 0)
                    {
                        if(grid[i][j+1] == 0) 
                        {
                            grid[i][j+1] = grid[i][j];
                            grid[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

   /** performs a move operation
    * @return boolean
    * @param direction 
    * */
   public boolean move(Direction direction) 
    {
        if(canMove(direction))
        {
            if(direction.equals(Direction.UP))
            {
                moveUp();
                return true;
            }
            else if(direction.equals(Direction.DOWN))
            {
                moveDown();
                return true;
            }
            else if(direction.equals(Direction.LEFT))
            {
                moveLeft();
                return true;
            }
            else if(direction.equals(Direction.RIGHT))
            {
                moveRight();
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

   /** checks to see if game is over 
    * @return boolean 
    * */
   public boolean isGameOver() 
    {
        if(canMove(Direction.UP) == false 
            && canMove(Direction.DOWN) == false 
            && canMove(Direction.LEFT) == false
            && canMove(Direction.RIGHT) == false) 
        {
            return true;
        }
        else
        {
           return false;
        }
    }

   /** return the reference to the 2048 grid
    * @return int[][]
    * */
    public int[][] getGrid() 
    {
        return grid;
    }

    // Return the score
    public int getScore() 
    {
        return score;
    }

    @Override
    public String toString() 
    {
        StringBuilder outputString = new StringBuilder();
        outputString.append(String.format("Score: %d\n", score));
        for (int row = 0; row < GRID_SIZE; row++) 
        {
            for (int column = 0; column < GRID_SIZE; column++)
                outputString.append(grid[row][column] == 0 ? "    -" :
                        String.format("%5d", grid[row][column]));

            outputString.append("\n");
        }
        return outputString.toString();
    }
}
