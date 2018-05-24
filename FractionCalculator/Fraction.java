public class Fraction {
    private int Numerator;
    private int Denominator = 1;

    public Fraction(int num, int denom){
        this.Numerator = num;
        if (denom == 0){
            throw new IllegalArgumentException();
        } else if (denom <0) {
            this.Numerator = -num;
        } else{
            this.Denominator = denom;
        }
    }

    public int getNumerator(){
        return Numerator;
    }

    public int getDenominator() {
        return Denominator;
    }

    public String toString(){
        return ("" + Numerator + "/" + Denominator );
    }

    public double toDouble(){
        return ((double)Numerator/ Denominator);
    }

    private int commonDenom(Fraction f){
        return (f.Denominator * this.Denominator);
    }

    private int scaledNum(Fraction f){
        return (this.Numerator * f.Denominator);
    }

    private int scaledOtherNum(Fraction f){
        return (f.Numerator * this.Denominator);
    }

    public Fraction add(Fraction f){
        // new denominator is a multiplication both denominators
        int newDenom = commonDenom(f);
        int newNum = scaledNum(f);
        int otherNum = scaledOtherNum(f);
        int sumNum = newNum + otherNum;
        return new Fraction(sumNum, newDenom);
    }

    public Fraction subtract(Fraction f){
        int newDenom = commonDenom(f);
        int newNum = scaledNum(f);
        int otherNum = scaledOtherNum(f);
        int subNum = otherNum - newNum;
        return new Fraction(subNum, newDenom);
    }

    public Fraction multiply(Fraction f){
        int newNum = f.Numerator*this.Numerator;
        int newDenom = commonDenom(f);
        return(new Fraction(newNum, newDenom));
    }

    public Fraction divide(Fraction f){
        int newNum = f.Numerator * this.Denominator;
        int newDenom = f.Denominator * this.Numerator;
        return(new Fraction(newNum, newDenom));
    }

    public static int gcd(int a, int b){
        while (a != 0 && b != 0){
            int remain = a%b;
            a = b;
            b = remain;
        }
        return a;
    }
    public void toLowestTerms(){
        int commonDenom = gcd(this.Numerator, this.Denominator);
        this.Numerator /= commonDenom;
        this.Denominator /= commonDenom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fraction){
            Fraction temp = (Fraction) obj;
            temp.toLowestTerms();
            int tempNom = temp.getNumerator();
            int tempDenom = temp.getDenominator();
            this.toLowestTerms();
            if (tempNom == this.Numerator && tempDenom == this.Denominator){
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }
}
