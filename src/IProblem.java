interface IProblem {
    /**
     * Method that takes one Individual and calculate the fitness.
     * @param individuo Individual we want to calculate the fitness.
     * @return Fitness value corresponding to the Individual.
     */
    int fitness(Individual individuo);
}