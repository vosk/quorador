package game.state;

public interface GameState {

    void  initGame();

    GameState applyMove(Move move);
    Integer score();
    Integer heuristic();
    Index location(Player player);
    Integer remainingWalls(Player player);

    boolean isConnected(Index i, Index j);
//
//    /**
//     * Path distance from i to j. TODO: Not true path length for now, for unobstructed paths it is the true path length
//     * @param i The start of the path
//     * @param j the end of the path
//     * @return returns the number of steps required to reach j from i, or -1 if no path exists
//     */
//    int isTransitivelyConnected(Index i, Index j);//There exists a valid path from i to j

    void attachHeuristic(Heuristic heuristic);

    int getBoardSize();
}
