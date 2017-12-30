//------------------------------------------------------------------//
// Gui2048.java                                                     //
//                                                                  //
// GUI Driver for 2048                                              //
//                                                                  //
// Author:  Saveree Joshipura                                       //
// Date:    03/8/2017                                               //
// PID: cs8bwaph A13349228                                          //
//------------------------------------------------------------------//


import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;

/** Sets up GUI2048 Game 
 **/
public class Gui2048 extends Application
{
    private String outputBoard; // The filename for where to save the Board
    private Board board; // The 2048 Game Board

    private static final int TILE_WIDTH = 106;

    private static final int TEXT_SIZE_LOW = 55; // Low value tiles (2,4,8,etc)
    private static final int TEXT_SIZE_MID = 45; // Mid value tiles
    //(128, 256, 512)
    private static final int TEXT_SIZE_HIGH = 35; // High value tiles
    //(1024, 2048, Higher)

    // Fill colors for each of the Tile values
    private static final Color COLOR_EMPTY = Color.rgb(238, 228, 218, 0.35);
    private static final Color COLOR_2 = Color.rgb(238, 228, 218);
    private static final Color COLOR_4 = Color.rgb(237, 224, 200);
    private static final Color COLOR_8 = Color.rgb(242, 177, 121);
    private static final Color COLOR_16 = Color.rgb(245, 149, 99);
    private static final Color COLOR_32 = Color.rgb(246, 124, 95);
    private static final Color COLOR_64 = Color.rgb(246, 94, 59);
    private static final Color COLOR_128 = Color.rgb(237, 207, 114);
    private static final Color COLOR_256 = Color.rgb(237, 204, 97);
    private static final Color COLOR_512 = Color.rgb(237, 200, 80);
    private static final Color COLOR_1024 = Color.rgb(237, 197, 63);
    private static final Color COLOR_2048 = Color.rgb(237, 194, 46);
    private static final Color COLOR_OTHER = Color.BLACK;
    private static final Color COLOR_GAME_OVER = Color.rgb(238, 228, 218, 0.73);

    private static final Color COLOR_VALUE_LIGHT = Color.rgb(249, 246, 242);
    // For tiles >= 8

    private static final Color COLOR_VALUE_DARK = Color.rgb(119, 110, 101);
    // For tiles < 8

    /*instance variables*/
    private GridPane pane;
    private GridPane pane2;
    public final int side = 4;
    private final int squareSize = 100;
    private String[][] cellValue;

    /** Start method 
    * @param primaryStage 
    * @return void 
    * */
    @Override
    public void start(Stage primaryStage)
    {
        // Process Arguments and Initialize the Game Board
        processArgs(getParameters().getRaw().toArray(new String[0]));

        // Create the pane that will hold all of the visual objects
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setStyle("-fx-background-color: rgb(187, 173, 160)");
        // Set the spacing between the Tiles
        pane.setHgap(15);
        pane.setVgap(15);

        //adds pane to scene, and scene to stage 
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Gui2048");
        primaryStage.setScene(scene);
        primaryStage.setWidth((side*squareSize)+((side-1)*15)+100);
        primaryStage.setHeight((side*squareSize)+((side-1)*15)+100);
        primaryStage.show();

        Text text = new Text();
        text.setText("2048");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        text.setFill(Color.BLACK);

        //adds text to grid
        pane.add(text, 0, 0, 2, 1);

        //sets text of score 
        Text textScore = new Text();
        textScore.setText("Score: " + board.getScore());
        textScore.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        text.setFill(Color.BLACK);

        //adds text of score to grid 
        pane.add(textScore, 2, 0, 2, 1);
        setUpTiles();
        scene.setOnKeyPressed(new myKeyHandler());
    }

    /** sets up title and score 
    * @return void 
    * */
    private void titleAndScore()
    {
        pane.getChildren().clear();
 
        Text text = new Text();
        text.setText("2048");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        text.setFill(Color.BLACK);

        //adds text to grid
        pane.add(text, 0, 0, 2, 1);

        //sets text of score 
        Text textScore = new Text();
        textScore.setText("Score: " + board.getScore());
        textScore.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        text.setFill(Color.BLACK);

        //adds text of score to grid 
        pane.add(textScore, 2, 0, 2, 1);
    }

  /** sets up tiles 
    * @return void 
    * */
    private void setUpTiles()
    {
        for(int i = 0; i < side; i++)
        {
            for(int j = 0; j < side; j++)
            {
                Rectangle cell=new Rectangle(squareSize, squareSize, Color.GRAY);
                String number=Integer.toString(board.getGrid()[i][j]);
                Text text1 = new Text();
                text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
                GridPane.setHalignment(text1, HPos.CENTER);

                if(board.getGrid()[i][j]>0)
                text1.setText(number);
                pane.add(cell, j,i+1);
                pane.add(text1, j,i+1);

                if(board.getGrid()[i][j] == 2)
                {
                    cell.setFill(COLOR_2);
                }
                else if(board.getGrid()[i][j] == 4)
                {
                    cell.setFill(COLOR_4);
                }
                else if(board.getGrid()[i][j] == 8)
                {
                    cell.setFill(COLOR_8);
                }
                else if(board.getGrid()[i][j] == 16)
                {
                    cell.setFill(COLOR_16);
                }
                else if(board.getGrid()[i][j] == 32)
                {
                    cell.setFill(COLOR_32);
                }
                else if(board.getGrid()[i][j] == 64)
                {
                    cell.setFill(COLOR_64);
                }
                else if(board.getGrid()[i][j] == 128)
                {
                    cell.setFill(COLOR_128);
                }
                else if(board.getGrid()[i][j] == 256)
                {
                    cell.setFill(COLOR_256);
                }
                else if(board.getGrid()[i][j] == 512)
                {
                    cell.setFill(COLOR_512);
                }
                else if(board.getGrid()[i][j] == 1024)
                {
                    cell.setFill(COLOR_1024);
                }
                else if(board.getGrid()[i][j] == 2048)
                {
                    cell.setFill(COLOR_2048);
                }
            }
        }
    }


