package game.state.grid.impl;

import game.state.basic.Index;
import game.state.grid.Grid;
import game.state.bitSet.ShiftableBitSet;
import game.state.motion.Direction;

public class BitSetGrid implements Grid {

    private final int size;
    private ShiftableBitSet bitSet;
    private Masks masks;

    public BitSetGrid(int size) {

        this.size = size;
        bitSet = new ShiftableBitSet(size * size);
        bitSet.clear();
    }

    public BitSetGrid(BitSetGrid otherGrid) {

        this.size = otherGrid.size;
        this.masks = otherGrid.masks;
        this.bitSet = otherGrid.bitSet.clone();
    }

    @Override
    public int getSize() {

        return size;
    }

    @Override
    public boolean get(Index index) {

        return bitSet.get(SpaceFilling.transform(index, size))>0;
    }

    @Override
    public void fill(Index index) {

        bitSet.set(SpaceFilling.transform(index, size));
    }

    @Override
    public void set(Index index) {

        bitSet.set(SpaceFilling.transform(index, size));
    }

    @Override
    public void clear(Index index) {

        bitSet.clear(SpaceFilling.transform(index, size));
    }

    @Override
    public void clearAll() {

        bitSet.clear();
    }

    @Override
    public Grid shift(Direction dir) {
        prepareMasks();
        shift(this.bitSet,masks, dir, size);

        return this;
    }

    private static void shift(ShiftableBitSet set, Masks masks, Direction dir, int size){

        int shiftAmount = 0;
        switch (dir) {
            case RIGHT:
                shiftAmount = 1;
                break;
            case LEFT:
                shiftAmount = -1;
                break;
            case DOWN:
                shiftAmount = -size;
                break;
            case UP:
                shiftAmount = size;
                break;
        }
        set.and(masks.moveMasks.get(dir));
        set.shiftLeft(shiftAmount);
    }
    private void assertType(Grid grid) {

        if (!(grid instanceof BitSetGrid)) {
            throw new UnsupportedOperationException("Grid is not a BitSetGrid");
        }
    }

    @Override
    public Grid and(Grid grid) {

        assertType(grid);
        this.bitSet.and(((BitSetGrid) grid).bitSet);
        return this;
    }

    @Override
    public Grid or(Grid grid) {

        assertType(grid);
        this.bitSet.or(((BitSetGrid) grid).bitSet);
        return this;
    }

    @Override
    public Grid xor(Grid grid) {

        assertType(grid);
        this.bitSet.xor(((BitSetGrid) grid).bitSet);
        return this;
    }

    @Override
    public Grid not() {

        this.bitSet.not();
        return this;
    }

    @Override
    public Grid fillFlood(Index index) {
        prepareMasks();
        ShiftableBitSet grow=this.bitSet.clone();
        ShiftableBitSet stopMask=this.bitSet.clone();

        grow.clear();
        grow.set(SpaceFilling.transform(index,size));

        stopMask.not();

        grow.and(stopMask);

        for(int i=0;i<size*2;i++){

            for(Direction dir: Direction.values()){
                ShiftableBitSet cpy=grow.clone();
                shift(cpy,masks,dir,size);
                cpy.and(stopMask);
                grow.or(cpy);
            }
        }
        this.bitSet.or(grow);

        return this;
    }

    private void prepareMasks() {

        if (masks == null) {
            masks = Masks.createMasks(size);
        }
    }

    @Override
    public String toString() {

        StringBuilder buf = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                buf.append(bitSet.get(SpaceFilling.transform(new Index(i, j), size)) > 0 ? 1 : 0);
            }
            buf.append("\n");
        }
        return buf.toString();
    }

    @Override
    public BitSetGrid clone() {

        return new BitSetGrid(this);
    }
}
