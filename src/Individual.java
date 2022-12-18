public class Individual {
    private int[] chromossoma;
    private double fitness;

    public Individual (int[] chromossoma) {
        this.chromossoma = chromossoma;
    }

    public Individual (int[] chromossoma, IProblem fitness) {
        this.chromossoma = chromossoma;
        this.fitness = fitness.fitness(this);
    }

    public int[] getChromossoma() {
        return chromossoma;
    }
}
