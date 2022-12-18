public class Main {
    public static void main(String[] args) {
//        Population pop = new Population(6, new Fitness());
//        System.out.println(pop.getIndividuals().get(0));
        int []a1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Individual p1 = new Individual(a1, new Fitness());
        int []a2 = {15,12,10,7,6,8,11,14,13,9,2,3,4,5,1};
        Individual p2 = new Individual(a2, new Fitness());
        Individual filho1 = p1.cycleCrossover(p2);
        for (int i = 0; i < filho1.getChromossoma().length; i++) {
            System.out.print(filho1.getChromossoma()[i] + " ");
        }
        System.out.println();
        Individual filho2 = p2.cycleCrossover(p1);
        for (int i = 0; i < filho2.getChromossoma().length; i++) {
            System.out.print(filho2.getChromossoma()[i] + " ");
        }
    }
}