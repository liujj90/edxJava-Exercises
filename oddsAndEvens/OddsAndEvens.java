/*
Module  2 Project - Odds or Evens
User plays against computer, and gets first pic
choose your side, then eacy player picks an integer between 0-5. The winner is determined by whether the sum of your fingers is odd or even
*/
import java.utils.Scanner

public class OddsAndEvens{
	public static void main(String[] args){

	}

	public static void game(){
		Scanner input = new Scanner();
		System.out.println("Let's play a game called \"Odds and Evens \"");
		System.out.print("What is your name? ");
		String userName = input.nextLine();
		System.out.print("Hi "+ userName +', Which do you choose? (O)dds or (E)vens ');
		String userSide = input.nextLine();
		if userSide.equals("O"){
			System.out.print(userName + " has picked odds! The computer will be evens");
		 } else if userSide.equals("E"){
		 	System.out.print(userName + " has picked evens! The computer will be odds");
		 } else{ // user picked something other than O or E
		 	System.out.print("Invalid choice")
		 }
		


	}
}

