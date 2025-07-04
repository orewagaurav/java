import java.util.*;
public class matrix {
    public static void main(String []args){
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter the row size: ");
        int m =sc.nextInt();
        System.out.println("Enter the column size: ");
        int  n = sc.nextInt(); 
        int [][] mat = new int[m][n];
        System.out.println("Enter the matrix elements: ");
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                mat[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(" "+mat[i][j]);
            }
            System.out.println();
        }
        System.out.println("Row length: "+mat.length);
        System.out.println("Column length: "+mat[0].length);

        //Transpose of the matrix
        int [][]transpose = new int[n][m];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                transpose[j][i] = mat[i][j];
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(" "+transpose[i][j]);
            }
            System.out.println();
        }
        sc.close();
    }
    


}
