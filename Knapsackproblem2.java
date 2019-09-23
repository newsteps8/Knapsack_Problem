//Büşra_Gökmen
//150116027
import java.io.*;
public class Knapsackproblem2 {

    static int i ,o= 0;
   static int Size = 0;//size of arrays
   static int NumOfKnapsack  = 0;//number of knapsacks
    static int s = 0;
    static int indexx ;
   static int N = 0;
   static int M = 0;

    public static int find(double[] a, double target)//find index of value is maximum
    {
        for (int k = 0; k < a.length; k++)
            if (a[k] == target)
                return k;

        return -1;
    }
    public static void main(String[] args )throws IOException {
        long startTime_1 = System.nanoTime();//time of all of code

        try (BufferedReader nr = new BufferedReader(new FileReader("test2a.dat")))
        {//read the input file
            String line = null;
            while ((line = nr.readLine()) != null && o==0) {

                String[] parts = line.split(" ");

                int next = Integer.parseInt(parts[0]);
                int next2 = Integer.parseInt(parts[1]);

                if(o==0) {
                    N = next;
                    M = next2;
                }

                o++;
            }
        }
        catch (Exception expe)
        {
            expe.printStackTrace();
        }


         int [][] Selected = new int[N][M];//array to find whether item is choosen or not
         int [] Value = new int[N];// array to keep values of items
         int [] Weight = new int[N];//array to keep weights of items
        double [] Rate= new double[N];//array to keep value/weight rate values of items
        int [] Capacity = new int[M];//array to keep value of capacity of knapsacks
        int Total_value= 0;//total value in all knapsacks



        try (BufferedReader br = new BufferedReader(new FileReader("test2a.dat")))
        {
            String line = null;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" ");

                int next = Integer.parseInt(parts[0]);
                int next2 = Integer.parseInt(parts[1]);

                if(i==1) {

                   for (int j=0; j<parts.length; j++){
                        Capacity[j] = Integer.parseInt(parts[j]);//value of capacity of knapsacks

                    }
                }

                else if (i>=2) {
                    Value[s] = next;//value of items
                    Weight[s] = next2;//weight of items
                    Rate[s] = (double) Value[s] / Weight[s];////rate value of items
                    s = s+1;                }

                i++;
            }
        }
        catch (Exception expe)
        {
            expe.printStackTrace();
        }
        int l;
        int m=0;

        long startTime_2 = System.nanoTime();//time of algorithm code

        while(m < Capacity.length && Capacity[m] >= 0) {//start from the first knapsack to last knapsack
            double max = Rate[0];
            for (l = 1; l < Rate.length; l++) {//the item that have maximum rate value
                if (Rate[l] > max) {
                    max = Rate[l];
                }
            }

            indexx = find(Rate, max);//find index of maximum item value


            if (Capacity[m] >= Weight[indexx] && Weight[indexx] != 0) {
                Capacity[m] = Capacity[m] - Weight[indexx];//update capacity
                Total_value = Total_value + Value[indexx];//update total value
                Selected[indexx][m] += 1;
                Value[indexx]=0;//remove items that is used
                Weight[indexx]=0;
                Rate[indexx]=0.0;

            }
               else if (Capacity[m] < Weight[indexx]) {
                m++;

            }

        }

        System.out.println(Total_value);
        for(int i = 0; i < Value.length; i++)
        {
            for(int j = 0; j <Capacity.length; j++)
            {
                System.out.printf("%2d ", Selected[i][j]);
            }
            System.out.println();
        }
        long endTime_2 = System.nanoTime();//time of end of algorithm code


        PrintWriter outputfile = new PrintWriter("output-test.dat");//write the values to the output file
        outputfile.println(Total_value);
        for(int i = 0; i < Value.length; i++)
        {
            for(int j = 0; j <Capacity.length; j++)
            {
                outputfile.print( Selected[i][j] + " ");
            }
            outputfile.println();
        }

        outputfile.close();



        long endTime_1   = System.nanoTime();//time of end of code
        long totalTime_1 = endTime_1 - startTime_1;
        long totalTime_2 = endTime_2 - startTime_2;
        System.out.println("Space complexity: " + (3*N + M + N*M));//space efficiency of code
        System.out.println("Total code time complexity: " + totalTime_1/Math.pow(10,9) + " " + "seconds");
        System.out.println("Algorithm code time complexity: " + totalTime_2/Math.pow(10,9) + " " + "seconds");


    }//end of the main








}
