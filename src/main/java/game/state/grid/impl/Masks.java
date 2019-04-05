package game.state.grid.impl;

import game.state.basic.Index;
import game.state.bitSet.ShiftableBitSet;
import game.state.motion.Direction;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class Masks {

    public final Map<Direction, ShiftableBitSet> moveMasks;

    private Masks() {

        moveMasks = new HashMap<>();
    }

    public static ShiftableBitSet createBitsetFor(int size, Direction direction){
        ShiftableBitSet data  = new ShiftableBitSet(size*size);
        data.clear();
        Function<Integer, Index> motionFunction = (i)->null;
//        data.cardinality()
        switch (direction){
            case UP:
            case DOWN:
                data.not();
                return data;
            case LEFT:
                motionFunction= (i)->  Index.get(i,0);
                break;
            case RIGHT:
                motionFunction= (i) -> Index.get(i,size-1);
                break;
        }
        for(int i=0;i<size;i++)
            data.set(SpaceFilling.transform(motionFunction.apply(i),size));

        data.not();
        return data;
    }

    public static Masks createMasks(int size) {
        Masks masks=new Masks();
        for(Direction direction: Direction.values()){
            masks.moveMasks.put(direction,createBitsetFor(size,direction));
        }
        return masks;
    }
}
