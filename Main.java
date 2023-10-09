import java.util.ArrayList;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);

    int gameBoardWidth = 28;
    int gameBoardHeight = 28;

    ArrayList<Integer> boardList = new ArrayList<Integer>();

    Other.clearScreen();
    System.out.println(
        "The Game of Life is not your typical computer game. It is a cellular automaton, and was invented by Cambridge mathematician John Conway. This game became widely known when it was mentioned in an article published by Scientific American in 1970. It consists of a grid of cells which, based on a few mathematical rules, can live, die or multiply. Depending on the initial conditions, the cells form various patterns throughout the course of the game.");
    System.out.println(
        "\nPRESS ENTER TO CONTINUE.");
    // gameBoardWidth = scan.nextInt();
    scan.nextLine();

    System.out.println(
        "\nRULES: For a space that is populated: Each cell with one or no neighbors dies, as if by solitude. Each cell with four or more neighbors dies, as if by overpopulation. Each cell with two or three neighbors survives. For a space that is empty or unpopulated: Each cell with three neighbors becomes populated.");
    System.out.println(
        "\nPRESS ENTER TO CONTINUE.");

    scan.nextLine();

    // try {
    // Runtime.getRuntime().exec(new String[] {
    // "sh",
    // "-c",
    // "stty -echo -icanon min 1 < /dev/tty"
    // });
    // that would put the terminal in raw mode. I dont think I need that though

    Other.clearScreen();

    System.out.print("\033[?25l");

    // board 1 is printed out
    Board board1 = new Board(gameBoardWidth, gameBoardHeight);
    board1.makeEndsIntoNegativeOnes(board1.arrayBoard);

    // Board prevBoard2 = new Board(gameBoardWidth, gameBoardHeight);
    // prevBoard2.makeEndsIntoNegativeOnes(prevBoard2.arrayBoard);

    Board board2 = new Board(gameBoardWidth, gameBoardHeight);
    board2.makeEndsIntoNegativeOnes(board2.arrayBoard);

    // do stuff that gets printed before in this section ends here.

    Other.clearScreen();

    // user needs to be able to see letters and then basically be able to place a
    // starting number of yellow tiles. Then the game will go on and they can add
    // more anytime that they want. with a code such as A8.

    boolean wantsToRunProgram = true;

    while (wantsToRunProgram) {

      boolean placing = true;
      while (placing == true) {
        Other.clearScreen();
        board2.print2dCharactersArray(board2.arrayBoard);

        System.out.println("\u001b[37m"
            + "We will be placing our yellow squares by indicating the position with the number and letter. For example, enter 'A1' or 'a1' to place a yellow square there. (caps does not matter for the letter).");

        System.out.println(
            "\nIMPORTANT: ONLY INPUT VALUES FROM A1 TO Z26. DO NOT ENTER VALUES OR CHARACTERS BEYOND THAT. YOU MAY NEED TO DO CNTRL- TO ZOOM OUT YOUR SCREEN TO SEE THE FULL BOARD.");

        System.out.println(
            "\nEnter the square you would like to place a yellow tile on then press the ENTER key to continue. Please make sure you enter the space in the format listed, letter than number (caps does not matter for the letter), then press enter. If you would like to stop placing tiles, type 'stop' and press enter."
                + "\u001b[37m");
        System.out.println();
        String inputTile;
        int inputColumnNumber = 0;
        int inputRowNumber = 0;

        inputTile = scan.nextLine();

        if (inputTile.equals("stop")) {
          placing = false;
        } else if (inputTile.length() == 2 || inputTile.length() == 3) {
          inputColumnNumber = (int) (inputTile.substring(0, 1).toUpperCase().charAt(0)) - 65 + 1;
          inputRowNumber = Integer.valueOf(inputTile.substring(1));

          board2.arrayBoard[inputColumnNumber][inputRowNumber] = 1;

        } else {
          System.out.println("\nPlease enter a valid square pick!" + "\u001b[37m");
          System.out.println(
              "\nPRESS ENTER TO TRY AGAIN AND CONTINUE.");
          // gameBoardWidth = scan.nextInt();
          scan.nextLine();
        }

        Other.clearScreen();
      }

      System.out.println("\u001b[37m"
          + "WHEN THE GAME STARTS, DO NOT ENTER OR TYPE ANYTHING. THIS WILL BREAK THE SIMULATION. The game will go on for 100 generations.");
      System.out.println("\nPress the ENTER key to continue." + "\u001b[37m");

      scan.nextLine();
      Other.clearScreen();
      board1.print2dCharactersArray(board1.arrayBoard);

      int generationNumber = 0;

      // code that was a test to try if the logic works
      // board2.arrayBoard[10][10] = 1;
      // board2.arrayBoard[10][12] = 1;
      // board2.arrayBoard[9][11] = 1;
      // board2.arrayBoard[11][11] = 1;
      // board2.arrayBoard[9][10] = 1;
      // board2.arrayBoard[10][9] = 1;
      try {

        boolean shouldRun = true;
        long tick = System.currentTimeMillis();

        // boolean newGame = true;

        int fps = 1;
        int skip = 1000 / fps;

        while (shouldRun) {

          if (generationNumber > 100) {
            shouldRun = false;
          }
          // program would go start here

          tick += skip;

          // you are supposed to update board2, which will then update board 1

          generationNumber++;

          // below I set the previous board to the current board;
          // prevBoard2.arrayBoard = board2.arrayBoard;
          // and the I set the new board to the updated previous board, updating meaning
          // it does the rules of the game of life.
          board2.arrayBoard = board2.update(board2.arrayBoard);

          // following code does the updating for the printed screen
          for (int y = 0; y < board1.boardHeight; y++) {
            for (int x = 0; x < board1.boardWidth; x++) {
              if (board1.arrayBoard[y][x] != board2.arrayBoard[y][x]) {
                board1.arrayBoard[y][x] = board2.arrayBoard[y][x];
                System.out.print("\033" + "[" + (1 + y) + ";" + (1 + x * 2) + "H");
                // need to do the 1 + x2 above for the x variable since we print out the squares
                // with two characters next to each other
                // System.out.print(board2.arrayBoard[y][x]);
                // need to do this for it to work
                if (board2.arrayBoard[y][x] == -1) {
                  System.out.print("\u001b[37m" + "██" + "\u001b[37m" + "");
                }
                if (board2.arrayBoard[y][x] == 0) {
                  System.out.print("\u001b[30m" + "██" + "\u001b[30m" + "");
                }
                if (board2.arrayBoard[y][x] == 1) {
                  System.out.print("\u001b[33m" + "██" + "\u001b[33m" + "");
                  // yellow is the color if a square that is "on"
                }
              }
            }
          }

          // following bracket closes the "while gamewon = false and gamelost = false"
          // doing this here so that it can draw and then end the game so that the last
          // layer is drawn
          // board1.print2dCharactersArray(board1.arrayBoard);

          // program would end here

          long sleep = tick - System.currentTimeMillis();
          if (sleep >= 0) {
            Thread.sleep(sleep);
          }
          // next bracket closes the "while shouldrun"
        }

        System.out.println("\033" + "[" + (30) + ";" + (0) + "H" + "\u001b[37m"
            + "The program has ended.");
        System.out.println(
            "\nInput 0 if you would like to play again. Input 1 if you would like to end the program." + "\u001b[37m");

        int playAgain = 0;
        playAgain = scan.nextInt();
        if (playAgain == 1) {
          wantsToRunProgram = false;
          Other.clearScreen();
        }
        if (playAgain == 0) {
          wantsToRunProgram = true;
          shouldRun = true;
          board2.clearInnerBoard(board2.arrayBoard);
          board1.clearInnerBoard(board1.arrayBoard);
          generationNumber = 0;
          Other.clearScreen();

        }

        // next line will close the while wantsPRogramToRun

      } catch (Exception e) {
        System.out.println(e);
        System.out.println("problem");
      }

    }

  }
}
// main method ends here;
