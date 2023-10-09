public class Other {

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  // static methods like this one belong to the class and can be accesed anywhere by typing Other.clearscreen() like that. 

  
  
}