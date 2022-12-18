public class Individual {
    private int[] chromossoma;
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

    public int[] getChromossoma() {
        return chromossoma;
    }

    public int getFitness() {
        return this.fitness;
    }



    private int definido (Individual filho) {
        for (int i = 0; i < filho.chromossoma.length; i++) {
            if (filho.chromossoma[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    //procura o indice do allele do cromossma2 que esta no cromossoma1
    public int searchAllele1 (int allele2, Individual p1) {
        for (int i = 0; i < p1.chromossoma.length; i++) {
            if (allele2 == p1.chromossoma[i]) {
                return i;
            }
        }
        return -1;
    }

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

    public void swapMutation(int i, int j){
        int temp = this.chromossoma[i];
        this.chromossoma[i] = this.chromossoma[j];
        this.chromossoma[j] = temp;
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
