import java.util.DoubleSummaryStatistics;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static void testSomeValues(){
        for (int i = 4; i <= 24; i+=4){
            SGA a = new SGA(i, new Fitness());
            long startTime1 = System.nanoTime();
            Individual b = a.solve(i);
            long endTime1 = System.nanoTime();
            System.out.println("i = "+ i + " - " + (float) (endTime1 - startTime1) / 1000000000 + " segundos");
        }
    }

    public static void main(String[] args) {
//        Population pop = new Population(6, new Fitness());
//        System.out.println(pop.getIndividuals().get(0));

        /*int[] ch1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] ch2 = {9, 3, 7, 8, 2, 6, 5, 1, 4};
        Individual p1 = new Individual(ch1, new Fitness());
        Individual p2 = new Individual(ch2, new Fitness());

        Individual f1 = p1.PMXCrossover(p2, new Random(0));
        System.out.println("f1");
        for(int el : f1.getChromossoma()){
            System.out.print(el + " ");
        }

        Individual f2 = p2.PMXCrossover(p1, new Random(0));
        System.out.println("\nf2");
        for(int el : f2.getChromossoma()){
            System.out.print(el + " ");
        }*/

        testSomeValues();

        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        SGA a = new SGA(l, new Fitness());
        long startTime1 = System.nanoTime();
        Individual b = a.solve(l);
        long endTime1 = System.nanoTime();
        System.out.println(b);
        System.out.println((float) (endTime1 - startTime1) / 1000000000 + " segundos");

//        int []a = {0,1,2,3,4,5,6,7,8,9};
//        Individual a1 = new Individual(a);
//        IProblem fitness = new Fitness();
//        int d = fitness.fitness(a1);
//        System.out.println(d);
    }
}