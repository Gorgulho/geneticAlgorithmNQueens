public class SGA {
    Population populacao;
    IProblem fitness;

    public SGA(int l, int n, IProblem fitness) {
        this.populacao = new Population(l, n, fitness);
        this.fitness = fitness;
    }

    public boolean searchBoardWinner() {
        for (Individual l : this.populacao.getIndividuals()) {
            if (l.getFitness() == 0) {
                return true;
            }
        }
        return false;
    }
}
