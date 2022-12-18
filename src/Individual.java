public class Individual {
    private int[] chromossoma;
    private int fitness;

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

    public int getFitness() {
        return this.fitness;
    }

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


}
