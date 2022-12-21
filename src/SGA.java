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
     * solves puzzle using sGA
     * @param l length of chromosome
     * @return solution of puzzle
     */
    public Individual solve(int l) {
        Individual l1 = null;
        while (l1 == null) {
            this.populacao = this.populacao.selectionTournament(l);
            this.populacao = this.populacao.crossOverCX();
            //this.populacao = this.populacao.crossOverPMX();
            this.populacao = this.populacao.mutation();
            l1 = this.populacao.calculateAll(this.fitness);
        }
        return l1;
    }
}