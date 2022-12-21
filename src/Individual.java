import java.util.ArrayList;
import java.util.Random;

public class Individual {
    private final int[] chromossoma;
    private int fitness;

    public Individual (int[] chromossoma) {
        this.chromossoma = chromossoma;
    }

    public Individual (int l) {
        this.chromossoma = new int[l];
        for (int i = 0; i < l; i++) {
            this.chromossoma[i] = -1;
        }
    }

    public Individual (int[] chromossoma, IProblem fitness) {
        this.chromossoma = chromossoma;
        this.fitness = fitness.fitness(this);
    }

    /**
     *
     * @return Integer array with the chromossome of the Individual
     */
    public int[] getChromossoma() {
        return chromossoma;
    }

    /**
     *
     * @return Fitness value from the Individual
     */
    public int getFitness() {
        return this.fitness;
    }

    /**
     * Recives an int value and sets it as the Individual Fitness
     * @param f integer value correspondng to the Individual Fitness
     */
    public void setFitness (int f) {
        this.fitness = f;
    }

    /**
     * Search in an Individual chromosome for the existence of a -1 value
     * @param filho Individual where the search will get place.
     * @return index where the -1 is, -1 otherwise.
     */
    private int definido (Individual filho) {
        for (int i = 0; i < filho.getChromossoma().length; i++) {
            if (filho.getChromossoma()[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    //procura o indice do allele do cromossma2 que esta no cromossoma1

    /**
     * Search the index of the allele from chromossome2(p2) in chromossome1(p1).
     * @param allele2 allele value to search in p1 chromossome.
     * @param p1 Individual that will be searched.
     * @return index where the allele value is, -1 otherwise.
     */
    public int searchAllele1 (int allele2, Individual p1) {
        for (int i = 0; i < p1.chromossoma.length; i++) {
            if (allele2 == p1.chromossoma[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param p1 first Individual(parent) needed to make the crossover.
     * @param p2 second Individual(parent) needed to make the crossover.
     * @param result
     * @param filho
     */
    public void findCycle (Individual p1, Individual p2, int result, Individual filho) {
        int allele1 = p1.chromossoma[result];
        int i = result;

        while (true) {
            filho.chromossoma[i] = p1.chromossoma[i];
            int allele2 = p2.chromossoma[i];

            i = searchAllele1(allele2, p1);
            if (allele1 == p2.chromossoma[i]) {
                filho.chromossoma[i] = p1.chromossoma[i];
                return;
            }
        }
    }

    /**
     * Cycle Crossover (CX) is a genetic algorithm operator used to combine two parent solutions to create a new
     * offspring solution. Works by identifying cycles of values in two parents and swaping them to create the offspring.
     * First identify the cycles of values in the two parent, next, we swap the values in the cycles to create the offspring
     * The first Individual(parent) is p1, and it's the Individual who calls the method.
     * @param p2 second Individual(parent) needed to make the crossover.
     * @return new Individual with the resulting offspring from both parents.
     */
    public Individual cycleCrossover(Individual p2) {
        int result; //result é o numero de elementos dentro do ciclo
        int ciclos = 0; //numero de ciclos

        int tamanho = this.chromossoma.length;

        Individual filho = new Individual(tamanho);

        while ((result = definido(filho)) != -1) {
            if (ciclos % 2 == 0) {
                findCycle(this, p2, result, filho);
            }
            else {
                findCycle(p2, this, result, filho);
            }
            ciclos++;
        }
        return filho;
    }

    /**
     * Takes two positions and exchange them in the Individual Chromosome.
     * @param i first index.
     * @param j second index.
     */
    public void swapMutation(int i, int j){
        int temp = this.chromossoma[i];
        this.chromossoma[i] = this.chromossoma[j];
        this.chromossoma[j] = temp;
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    public String toString() {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < chromossoma.length; i++) {
            for(int j = 0; j < chromossoma.length; j++){
                if(chromossoma[j] == i) a.append("1");
                else a.append("0");
            }
            a.append("\n");
        }
        a.append("Fitness: " + this.fitness + "\n");
        return a.toString();
    }

    /**
     * Partially Mapped Crossover (PMX) is a genetic algorithm operator used to combine two parents to create a new
     * offspring. It is similar to cycle crossover (CX), but it works by creating a mapping between the values of the
     * two parent solutions and swapping the mapped values to create the offspring.
     * Sets a random section of p2 and copies it to the offspring,
     * maps all values, except for the positions in the selected section, from p1 and copies them to the offspring,
     * at last copies the missing values in the offspring in order from de p2.
     * The first Individual(parent) is p1, and it's the Individual who calls the method.
     * @param p2 second Individual(parent) needed to make the crossover.
     * @param rand object type Random to generate the section.
     * @return new Individual with the resulting offspring from both parents.
     */
    public Individual PMXCrossover(Individual p2, Random rand) {
        Individual child = new Individual(this.chromossoma.length);

        // Choose random crossover points
        int point1 = rand.nextInt(this.chromossoma.length);
        int point2 = rand.nextInt(this.chromossoma.length);
        if (point2 < point1) {
            int temp = point1;
            point1 = point2;
            point2 = temp;
        }

        // Copy section between crossover points from one parent to child
        for (int i = point1; i <= point2; i++) {
            child.chromossoma[i] = p2.getChromossoma()[i];
        }

        for(int i = 0; i < this.chromossoma.length; i++){
            if (i < point1 || i > point2) {
                if (!containsValue(child.chromossoma, this.chromossoma[i])) {
                    child.chromossoma[i] = this.chromossoma[i];
                }
            }
        }

        ArrayList<Integer> minusOnes = getMinusOnes(child.chromossoma, -1);

        for(int i = 0; i < p2.getChromossoma().length; i++){
            if (!containsValue(child.chromossoma, p2.getChromossoma()[i])) {
                child.chromossoma[minusOnes.remove(0)] = p2.getChromossoma()[i];
            }
        }

        return child;
    }

    /**
     * Check if array contains a specified value.
     * @param arr Integer array where the search will get place.
     * @param value Value that we want to check if existes in arr.
     * @return True if the value was found, False otherwise.
     */
    private static boolean containsValue(int[] arr, int value){
        for (int el : arr){
            if(el == value) return true;
        }
        return false;
    }

    /**
     * It recieves an Integer array where the searche will be done.
     * @param arr Integer array where the search will get place.
     * @param val Value that we want to check the ocurrences in arr.
     * @return ArrayList with all the indexes with the value -1.
     */
    private static ArrayList<Integer> getMinusOnes(int[] arr, int val) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) result.add(i);
        }
        return result;
    }
}
