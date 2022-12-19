import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Population pop = new Population(6, new Fitness());
//        System.out.println(pop.getIndividuals().get(0));
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        SGA a = new SGA(l, new Fitness());
        Individual b = a.solve(l);
        System.out.println(b);


    }
}