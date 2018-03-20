package cecs328lab;
import java.util.*;

public class CECS328Lab1 {

    private static int[] nums, help;
    private static int no;
    private static final int MIN = -1000;
    private static final int MAX = 1000;
    private static final double secConv = 0.000000001;
    
    /**
     * Initializes the array to be sorted
     * @param a the array to be sorted
     */
    public static void sort(int[] a)
    {
        nums = a;
        no = a.length;
        help = new int[no];
        mergesort(0,no - 1);
    }
    
    /**
     * Divides and conquers subarray
     * @param min the first index of the array
     * @param max the last index of the array
     */
    private static void mergesort(int min, int max)
    {
        if(min < max)
        {
            int mid = min + (max - min) / 2;
            mergesort(min,mid);
            mergesort(mid+1,max);
            merge(min,mid,max);
        }
    }
    
    /**
     * Merge sorts an array using three specific indices
     * @param min the first index of the array
     * @param mid the middle index of the array
     * @param max the last index of the array
     */
    private static void merge(int min, int mid, int max)
    {
         for (int i = min; i <= max; i++)
         {
             help[i] = nums[i];
         }

         int i = min;
         int j = mid + 1;
         int k = min;
         while (i <= mid && j <= max)
         {
             if (help[i] <= help[j])
             { nums[k] = help[i]; i++; }
             else
             { nums[k] = help[j]; j++; }
             k++;
         }
         while (i <= mid)
         {
             nums[k] = help[i]; k++; i++;
         }
    }
    
    /**
     * Finds a key using linear search
     * @param a the array to be searched
     * @param key the element to be searched
     * @return if the key is in the array
     */
    public static boolean linearSearch(int[] a, int key)
    {
        boolean found = false;
        int ind = 0;
        while(ind < a.length)
        {
            if(a[ind] == key)
                found = true;
            ind++;
        }
        return found;
    }
    
    /**
     * Finds a key using binary search
     * @param a the array to be searched
     * @param key the element to be searched
     * @return the element's index in the array
     */
    public static int binarySearch(int[] a, int key)
    {
        int low = 0;
        int high = a.length - 1;
        while(low <= high)
        {
            int mid = (low+high)/2;
            if(a[mid] == key) { return mid; }
            else if(a[mid] > key) { high = mid - 1; }
            else { low = mid + 1; }
        }
        return -1;
    }
    
    public static void main(String[] args)
    {
        Random r = new Random();
        Scanner in = new Scanner(System.in);
        int n = 0;
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
        int[] a = new int[n];
        System.out.print("Enter number of repetitions: ");
        int rep = in.nextInt();
        
        for(int i = 0; i < n; i++)
            a[i] = r.nextInt(MAX-MIN+1)+MIN;
        
        sort(a);
        
        int key;
        double totalLinear = 0;
        double totalBinary = 0;
        double linStart,linEnd,binStart,binEnd;
        

        for(int i = 0; i < rep; i++)
        {
            key = a[r.nextInt(n-1)];
            
            linStart = System.nanoTime();
            linearSearch(a,key);
            linEnd = System.nanoTime();
            totalLinear += linEnd-linStart;
            
            binStart = System.nanoTime();
            binarySearch(a,key);
            binEnd = System.nanoTime();
            totalBinary += binEnd-binStart;
            
        }
        
        double avgLin = totalLinear * secConv/rep;
        System.out.println("Average Linear Search Time: " + avgLin + " seconds");
        
        double avgBin = totalBinary * secConv/rep;
        System.out.println("Average Binary Search Time: " + avgBin + " seconds");
        in.close();
    }
}
