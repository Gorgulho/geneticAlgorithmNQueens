public class Main {
    public static void main(String[] args) {
        Population pop = new Population(6, new Fitness());
        System.out.println(pop.getIndividuals().get(0));
    }
}