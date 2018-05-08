import java.util.Scanner;

public class MazeRunner {

    public static void intro(Maze maze){
        System.out.println("Welcome to Maze Runner!\nHere is your current position:");
        maze.printMap();
    }
    public static String userMove(){
        Scanner input = new Scanner(System.in);
        System.out.print("Where would you like to move: (R,L,U,D) ");
        String movement = input.next();
        if (movement.equals("R") || movement.equals("L") || movement.equals("U") || movement.equals("D")){
        } else{
            System.out.print("You can only move (R,L,U,D)");
            movement = userMove();
        }
        return movement;
    }

    public static boolean checkMove(Maze maze, String dir){
        if (dir.equals("R") ){
            return maze.canIMoveRight();
        } else if(dir.equals("L")){
            return maze.canIMoveLeft();
        } else if(dir.equals("U")){
            return maze.canIMoveUp();
        } else{
            return maze.canIMoveDown();
        }

    }

    public static void advance(Maze maze, String dir){
        // advace in direction if movement is possible

        if (dir.equals("R")) {
            maze.moveRight();
        } else if (dir.equals("L")){
            maze.moveLeft();
        } else if (dir.equals("U")){
            maze.moveUp();
        } else{
            maze.moveDown();
        }
        maze.printMap();
    }

    public static void game(Maze maze){
        int Counter = 0;
        while (Counter < 100){
            String userChoice = userMove();
            if (checkMove(maze, userChoice) == true) {
                advance(maze, userChoice);
            } else if (maze.isThereAPit(userChoice) == true) { // is there a pit?
                navigatePit(maze, userChoice);
            } else { //must be a wall
                hitWall(maze);
            }
            System.out.println(movesMessage(Counter));
            Counter +=1;
        }
        // if counter reaches 100, you lose
        System.out.println("Sorry, but you didn't escape in time- you lose!");
        System.exit(0);
    }

    public static String movesMessage(int moves){
        if (moves == 49){
            return "Warning: You have made 50 moves, you have 50 remaining before the maze exit closes";
        } else if(moves == 74){
            return "Alert! You have made 75 moves, you only have 25 moves left to escape.";
        } else if(moves == 89){
            return "DANGER! You have made 90 moves, you only have 10 moves left to escape!!";
        } else if(moves == 99){
            return "Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[";
        } else{
            return "";// return empty string if not any of these
        }
    }
    public static void hitWall(Maze maze){
        System.out.println("Sorry you've hit a wall");
        maze.printMap();
    }

    public static void navigatePit(Maze maze, String dir){
        Scanner input = new Scanner(System.in);
        System.out.print("Watch out! There's a pit ahead, jump it? (yes/no) ");
        String decision = input.next();
        if (decision.equals("yes")){
            maze.jumpOverPit(dir);
            maze.printMap();
        } else{
            System.out.println("You're stuck then :/");
            maze.printMap();
        }
    }
    public static void main(String[] args){
        Maze myMap = new Maze();
        intro(myMap);
        while(myMap.didIWin() == false){
            game(myMap);
            myMap.didIWin();
        }
        System.out.println("Congratulations, you hav made it out alive!");

    }

}
