import java.util.*;
public class hello{
    public static void main(String [] args){
        System.out.println("Hello Badmoose");
        int num = 10;
        System.out.println(num);
        String str = "Hello Gaurav";
        System.out.println(str);
        int [] arr = {1,2,3,4,5,6,7};
        System.out.println("Array Length: " + arr.length);
        for(int i :arr){
            System.out.println(i);
        }
        String str1  = "Hello World";
        System.out.println("String is: "+str1);
        System.out.println(str1.toUpperCase());
        System.out.println(str.toLowerCase());
        System.out.println(str1.charAt(0));
        System.out.println(str1.indexOf("World"));
        System.out.println(str.substring(0,4));
        System.out.println(str1.contains("World"));
        System.out.println(str1.replace("World", "Gaurav"));
        int z = 1;
        System.out.println(z++ + --z);
        System.out.println(Integer.toBinaryString(z));
        char ch = 'A';
        System.out.println("Character: " + ch);
        System.out.println("Character to ASCII: " + (int)ch);
        Scanner sc  = new Scanner(System.in);
        System.out.println("Enter a Number: ");
        int inputNum  = sc.nextInt();
        System.out.println("You entered: " + inputNum);
        System.out.println("Enter a String: ");
        sc.nextLine(); // Consume the newline character
        String inputStr = sc.nextLine();
        System.out.println("You entered: " + inputStr);
        System.out.println("Goodbye " + inputStr);
        sc.close();
        System.out.println("End of Program");

    }
}