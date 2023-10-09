public class Board {

  public int boardWidth = 0;
  public int boardHeight = 0;
  public int[][] arrayBoard;

  public Board(int boardWidth, int boardHeight) {
    // width goes first in creation of board object.
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    arrayBoard = new int[boardHeight][boardWidth];

  }

  public void makeEndsIntoNegativeOnes(int[][] arr2d) {
    for (int i = 0; i < boardWidth; i++) {
      arr2d[0][i] = -1;
      arr2d[boardHeight - 1][i] = -1;
    }
    for (int i = 0; i < boardHeight; i++) {
      arr2d[i][0] = -1;
      arr2d[i][boardWidth - 1] = -1;
    }
  }

 public int[][] update(int[][] arr2d) {
    int[][] arr2dTwo = new int[boardHeight][boardWidth];
    makeEndsIntoNegativeOnes(arr2dTwo);
   
    for (int i = 0; i < boardHeight; i++) {

      for (int j = 0; j < boardWidth; j++) {

        if (arr2d[i][j] == 1)
        {
          // four or more around it dies, one or less dies too 
          if (numberPopulatedAround(arr2d, i, j) > 3 || numberPopulatedAround(arr2d, i, j) < 2)
          {
            arr2dTwo[i][j] = 0;
          } else {
            arr2dTwo[i][j] = 1;
          }
          
        } 
        else
        {
          if (arr2d[i][j] == 0)
        {
          if (numberPopulatedAround(arr2d, i, j) == 3)
          {
            arr2dTwo[i][j] = 1;
          }
        }
        }
      }   
    }  

   return arr2dTwo;
   
  }

  public int numberPopulatedAround(int[][] arr2d, int i, int j) {
    int count = 0;

     if (arr2d[i - 1][j - 1] == 1) {
            count++;
          }

          if (arr2d[i][j - 1] == 1) {
            count++;
          }

          if (arr2d[i + 1][j - 1] == 1) {
            count++;
          }

          if (arr2d[i + 1][j] == 1) {
            count++;
          }

          if (arr2d[i + 1][j + 1] == 1) {
            count++;
          }

          if (arr2d[i][j + 1] == 1) {
            count++;
          }

          if (arr2d[i - 1][j + 1] == 1) {
            count++;
          }

          if (arr2d[i - 1][j] == 1) {
            count++;
          }

    return count;
  }

  

  public void print2dIntArray(int[][] arr2d) {
    for (int i = 0; i < boardHeight; i++) {

      for (int j = 0; j < boardWidth; j++) {

        System.out.print(arr2d[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public void print2dCharactersArray(int[][] arr2d) {
    for (int i = 0; i < boardHeight; i++) {

      for (int j = 0; j < boardWidth; j++) {
        // printing special characters based off of the number here.

        if (arr2d[i][j] == -1) {
          System.out.print("\u001b[37m" + "██" + "\u001b[37m" + "");
        }
        if (arr2d[i][j] == 0) {
          System.out.print("\u001b[30m" + "██" + "\u001b[30m" + "");
        }
        if (arr2d[i][j] == 1) {
          System.out.print("\u001b[33m" + "██" + "\u001b[36m" + "");
        }
      }
      System.out.println();
    }
    System.out.println();
  }





public void clearInnerBoard(int[][] arr2d) {

    for (int i = 1; i < boardHeight - 1; i += 1) {

      for (int j = 1; j < boardWidth - 1; j++) {

        arr2d[i][j] = 0;

      }
    } 
  }



  
  }