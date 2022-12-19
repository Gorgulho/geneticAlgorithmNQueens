import java.util.Collections;

public class SGA {
    Population populacao;
    IProblem fitness;

    public SGA(int l, IProblem fitness) {
        this.populacao = new Population(l, fitness);
        this.fitness = fitness;
    }

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
        while ((l1 = searchBoardWinner()) == null) {
            this.populacao = this.populacao.tournamentSelecNoReplacement(l, this.fitness);
            this.populacao = this.populacao.crossOver1(this.fitness);
            this.populacao.mutation(this.fitness);
        }
        return l1;
    }
}
