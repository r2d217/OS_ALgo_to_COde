
package javaapplication1;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author R2D217
 */
public class Bankersalgo {
    public static void main(String arg[])
    {
        int n,m;//number of processes & types of resources
        Scanner in = new Scanner(System.in); //object of Scanner class to take input
        //accept user input for number of processes and type of resources
        n=in.nextInt();
        m=in.nextInt();
        String x="";
        //to store available resources
        int avail[] = new int[m];
        //total resources present in our system(avail+allocated)
        int total[] = new int [m];
        //terminated process is checked with help of this boolean array 
        boolean work []= new boolean [n];
        for(int i=0;i<n;i++)
        {
            work[i]=false;
        }
        System.out.println("Available:");
        for(int i=0;i<m;i++)
        {
            avail[i]=in.nextInt();
        }
        /*array to store maximum resources required by 
          an process column represent diffternt type 
            of resources and row different process*/
        int max[][] = new int[n][m];
         /*array to store alocated resources to
            process column represent diffternt type 
            of resources and row different process*/
        int allocated[][] = new int[n][m];
        System.out.println("MAX");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                max[i][j]=in.nextInt();// Rj max required by Pi
            }
        }
        System.out.println("Allocated");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                allocated [i][j]=in.nextInt();// Rj alloted to pi
            }
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
               total[i]+=allocated[j][i];
            }
            total[i]+=avail[i];
        }
        // needed to fullfill processes hunger
        int need [][] = new int [n][m];//max-allocated
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                need [i][j]= max[i][j]-allocated[i][j];
            }
        }
       
        // comapre need and availabe matrix to find the safe sequence
        int flag=0,count=0;
        while(count<n)
        {
        for(int i=0;i<n;i++)
        {   if(work[i]==false)//to elminante checking of already terminated process
            {
            for(int j=0;j<m;j++)
            {
                if(avail[j]-need[i][j]<0)
                {  
                    flag=1;
                    break;
                }
            }
            if(flag==0)
            {
                work[i]=true;
                 for(int j=0;j<m;j++)
                 {
                     avail[j]+=allocated[i][j]; // adding resource back that are rleased after completion of process
                 }
                 x+=""+i+"->";
                
            }
            flag=0;
            }
        }
        count++;
        }
      
       for(int i=0;i<m;i++)
       {
           System.out.print(avail[i]);
       }
        System.out.print(x);
    }
}
