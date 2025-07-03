public class operation {
    public static void main(String[] args){
        int [] arr = {1,2,2,1,1,0};
        for(int i = 0;i<arr.length-1;i++){
            if(arr[i]==arr[i+1]){
                arr[i]= arr[i]*2;
                arr[i+1]=0;
            }
            else{
                continue;
            }
        }
        for(int i:arr){
            System.out.print(" "+i);
        }
        System.out.println();
        for(int i= 0;i<arr.length-1;i++){
            if(arr[i]==0){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
            else{
                continue;
            }
        }
        for(int i:arr){
            System.out.print(" "+i);
        }
    }
}
