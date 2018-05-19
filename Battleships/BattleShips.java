import java.util.*;

public class BattleShips {

    public static void printMap(char [][] map){
        int xlen = map.length +2;
        int ylen = map[0].length +4;

        for (int i = 0; i <xlen; i ++){ // loops through a larger array to add the coordinates
            System.out.println(); // new line after each row
            for (int j = 0; j< (ylen); j++){
                if (i == 0 || i == xlen-1){ // first and last row
                    if (j > 1 && j < 12){ //add numbers between 3rd and 3rd last col
                        System.out.print(j-2); // print numbers
                    } else{
                        System.out.print(" "); // add spaces to start and end
                    }
                } else {
                    if (j == 0 || j == ylen-1){ // add row numbers
                        System.out.print(i-1);
                    } else if(j == 1 || j == ylen-2){
                        System.out.print("|"); // add borders
                    } else{
                        if (map[i-1][j-2] == '1') { // print where the player's ships are
                            System.out.print('@');
                        } else if (map[i-1][j-2] == '2'){
                            System.out.print(' '); // print blank spaces
                        } else if(map[i-1][j-2] == '0'){
                            System.out.print(' '); // print blank spaces
                        }else{
                            // print array
                            System.out.print(map[i-1][j-2]);
                        }
                    }
                }
            }
        }

    }

    public static void placeShips(char[][] map, int numShips){
        Scanner input = new Scanner(System.in);
        System.out.println("Deploy your ships:");
        while(numShips >0) {
            int x = map.length ;
            int y = map[0].length ;
            while ((x > map.length -1 || x < 0) || (y > map[0].length-1 || y < 0)) {
                System.out.print("Enter X coordinate for your ship: ");
                x = input.nextInt();
                if (x >= map.length) {
                    System.out.println("Invalid coordinates");
                }

                System.out.print("Enter Y coordinate for your ship: ");
                y = input.nextInt();
                if (y >= map[0].length) {
                    System.out.println("Invalid coordinates");
                }
            }
            if (map[x][y] == ' ') { // if empty add ship
                map[x][y] = '1';
                numShips -= 1;
            } else{
                System.out.println("There is already a ship there!");
            }
        }

    }

    public static void placeComputerShips(char[][] map, int numShips){
        System.out.println();
        System.out.println("Computer is deploying ships");
        int lenx = map.length;
        int leny = map[0].length ;
        Random rn = new Random();
        while (numShips>0){
            int x = rn.nextInt(lenx); // generate random numbers
            int y = rn.nextInt(leny);
            if (map[x][y] == ' '){ // if empty, place ships
                map[x][y] = '2';
                System.out.println("ship DEPLOYED");
                numShips -=1;
            }
        }
    }
    public static void intro(){
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println();
        System.out.println("Right now, the sea is empty.");
    }
    public static void playerTurn(char[][] map){
        Scanner input = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("YOUR TURN");
        System.out.print("Enter X Coordinates");
        int playerGuessX = input.nextInt();
        System.out.print("Enter Y Coordinates");
        int playerGuessY = input.nextInt();

        if (playerGuessX >= map.length || playerGuessY >= map[0].length || playerGuessX < 0 || playerGuessY <0){
            System.out.println("Invalid guess");
            playerTurn(map);
        } else {
            if (map[playerGuessX][playerGuessY] == '1') {
                map[playerGuessX][playerGuessY] = 'x';
                System.out.println("Oh no, you sunk your own ship :(");
            } else if (map[playerGuessX][playerGuessY] == '2') {//computer ship
                map[playerGuessX][playerGuessY] = '!';
                System.out.println("Boom! You sunk the computer's ship!");
            } else if (map[playerGuessX][playerGuessY] == ' ' || map[playerGuessX][playerGuessY] == '0') {
                map[playerGuessX][playerGuessY] = '-';
                System.out.println("Sorry, you missed");
            } else {
                System.out.println("You have already shot there :/");
            }
        }

    }

    public static void computerTurn(char[][] map){
        Random rn = new Random();
        System.out.println("\n");
        System.out.println("COMPUTER'S TURN");
        int guessX = rn.nextInt(map.length);
        int guessY = rn.nextInt(map[0].length);
        while (map[guessX][guessY] == '-' || map[guessX][guessY] == '!' || map[guessX][guessY] == '0' || map[guessX][guessY] == 'x'){
            computerTurn(map); // guess continually if invalid
        }
        if (map[guessX][guessY] == '1'){
            System.out.println("The Computer Sunk one of your ships!");
            map[guessX][guessY] = 'x';
        } else if (map[guessX][guessY] == '2'){
            System.out.println("The Computer sunk one of its own ships");
            map[guessX][guessY] = '!';
        } else {
            System.out.println("The Computer missed");
            map[guessX][guessY] = '0';
        }

    }
    public static int [] countShips(char[][] map){
        // count number of player and computer ships
        int playerShips =0;
        int compShips = 0;
        int[] numShips = new int [2];
        for (int i = 0; i<map.length;i++){
            for (int j = 0; j< map[0].length;j++){
                if (map[i][j] == '1'){
                    playerShips +=1;
                } else if (map[i][j] == '2'){
                    compShips += 1;
                }
            }
        }
        numShips[0] = playerShips;
        numShips[1] = compShips;


        return numShips;
    }
    public static void battle(char [][] map){
        int playerShips = countShips(map)[0];
        int compShips = countShips(map)[1];
        System.out.println("Your ships: " +playerShips + " | Computer ships: " + compShips);

        while (playerShips >0 && compShips > 0){ // while either side has ships, play game

            playerTurn(map);
            playerShips = countShips(map)[0];
            compShips = countShips(map)[1];
            printMap(map);
            computerTurn(map);
            playerShips = countShips(map)[0];
            compShips = countShips(map)[1];
            printMap(map);
            System.out.println("Your ships: " +playerShips + " | Computer ships: " + compShips);
        }
        if (countShips(map)[0] ==0){
            //player wins
            System.out.println("Hooray! you win the battle :)");
        } else if (countShips(map)[1] == 0){
            //computer wins
            System.out.println("Game over, the Computer wins");
        }

    }

    public static void main(String[] args){
        //build array
        char [][] map = new char [10][10]; //initialise array
        for (int i = 0; i < 10; i++){
            for (int j = 0; j <10; j++){
                map[i][j] = ' '; //add spaces to array
            }
        }
        intro();
        printMap(map);// pass array into printmap
        placeShips(map, 5); // place ships on map
        printMap(map);
        placeComputerShips(map, 5);
        battle(map);
    }
}
