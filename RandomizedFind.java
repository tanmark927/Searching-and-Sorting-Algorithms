/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs328lab;
import java.util.*;
/**
 *
 * @author marktan
 */
public class RandomizedFind {
    private static final int MAX = 50;
    private static final int MIN = -50;
    private static final double secConv = 0.000000001;
    
    public static int RFind(int[] a, int begin, int end, int k)
    {
        int find = 0;
        if(end-begin <= 1)
            find = a[end-begin];
        else
        {
            int first,middle,last,aLeft,aRight;
        
            first = a[begin];
            middle = a[(end-begin)+begin/2];
            last = a[end];
                        
            int min = Math.min(first, Math.min(middle,last));
            int max = Math.max(first, Math.max(middle,last));
            int pivot = first + middle + last - min - max;
            
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
            //if(a[a.length-1] == pivot)
            //    find = a[a.length-1];
            if(begin < aRight && a[k-1] < pivot)
                find = RFind(a,begin,aRight,k);
            else if(end > aLeft && a[k-1] > pivot)
                find = RFind(a,aLeft,end,k);   
        }
        return find;
    }
    
    public static void main(String[] args)
    {
        double RFind = 0;
        double RFindStart,RFindEnd;
        int RFindVal;
        
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
        
        System.out.print("Enter the kth least value in the array: ");
        int k = in.nextInt();
        
        Random r = new Random();
        int[] a = new int[n]; 
            
        for(int j = 0; j < n; j++)
                a[j] = r.nextInt(MAX-MIN+1)+MIN;
        
        System.out.println(Arrays.toString(a));
        
        for(int i = 0; i < rep; i++)
        {     
            RFindStart = System.nanoTime();
            RFindVal = RFind(a,0,a.length-1,k);
            RFindEnd = System.nanoTime();
            RFind += RFindEnd-RFindStart;
            System.out.println(RFindVal);
        }
        double avgRFind = RFind * secConv/rep;
        System.out.println("Average Randomized Find Time: " + avgRFind + " seconds");
        in.close();
    }
}
