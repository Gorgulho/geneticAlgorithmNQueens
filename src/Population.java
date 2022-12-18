import java.util.*;

public class Population {
    private LinkedList<Individual> individuals;
    private static final Random generator = new Random(0);

    public Population(int l, IProblem fitness) {
        individuals = new LinkedList<>();
        for (int i = 0; i < l*l; i++){
            this.individuals.add(new Individual(randPermutation(l), fitness));
        }
        individuals.sort((s1, s2) -> (int) Math.signum(s1.getFitness() - s2.getFitness()));
    }

    public LinkedList<Individual> getIndividuals() {
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
                if(d < 0.5){
                    int r = (int) (i+Math.round(generator.nextDouble() * (ind.getChromossoma().length-1 - i)));
                    ind.swapMutation(i, r);
                }
            }
        }
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
