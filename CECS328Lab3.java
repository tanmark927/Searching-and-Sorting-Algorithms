package cecs328lab;
import java.util.*;

/**
 * Determines average runtime for quicksort and insertion sort
 * @author marktan
 */
public class CECS328Lab3 {
    
    private static final int MAX = 7000;
    private static final int MIN = -7000;
    private static final double secConv = 0.000000001;

    /**
     * Sorts an array using quick sort
     * @param a the array to be sorted
     * @param begin the first index of the array
     * @param end  the last index of the array
     */
    public static void quick_sort(int[] a, int begin, int end)
    {        
        //checks for base case of array (size at most 5)
        if(end-begin <= 5) { insertion_sort(a); }
        else
        {
            int first,middle,last,aLeft,aRight;
        
            //Find pivot using median of three algorithm
            first = a[begin];
            middle = a[(end-begin)+begin/2];
            last = a[end];
            
            int min = Math.min(first, Math.min(middle,last));
            int max = Math.max(first, Math.max(middle,last));
            int pivot = first + middle + last - min - max;
            
            //Define left and right markers
            aLeft = begin;
            aRight = end;
            
            //Move left and right markers toward the center
            //Swap elements when both markers stop
            int temp = 0;
            
            while(aLeft <= aRight)
            {
                while(a[aRight] > pivot)
                    aRight--;
                while(a[aLeft] < pivot)
                    aLeft++;
                if(aLeft <= aRight)
                {
                    temp = a[aLeft];
                    a[aLeft] = a[aRight];
                    a[aRight] = temp; 
                    aLeft++;
                    aRight--;
                }
            }
            //Divide array into two sub-arrays            
            if(begin < aRight)
                quick_sort(a,begin,aRight);
            if(end > aLeft)
                quick_sort(a,aLeft,end);    
        }
    }
    
    /**
     * Sorts an array using insertion sort
     * @param a the array to be sorted
     * @return the sorted array
     */
    public static void insertion_sort(int[] a)
    {
        int value;
        for(int i = 0; i < a.length; i++)
        {
            value = a[i];
            while(i > 0 && a[i-1] > value)
            {
                a[i] = a[i-1];
                i--;
            }
            a[i] = value;
        }
    }
    
    public static void main(String[] args)
    {
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
        
        System.out.print("Enter number of repetitions: ");
        int rep = in.nextInt();
        
        Random r = new Random();
        int[] a = new int[n];        
        
        double quickSort = 0;
        double insertionSort = 0;
        double quickStart,quickEnd,insertStart,insertEnd;
                        
        for(int i = 0; i < rep; i++)
        {
            for(int j = 0; j < n; j++)
                a[j] = r.nextInt(MAX-MIN+1)+MIN;
            System.out.println(Arrays.toString(a));
            quickStart = System.nanoTime();
            quick_sort(a,0,a.length-1);
            quickEnd = System.nanoTime();
            quickSort += quickEnd-quickStart;
            System.out.println(Arrays.toString(a));

            for(int j = 0; j < n; j++)
                a[j] = r.nextInt(MAX-MIN+1)+MIN;

            insertStart = System.nanoTime();
            insertion_sort(a);
            insertEnd = System.nanoTime();
            insertionSort += insertEnd-insertStart;
        }    
        double avgQuick = quickSort * secConv/rep;
        double avgInsert = insertionSort * secConv/rep;
        double QuickPerSec = n*n/avgQuick;
        double InsertPerSec = n*n/avgInsert;

        System.out.println("Average Quick Sort Time: " + avgQuick + " seconds");
        System.out.println("Average Insertion Sort Time: " + avgInsert + " seconds");
        
        System.out.println();
        
        System.out.println("Quick Sort Instruction Rate Per Second: " + QuickPerSec);
        System.out.println("Insert Sort Instruction Rate Per Second: " + InsertPerSec);
        in.close();
    }
}
