import java.util.Collections;

public class SGA {
    Population populacao;
    IProblem fitness;

    /**
     *SGA constructor
     * @param l
     * @param fitness
     */
    public SGA(int l, IProblem fitness) {
        this.populacao = new Population(l, fitness);
        this.fitness = fitness;
    }

    /**
     *
     * @return o objeto Individual que
     */
    public Individual searchBoardWinner() {
        for (Individual l : this.populacao.getIndividuals()) {
            if (l.getFitness() == 0) {
                return l;
            }
        }
        return null;
    }

    public Individual solve(int l) {
        Individual l1 = null;
        while (l1 == null) {
            this.populacao = this.populacao.tournamentSelecNoReplacement(l);
            this.populacao = this.populacao.crossOver();
            this.populacao.mutation();
            l1 = this.populacao.calculateAll(this.fitness);
        }
        return l1;
    }
}