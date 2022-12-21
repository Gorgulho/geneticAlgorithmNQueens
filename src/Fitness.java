import java.util.Arrays;

public class Fitness implements IProblem {

    private int calc (int n) {
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return (n-1)*n/2;
    }

    /**
     * Method that takes one Individual and calculate the fitness.
     * This fitness method calculates the number of atacks existing in the Indivivual, the lowest number is the best fitness.
     * @param individuo Individual we want to calculate the fitness.
     * @return Fitness value corresponding to the Individual.
     */
    @Override
    public int fitness(Individual individuo) {
        int posDiagonal = 0;
        int negDiagonal = 0;
        int size = individuo.getChromossoma().length;
        int []posDia = new int[size]; // (r+c)
        int []negDia = new int[size]; // (r-c)
        int []cromossoma = individuo.getChromossoma();
        for (int i = 0; i < size; i++) {
            posDia[i] = cromossoma[i]-i;
            negDia[i] = cromossoma[i]+i;
        }
        Arrays.sort(posDia);
        Arrays.sort(negDia);
        int mpos = 1;
        int aneg = 1;
        for (int i = 0; i < size-1; i++) {
            int j = i + 1;
            if (posDia[i] == posDia[j]) {
                mpos++;
            }
            else {
                posDiagonal += calc(mpos);
                mpos = 1;
            }
            if (negDia[i] == negDia[j]) {
                aneg++;
            }
            else {
                negDiagonal += calc(aneg);
                aneg = 1;
            }
            if (j == size-1) {
                posDiagonal += calc(mpos);
                negDiagonal += calc(aneg);
                break;
            }
        }

        return posDiagonal+negDiagonal;
    }
}
