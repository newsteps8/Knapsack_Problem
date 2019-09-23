//Büşra_Gökmen
//150116027

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Knapsackproblem1 {

    static int i = 0;
    static int capacity  = 0;//capacity of knapsack
    static int s = 0;
    static int indexx ;
    static int o= 0;
    static int N = 0;//size of value,rate array
    static int M = 0;//capacity



    public static int find(double[] a, double target)//find index of value is maximum
    {
        for (int k = 0; k < a.length; k++)
            if (a[k] == target)
                return k;

        return -1;
    }

    public static void main(String[] args )throws IOException {
        long startTime_1 = System.nanoTime();//time of all of code


        try (BufferedReader nr = new BufferedReader(new FileReader("test1a.dat")))
        {//read the input file
            String line = null;
            while ((line = nr.readLine()) != null && o==0) {

                String[] parts = line.split(" ");

                int next = Integer.parseInt(parts[0]);
                int next2 = Integer.parseInt(parts[1]);

                if(o==0) {
                    N = next;//size
                    M = next2;//capacity
                    capacity = M;
                }

                o++;
            }
        }
        catch (Exception expe)
        {
            expe.printStackTrace();
        }


        int []Selected = new int[N];//array to find whether item is choosen or not
        int [] Value = new int[N];// array to keep values of items
        int [] Weight = new int[N];//array to keep weights of items
        double [] Rate= new double[N];//array to keep value/weight rate values of items
        int Total_value= 0;//total value in knapsack


        try (BufferedReader br = new BufferedReader(new FileReader("test1a.dat"))) {
            String line = null;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" ");

                int next = Integer.parseInt(parts[0]);
                int next2 = Integer.parseInt(parts[1]);

                if (i >= 1) {
                    Value[s] = next;//value of items
                    Weight[s] = next2;//weight of items
                    Rate[s] = (double) Value[s] / Weight[s];//rate value of items
                    s = s + 1;
                }


                i++;
            }
        } catch (Exception expe) {
            expe.printStackTrace();
        }

        int l;

        long startTime_2 = System.nanoTime();//time of algorithm code
        int limit = capacity/10;//limit for the capacity


        while(capacity >= limit) {
            /*double max = time[setbit][0];
            for (l = 1; l < L1E; l++) {//the item that have maximum rate value
                if (time[setbit][l] > max) {
                    max = time[setbit][l];
                }
                t=l;
            }
            indexx = find(Rate, max);//find index of maximum item */

            if (capacity >= Weight[indexx] && Weight[indexx] != 0) {
                capacity = capacity - Weight[indexx];//update capacity
                Total_value = Total_value + Value[indexx];//update total value
                Selected[indexx]+=1;
                Value[indexx]=0;//remove items that is used
                Weight[indexx]=0;
                Rate[indexx]=0.0;

            }

            else if (capacity < Weight[indexx]) {
                Rate[indexx]=0.0;
            }

        }

      System.out.println(Total_value);


        for(int i = 0; i < Value.length; i++)
        {
            System.out.println(Selected[i]);
        }
        long endTime_2 = System.nanoTime();//time of end of algorithm code


        PrintWriter outputfile = new PrintWriter("output-test.dat");//write the values to the output file
        outputfile.println(Total_value);

        for(int i = 0; i < Value.length; i++)
        {
            outputfile.println(Selected[i]);
        }

        outputfile.close();

        long endTime_1   = System.nanoTime();//time of end of code
        long totalTime_1 = endTime_1 - startTime_1;
        long totalTime_2 = endTime_2 - startTime_2;
        System.out.println("Space complexity: " + 4*N);//space efficiency of code
        System.out.println("Total code time complexity: " + totalTime_1/Math.pow(10,9) + " " + "seconds");
        System.out.println("Algorithm code time complexity: " + totalTime_2/Math.pow(10,9) + " " + "seconds");
    }
}

