package game.state.grid;

import game.state.basic.Index;
import game.state.motion.Direction;

public interface Grid extends  Cloneable{
    int getSize();
    void get(Index index);
    void fill(Index index);
    void clear(Index index);
    void set(Index index);
    void clearAll();
    Grid shift(Direction dir);
    Grid and(Grid grid);
    Grid or( Grid grid);
    Grid xor(Grid grid);
    Grid not();
}
