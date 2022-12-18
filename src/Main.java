public class Main {
    public static void main(String[] args) {
//        Population pop = new Population(6, new Fitness());
//        System.out.println(pop.getIndividuals().get(0));
        int []a1 = {10,9,8,7,6,5,4,3,2,1,16,15,14,13,12,11};
        Individual p1 = new Individual(a1, new Fitness());
        int []a2 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
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