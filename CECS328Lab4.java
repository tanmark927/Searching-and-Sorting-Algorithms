/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs328lab;
import static cecs328lab.CECS328Lab3.quick_sort;
import java.util.*;

/**
 * Determines average running time of heapsort, quicksort
 * and selection sort on an array
 * @author marktan
 */
public class CECS328Lab4 {
    
    private static final int MAX = 7000;
    private static final int MIN = -7000;
    private static final double secConv = 0.000000001;
    private static int n;
    
    /**
     * Sort an array using heap sort
     * @param a the array to be sorted
     */
    public static void heap_sort(int[] a)
    {
        n = a.length - 1;
        build_Maxheap(a);
        
        for(int i = n; i >= 0; i--)
        {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            n = n - 1;
            max_heapify(a,0);
        }
    }
    
    /**
     * Constructs a max-heap by repeated uses of max-heapify
     * @param a the array to be made into a max heap
     */
    public static void build_Maxheap(int[] a)
    {
        for(int i = n/2; i >= 0; i--)
            max_heapify(a,i);
    }
    
    /**
     * Transforms a binary heap into a max heap
     * @param a the array representing a binary heap
     * @param size the size of the array
     * @param index the index of the item to be made into a max heap
     */
    public static void max_heapify(int[] a, int index)
    {
        int max = index;
        int left = 2*index;
        int right = (2*index) + 1;
        if(left <= n && a[left] > a[max])
            max = left;
        if(right <= n && a[right] > a[max])
            max = right;
        if(index != max)
        {
            int temp = a[index];
            a[index] = a[max];
            a[max] = temp;
            max_heapify(a,max);
        }
    }
    
    /**
     * Sorts an array using selection sort
     * @param a the array to be sorted
     */
    public static void selection_sort(int[] a)
    {
        for(int i = 0; i < a.length - 1; i++)
        {
            int minInd = i;
            for(int j = i+1; j < a.length; j++)
                if(a[j] < a[minInd])
                    minInd = j;
            int temp = a[minInd];
            a[minInd] = a[i];
            a[i] = temp;
        }
    }
    
    public static void main(String[] args)
    {
        double heapsort = 0;    double quickSort = 0;   double selectionSort = 0;
        double heapsortStart,heapsortEnd,quickStart,quickEnd,selectStart,selectEnd;
        
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
            heapsortStart = System.nanoTime();
            heap_sort(a);
            heapsortEnd = System.nanoTime();
            heapsort += heapsortEnd-heapsortStart;
            
            for(int j = 0; j < n; j++)
                a[j] = r.nextInt(MAX-MIN+1)+MIN;
            quickStart = System.nanoTime();
            quick_sort(a,0,a.length-1);
            quickEnd = System.nanoTime();
            quickSort += quickEnd-quickStart;
            
            for(int j = 0; j < n; j++)
                a[j] = r.nextInt(MAX-MIN+1)+MIN;
            selectStart = System.nanoTime();
            selection_sort(a);
            selectEnd = System.nanoTime();
            selectionSort += selectEnd-selectStart;
        }
        
        double avgHeapsort = heapsort * secConv/rep;
        System.out.println("Average Heap Sort Time: " + avgHeapsort + " seconds");
        double avgQuick = quickSort * secConv/rep;
        System.out.println("Average Quick Sort Time: " + avgQuick + " seconds");
        double avgSelect = selectionSort * secConv/rep;
        System.out.println("Average Selection Sort Time: " + avgSelect + " seconds");

        in.close();
    }
}
