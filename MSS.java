package cecs328lab;
import java.util.*;

/**
 * Finds maximum subsequence sum using multiple techniques
 * @author marktan
 */
public class MSS {
    
    private static final int MAX = 10;
    private static final int MIN = -10;
    private static final double secConv = 0.000000001;
    
    public static int mssDC(int[] a, int begin, int end)
    {
        if(a.length == 1) { return a[0]; }
        int middle = (end-begin) + begin/2;
        return middle;
        //divide array in half
        //recursively call method on halved arrays
    }
    
    /**
     * Finds the MSS by using the max sum of subsequent array indices
     * @param a the array to find the sum
     * @return the maximum subsequent sum
     */
    public static int mssMax(int[] a)
    {
        int maxSum = 0;
        int sum = 0;
        for(int i = 0; i < a.length; i++)
        {
            sum += a[i];
            if(sum > maxSum)
                maxSum = sum;
            else if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }
    
    public static void main(String[] args)
    {
        double mssDC = 0; double mssMax = 0;
        double mssDCStart,mssDCEnd,mssMaxStart,mssMaxEnd;
        int mssMaxVal,mssDCVal;
        
        Scanner in = new Scanner(System.in);
        int n = 0; //n = 10000
        do
        {
            System.out.print("Enter a positive integer: ");
            while(!in.hasNextInt())
            {
                System.out.println("Invalid input. Please enter a positive integer: ");
                in.next();
            }
            n = in.nextInt();
        }while(n <= 0);
        
        System.out.print("Enter number of repetitions: ");
        int rep = in.nextInt(); //rep = 100
        
        Random r = new Random();
        int[] a = new int[n]; 
        
        for(int i = 0; i < rep; i++)
        {    
            for(int j = 0; j < n; j++)
                a[j] = r.nextInt(MAX-MIN+1)+MIN;
            
            System.out.println(Arrays.toString(a));
            mssDCStart = System.nanoTime();
            mssDCVal = mssDC(a,0,a.length - 1);
            mssDCEnd = System.nanoTime();
            mssDC += mssDCEnd-mssDCStart;
            System.out.println(Arrays.toString(a));
            System.out.println(mssDCVal);
            
            mssMaxStart = System.nanoTime();
            mssMaxVal = mssMax(a);
            mssMaxEnd = System.nanoTime();
            mssMax += mssMaxEnd-mssMaxStart;            
        }
        double avgMSSDC = mssDC * secConv/rep;
        System.out.println("Average MSS Divide & Conquer Time: " + avgMSSDC + " seconds");
        double avgMSSMax = mssMax * secConv/rep;
        System.out.println("Average MSS Max Time: " + avgMSSMax + " seconds");
        in.close();
    }
}
