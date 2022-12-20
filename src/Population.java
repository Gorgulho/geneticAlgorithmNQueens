import java.util.*;

public class Population {
    private ArrayList<Individual> individuals;
    private static final Random generator = new Random(0);

    public Population(int l, IProblem fitness) {
        individuals = new ArrayList<>();
        for (int i = 0; i < l*l; i++){
            this.individuals.add(new Individual(randPermutation(l), fitness));
        }
    }

    public Population(ArrayList<Individual> result) {
        this.individuals = result;
    }

    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

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

    public void mutation(){
        for(Individual ind : individuals){
            for(int i = 0; i < ind.getChromossoma().length-1; i++){
                double d = generator.nextDouble();
                if(d < 0.8){
                    int r = (int) (i+Math.round(generator.nextDouble() * (ind.getChromossoma().length-1 - i)));
                    ind.swapMutation(i, r);
                }
            }
        }
    }

    public ArrayList<Individual> randomPermutationNoReplacement () {
        ArrayList<Individual> result = new ArrayList<>(this.individuals);
        int n = this.individuals.size();
        double d;
        int index;
        for (int i = 0; i < n-1; i++) {
            d = generator.nextDouble();
            index = i + (int) Math.round(d*(n-1-i));
            Individual old = result.get(i);
            result.set(i, result.get(index));
            result.set(index,old);
        }
        return result;
    }

    public Population tournamentSelecNoReplacement (int s) {
        ArrayList<Individual> result = new ArrayList<>();
        ArrayList<Individual> permutation;
        Individual best;
        for (int i = 0; i < s; i++) {
            permutation = randomPermutationNoReplacement();
            for (int j = 0; j < this.individuals.size(); j+=s) {
                best = permutation.get(j);
                for (int k = j+1; k < j+s; k++) {
                    if (best.getFitness() < permutation.get(k).getFitness()) {
                        best = permutation.get(k);
                    }
                }
                result.add(best);
            }
        }
        return new Population(result);
    }

    public Population crossOver(IProblem fitness) {
        ArrayList<Individual> result = new ArrayList<>();
        double d;
        for (int i = 0; i < individuals.size()-1; i+=2) {
            d = generator.nextDouble();
            if (d < 0.8) {
                result.add(individuals.get(i).cycleCrossover(individuals.get(i+1)));
                result.add(individuals.get(i+1).cycleCrossover(individuals.get(i)));

            } else {
                result.add(individuals.get(i));
                result.add(individuals.get(i+1));
            }
        }
        return new Population(result);
    }

    public Population crossOver1() {
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

    public Individual calculateAll (IProblem fitness) {
        for (int i = 0; i < this.individuals.size(); i++) {
            int fit = fitness.fitness(this.individuals.get(i));
            this.individuals.get(i).setFitness(fit);
            if (fit == 0) {
                return this.individuals.get(i);
            }
        }
        return null;
    }

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
