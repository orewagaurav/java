package Recursion;

public class one {
    void printNumbers(int lower,int upper){
        if(lower > upper) 
            return;
        System.out.println(lower);
        printNumbers(lower+1, upper);
        // System.out.println(lower);
    }
    public static void main(String []args){
        one obj = new one();
        obj.printNumbers(1,10);
    }
}
