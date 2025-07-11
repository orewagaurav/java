package Recursion;

public class sum_array {
    int sumArr(int[] arr){
        if(arr.length == 0){
            return 0;
        }
        int []arr2 = new int[arr.length-1];
        System.arraycopy(arr, 1, arr2, 0, arr.length-1);
        return arr[0]+ sumArr(arr2);
    }
    public static void main(String[] args) {
        sum_array obj = new sum_array();
        int []arr3 = {1,2,3,4,5,6,7};
        int result = obj.sumArr(arr3);
        System.out.println(result);
    }
}
