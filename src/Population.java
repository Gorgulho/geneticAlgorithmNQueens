import java.util.LinkedList;
import java.util.Random;

public class Population {

    private final LinkedList<Individual> individuals;
    private static final Random generator = new Random(0);

    public Population(int l) {
        individuals = new LinkedList<>();
        for (int i = 0; i < l*2; i++){
            individuals.add(new Individual(randPermutation(l)));
        }
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
