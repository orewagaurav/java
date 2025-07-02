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

    }
}