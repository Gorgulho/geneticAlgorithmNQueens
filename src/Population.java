import java.util.*;

public class Population {
    private final ArrayList<Individual> individuals;
    private final Random generator = new Random();

    /**
     * Population constructor, generates a population with l*l size. Each Generated Indivdual is composed with a random
     * permutation has the chromosome.
     * @param l length of chromosome.
     * @param fitness object responsible for calculate the Individuals fitness.
     */
    public Population(int l, IProblem fitness) {
        individuals = new ArrayList<>();
        int n = l*l;
        for (int i = 0; i < n; i++){
            this.individuals.add(new Individual(randPermutation(l), fitness));
        }
    }

    /**
     * Population constructor, takes resut ArrayLList and place it as the population.
     * @param result ArrayList of Individuals to be assigned to the population.
     */
    public Population(ArrayList<Individual> result) {
        this.individuals = new ArrayList<>();
        this.individuals.addAll(result);
    }

    /**
     * @return ArrayList of Individuals in the population.
     */
    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    /**
     * int array of length 'n' is created with a permutaion, starting at 0, and shuffled.
     * @param n length of array being returned.
     * @return int array with the random permutation.
     */
    public int[] randPermutation(int n){
        int[] v = new int[n];
        double r;
        for(int i = 0; i < n; i++){
            v[i] = i;
        }
        for(int i = 0; i < n-1; i++){
            r = generator.nextDouble();
            int re = i + (int) Math.round(r * (n - 1 - i));
            int change = v[i];
            v[i] = v[re];
            v[re] = change;
        }
        return v;
    }

    /**
     * Analyses all genes from every Individual in the Population and test the probability of that gene to mutate.
     * For a gene to mutate, the value d, generated randomly, needs to be less than 0.8, otherwise the gene will not change.
     * @return new population with all the Population Individuals after every mutation.
     */
    public Population mutation(){
        ArrayList<Individual> result = new ArrayList<>();
        for(Individual ind : individuals){
            Individual a = new Individual(ind.getChromossoma());
            for(int i = 0; i < a.getChromossoma().length-1; i++){
                double d = generator.nextDouble();
                if(d < 0.8){
                    int r = (int) (i+Math.round(generator.nextDouble() * (ind.getChromossoma().length-1 - i)));
                    a = a.swapMutation(i, r);
                }
            }
            result.add(a);
        }
        return new Population(result);
    }

    /**
     * Generates a random permutation and shuffle the population based on it.
     * @return ArrayList with the suffled population.
     */
    public ArrayList<Individual> randomPopulationPermutation() {
        ArrayList<Individual> result = new ArrayList<>();
        int length = this.individuals.size();
        int[] v = randPermutation(length);
        for(int i: v){
            result.add(this.individuals.get(i));
        }
        return result;
    }

    /**
     * Shuffles the population randomly s times and make n/s tornaments, every tournament is between s Individuals,
     * winning the best Individual with the best fitness.
     * @param s tournament size.
     * @return new Population with the winning individuals.
     */
    public Population selectionTournament(int s){
        ArrayList<Individual> winners = new ArrayList<>();
        ArrayList<Individual> permutations;
        for (int i = 0; i < s; i++){
            permutations = (randomPopulationPermutation());
            for (int j = 0; j < this.individuals.size(); j+=s){
                int indexMax = getMinFitnessGroup(permutations, j, j + s);
                winners.add(permutations.get(indexMax));
            }
        }

        return new Population(winners);
    }

    /**
     * Analyses the population in a given range and returns the index of the Individual with the minor fitness.
     * @param ind ArrayList with all the Indivuduals.
     * @param start point where to start analysing.
     * @param end point where to start analysing.
     * @return Index of the ArryList where the best Individual is.
     */
    private int getMinFitnessGroup(ArrayList<Individual> ind, int start, int end){
        double min = ind.get(start).getFitness();
        int id = start;
        for (int i = start + 1; i < end; i++){
            if (ind.get(i).getFitness() < min){
                min =  ind.get(i).getFitness();
                id = i;
            }
        }
        return id;
    }

    /**
     *  Creates new array list that will contain de individuals after the crossover.
     *  variable d will determine if crossover occurs.
     *  each pair of individuals on the population is selected and if d < 0.95 cycle crossover occurs; if not then crossover
     * doesn't occur and the new individuals are the same as the parents.
     * @return new popultion resulting from crossover.
     */
    public Population crossOverCX() {
        ArrayList<Individual> result = new ArrayList<>();
        double d;
        for (int i = 0; i < individuals.size(); i+=2) {
            d = generator.nextDouble();
            if (d < 0.95) {
                result.add(individuals.get(i).cycleCrossover(individuals.get(i+1)));
                result.add(individuals.get(i+1).cycleCrossover(individuals.get(i)));

            } else {
                result.add(individuals.get(i));
                result.add(individuals.get(i+1));
            }
        }
        return new Population(result);
    }

    /**
     *  Creates new array list that will contain de individuals after the crossover.
     *  variable d will determine if crossover occurs.
     *  each pair of individuals on the population is selected and if d < 0.95 partialy mapped crossover occurs; if not then crossover
     * doesn't occur and the new individuals are the same as the parents.
     * @return new popultion resulting from crossover.
     */
    public Population crossOverPMX() {
        ArrayList<Individual> result = new ArrayList<>();
        double d;
        for (int i = 0; i < individuals.size()-1; i+=2) {
            d = generator.nextDouble();
            if (d < 0.95) {
                result.add(individuals.get(i).PMXCrossover(individuals.get(i+1), generator));
                result.add(individuals.get(i+1).PMXCrossover(individuals.get(i), generator));
            } else {
                result.add(individuals.get(i));
                result.add(individuals.get(i+1));
            }
        }
        return new Population(result);
    }

    /**
     * Sets all fitness in the population.
     * @param fitness object responsible to calculate the Individuals fitness.
     * @return Individual which the fitness is zero, if it doesn't exist returns null
     */
    public Individual calculateAll (IProblem fitness) {
        for (Individual individual : this.individuals) {
            int fit = fitness.fitness(individual);
            individual.setFitness(fit);
            if (fit == 0) {
                return individual;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Individual ind : individuals){
            result.append(ind.toString());
            result.append("\n");
        }
        return result.toString();
    }

}