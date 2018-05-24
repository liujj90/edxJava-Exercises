import java.util.Scanner;

public class FractionCalculator {
    public static void intro(){
        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to Quit");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers");
    }

    public static Fraction getFraction(){
        // need to split on '/' to get numerator and denominator to initialise Fraction
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String fraction = input.next();
        // make sure that it is in form a/b
        boolean isFraction = checkFraction(fraction);
        while (isFraction == false){
            System.out.print("Not a valid fraction. Please enter (a/b) or integer (a): ");
            fraction = input.next();
            isFraction = checkFraction(fraction);
        }
        String[] temp = fraction.split("/");
        int[] ab = new int[temp.length];
        for (int i = 0; i < temp.length; i++ ){
            ab[i] = Integer.parseInt(temp[i]);
        }
        Fraction x = new Fraction(ab[0], ab[1]);
        return x;
    }
    public static String calculate(){
        System.out.print("Please enter operation(+,-,/,*,= or q to quit): ");
        Scanner input = new Scanner(System.in);
        String action = input.nextLine();
        Fraction answer = new Fraction(1,1);
        boolean answer2 = false;

        if (action.equals("q") || action.equals("Q")){
            System.exit(0);
        } else{
            Fraction a = getFraction();
            Fraction b = getFraction();
            if (action.equals("+")){
                answer = a.add(b);
            } else if (action.equals("-")){
                answer = a.subtract(b);
            } else if(action.equals("*")){
                answer = a.multiply(b);
            } else if (action.equals("/")){
                answer = a.divide(b);
            } else if (action.equals("=")) {
                answer2 = a.equals(b);
                if (answer2 == false){
                    return "false";
                } else{
                    return "true";
                }
            } else {
                System.out.println("it appears you have input an invalid action, please re-try");
                calculate();
            }
        }
        return answer.toString();
    }
    private static boolean checkFraction(String s){
        // find slash
        if (s.indexOf('/') == -1){
            try{
                Integer.parseInt(s);
            } catch (NumberFormatException e){
                return false;
            }
        }
        // find a and b, make sure they are valid integers

        String [] list = s.split("/");
        int[] temp = new int[list.length];
        if ((list.length != 2)){
            return false;
        }
        try {
            for(int i = 0; i < list.length; i++){
                temp[i] = Integer.parseInt(list[i]);
            }
        }catch(NumberFormatException e){
            return false; // return false if cannot convert substrings to integers
        }
        return true;

    }


    public static void main(String[] args){
        intro();
        String out =  calculate();
        System.out.println(out);

    }

}