   /** KeyHandler 
    **/
    private class myKeyHandler implements EventHandler<KeyEvent>
    {
       /**KeyHandler 
        * @param KeyEvent e 
        * @return void 
        **/
        @Override
        public void handle(KeyEvent e) 
        {
            KeyCode code = e.getCode();
            if(board.isGameOver() != true)  
            {
                switch(code)
                {
                    case LEFT: //if pressed the left key 
                    if(board.canMove(Direction.LEFT)==true)
                        board.move(Direction.LEFT);
                        System.out.println("Moving Left");
                        pane.getChildren().clear();
                        board.addRandomTile();
                        titleAndScore();
                        setUpTiles();
                    break;
                    case RIGHT: //if pressed the right key 
                    if(board.canMove(Direction.RIGHT)==true)
                        board.move(Direction.RIGHT);
                        System.out.println("Moving Right");
                        pane.getChildren().clear();
                        board.addRandomTile();
                        titleAndScore();
                        setUpTiles();
                    break;
                    case UP: //if pressed the up key 
                    if(board.canMove(Direction.UP)==true)
                        board.move(Direction.UP);
                        System.out.println("Moving Up");
                        pane.getChildren().clear();
                        titleAndScore();
                        board.addRandomTile();
                        setUpTiles();
                    break;
                    case DOWN: //if pressed the down key 
                    if(board.canMove(Direction.DOWN)==true)
                        board.move(Direction.DOWN);
                        System.out.println("Moving Down");
                        pane.getChildren().clear();
                        board.addRandomTile();
                        titleAndScore();
                        setUpTiles();
                    break;
                    case S: //if pressed the s key 
                        System.out.println("Saving board to "+ outputBoard);
                        break;
                }
            }  
            //if game is over 
            if(board.isGameOver() == true)
            {
                pane2 = new GridPane();
                pane2.setAlignment(Pos.CENTER);
                pane2.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

                Text textScore2 = new Text();
                textScore2.setText("Game Over!");
                textScore2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 100));
                textScore2.setFill(Color.GRAY);

                pane2.add(textScore2, 2, 0, 2, 1);

                try 
                {
                    board.saveBoard(outputBoard);
                    System.out.println("Board saved to " + outputBoard);
                } 
                catch (IOException x) 
                {
                    System.out.println("saveBoard threw an exception");
                }
            }           
        }
    }


    /** DO NOT EDIT BELOW */

    // The method used to process the command line arguments
    private void processArgs(String[] args)
    {
        String inputBoard = null;   // The filename for where to load the Board
        int boardSize = 0;          // The Size of the Board

        // Arguments must come in pairs
        if((args.length % 2) != 0)
        {
            printUsage();
            System.exit(-1);
        }

        // Process all the arguments
        for(int i = 0; i < args.length; i += 2)
        {
            if(args[i].equals("-i"))
            {   // We are processing the argument that specifies
                // the input file to be used to set the board
                inputBoard = args[i + 1];
            }
            else if(args[i].equals("-o"))
            {   // We are processing the argument that specifies
                // the output file to be used to save the board
                outputBoard = args[i + 1];
            }
            else if(args[i].equals("-s"))
            {   // We are processing the argument that specifies
                // the size of the Board
                boardSize = Integer.parseInt(args[i + 1]);
            }
            else
            {   // Incorrect Argument
                printUsage();
                System.exit(-1);
            }
        }

        // Set the default output file if none specified
        if(outputBoard == null)
            outputBoard = "2048.board";
        // Set the default Board size if none specified or less than 2
        if(boardSize < 2)
            boardSize = 4;

        // Initialize the Game Board
        try{
            if(inputBoard != null)
                board = new Board(new Random(), inputBoard);
            else
                board = new Board(new Random(), boardSize);
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() +
                    " was thrown while creating a " +
                    "Board from file " + inputBoard);
            System.out.println("Either your Board(String, Random) " +
                    "Constructor is broken or the file isn't " +
                    "formated correctly");
            System.exit(-1);
        }
    }

    // Print the Usage Message
    private static void printUsage()
    {
        System.out.println("Gui2048");
        System.out.println("Usage:  Gui2048 [-i|o file ...]");
        System.out.println();
        System.out.println("  Command line arguments come in pairs of the "+
                "form: <command> <argument>");
        System.out.println();
        System.out.println("  -i [file]  -> Specifies a 2048 board that " +
                "should be loaded");
        System.out.println();
        System.out.println("  -o [file]  -> Specifies a file that should be " +
                "used to save the 2048 board");
        System.out.println("                If none specified then the " +
                "default \"2048.board\" file will be used");
        System.out.println("  -s [size]  -> Specifies the size of the 2048" +
                "board if an input file hasn't been");
        System.out.println("                specified.  If both -s and -i" +
                "are used, then the size of the board");
        System.out.println("                will be determined by the input" +
                " file. The default size is 4.");
    }
}
