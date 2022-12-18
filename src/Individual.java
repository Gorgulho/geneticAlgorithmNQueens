public class Individual {
    private int[] chromossoma;
    private double fitness;

    public Individual (int[] chromossoma) {
        this.chromossoma = chromossoma;
    }

    public Individual (int[] chromossoma, double fitness) {
        this.chromossoma = chromossoma;
        this.fitness = fitness;
    }

    public int[] getChromossoma() {
        return chromossoma;
    }
}
