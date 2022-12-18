import java.util.Collections;

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

    public Individual solve(int l) {
        while (!searchBoardWinner()) {
            this.populacao = this.populacao.tournamentSelecNoReplacement(l, this.fitness);
            this.populacao = this.populacao.crossOver(this.fitness);
            this.populacao = this.populacao.mutation();
        }
        populacao.getIndividuals().sort((s1, s2) -> (int) Math.signum(s1.getFitness() - s2.getFitness()));
        return populacao.getIndividuals().get(0);
    }
}
