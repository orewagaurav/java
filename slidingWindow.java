public class slidingWindow {
    public static void main(String []args){
        int []nums = {1,12,-5,-6,50,3};
        int k = 4;
        
        //SlidingWindow 
        int i=0;
        int j=0;
        int sum=0;
        int maxSum = Integer.MIN_VALUE;
        while(j<nums.length){
            int windowSize = j-i+1;
            sum +=nums[j];
            if(windowSize < k){
                j++;
            }
            else if(windowSize == k){
                maxSum = Math.max(maxSum,sum);
                sum -= nums[i];
                i++;
                j++;
            }
        }
        double result = (double)maxSum / k;
        System.out.println(result);
    }
}
