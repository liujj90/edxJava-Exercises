public class Crypto {
    public static void main(String[] args){
        String NewX = normalizeText("\"This is some \\\"really\\\" great. (Text)!?\"");
        String crypt = caesarify(NewX, 1);
        System.out.println(crypt);
        System.out.println(crypt.replaceAll("..", "$0 "));
        System.out.println(groupify(crypt, 3));
    }

    public static String normalizeText(String x){
        return x.replaceAll("[^a-zA-Z ]","").replaceAll(" ", "").toUpperCase();
    }
    public static String caesarify(String x, int i){
        String newAlphabet = shiftAlphabet(i);
        String newX = "";
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int ch = 0; ch < x.length(); ch++){
            char currChar = x.charAt(ch);
            int currInd = Alphabet.indexOf(currChar);
            newX += newAlphabet.charAt(currInd);
        }
        return newX;
    }
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static String groupify(String x, int i){
        if (x.length() % i != 0){
            String toAdd = "";
            for (int ch = 0; ch < (i - x.length()% i); ch++){
                toAdd+= "x";
            }

            x = x.concat(toAdd); // add 'x' to the end if not divisible by groups
        }
        String regexInput = "";
        for (int ri = 0; ri < i; ri++){
            regexInput += ".";
        }

        String newX = x.replaceAll(regexInput, "$0 ");
        // https://stackoverflow.com/questions/537174/putting-char-into-a-java-string-for-each-n-characters
        return newX;
    }

}
