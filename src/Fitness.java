public class Fitness implements IProblem {

    @Override
    public double fitness(Individual individuo) {
        int result = 0;
        int dim = individuo.getChromossoma().length;
        int []chromossomas = individuo.getChromossoma();
        for(int i = 0; i < dim-1; i++)
            for(int j = i+1; j < dim; j++)
                if(Math.abs(i-j) == Math.abs(chromossomas[i]-chromossomas[j]) || chromossomas[i] == chromossomas[j])
                    result++;
        return result;
    }
}
