package cecs328lab;
import java.util.*;

public class CECS328Lab2 {
    
    private static int[] nums, help;
    private static int no;
    private static final int MAX = 5000;
    private static final int MIN = -5000;
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
     * Sorts an array using merge sort
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
        for(int i = 0; i < n; i++)
            a[i] = r.nextInt(MAX-MIN+1)+MIN;
        
        sort(a);
        
        a[n-1]= 7000;
        int key = 7000;
                
        long linStart = System.nanoTime();
        linearSearch(a,key);
        long linEnd = System.nanoTime();
        long totalLinear = linEnd-linStart;
        System.out.println();
        System.out.println("Linear Search Time: " + totalLinear * secConv + " seconds");
        System.out.println("Linear Search Step Time: " + totalLinear * secConv / n + " seconds");
                
        long binStart = System.nanoTime();
        binarySearch(a,key);
        long binEnd = System.nanoTime();
        long totalBinary = binEnd-binStart;
        System.out.println("Binary Search Time: " + totalBinary * secConv + " seconds");
        System.out.println("Binary Search Step Time: " + totalBinary * secConv/(Math.log(n)/Math.log(2)) + " seconds");
        
        System.out.println();
        
        int sizeNew = 10000000;
        System.out.println("Runtime Predictions for n = 10^7");
        System.out.println("Linear Search Time: " + totalLinear * secConv * sizeNew / n+ " seconds");
        System.out.println("Binary Search Time: " + totalBinary * secConv * (Math.log(sizeNew)/Math.log(2)) / (Math.log(n)/Math.log(2))+ " seconds");
    
        System.out.println("Actual Runtime for n = 10^7");
        a = new int[sizeNew];
        for(int i = 0; i < sizeNew; i++)
            a[i] = r.nextInt(MAX-MIN+1)+MIN;
        
        linStart = System.nanoTime();
        linearSearch(a,key);
        linEnd = System.nanoTime();
        totalLinear = linEnd-linStart;
        System.out.println();
        System.out.println("Linear Search Time: " + totalLinear * secConv + " seconds");
                
        binStart = System.nanoTime();
        binarySearch(a,key);
        binEnd = System.nanoTime();
        totalBinary = binEnd-binStart;
        System.out.println("Binary Search Time: " + totalBinary * secConv + " seconds");
    }
}
