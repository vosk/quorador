package game.state.grid.impl;

import game.state.basic.Index;

public abstract class SpaceFilling {


    public static int transform(Index i, int size){
        return i.getI()*size+i.getJ();
    }

    Index transform(int i,int size){
        return new Index(i/size,i%size);
    }
}
