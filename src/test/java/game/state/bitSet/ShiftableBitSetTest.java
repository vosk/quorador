package game.state.grid.impl;

import static org.junit.Assert.assertEquals;

import game.state.bitSet.ShiftableBitSet;
import org.junit.Test;

public class ShiftableBitSetTest {

    private static int N=250;
    @Test
    public void setGet(){

        ShiftableBitSet bitSet = new ShiftableBitSet(N);
        for(int i=0;i<N;i++){
            bitSet.clear();
            bitSet.set(i);
            for(int j=0;j<N;j++){
                assertEquals( i==j,bitSet.get(j) ==1);
            }
        }
        for(int i=0;i<N;i++){
            bitSet.clear();
            for(int j=0;j<=i;j++){
                bitSet.set(j);
            }

            System.out.println(bitSet);
            for(int j=0;j<N;j++){
                assertEquals( i>=j,bitSet.get(j) ==1);
            }
        }
    }
    @Test
    public void not(){

        ShiftableBitSet bitSet = new ShiftableBitSet(N);
        for(int i=0;i<N;i++){
            bitSet.clear();
            bitSet.set(i);
            bitSet.not();
            for(int j=0;j<N;j++){
                assertEquals( i==j,bitSet.get(j) ==0);
            }
        }
        for(int i=0;i<N;i++){
            bitSet.clear();
            for(int j=0;j<=i;j++){
                bitSet.set(j);
            }
            bitSet.not();
            System.out.println(bitSet);
            for(int j=0;j<N;j++){
                assertEquals( i>=j,bitSet.get(j) ==0);
            }
        }
    }
    @Test
    public void shiftLeft(){
        ShiftableBitSet bitSet = new ShiftableBitSet(N);
        for(int i=0;i<N;i++){
            bitSet.clear();
            bitSet.set(0);
            System.out.println(i);
            bitSet.shiftLeft(i);
            System.out.println(bitSet);
            for(int j=0;j<N;j++){
                assertEquals( i==j,bitSet.get(j) ==1);
            }
        }

        for(int i=0;i<N;i++){
            bitSet.clear();
            bitSet.set(0);
            System.out.println(i);
            bitSet.shiftLeft(i);
            bitSet.not();
            System.out.println(bitSet);
            for(int j=0;j<N;j++){
                assertEquals( i==j,bitSet.get(j) ==0);
            }
        }

    }

    @Test
    public void shiftRight(){
        ShiftableBitSet bitSet = new ShiftableBitSet(N);
        for(int i=0;i<N;i++){
            bitSet.clear();
            bitSet.set(N-1);
            System.out.println(i);
            bitSet.shiftLeft(-N+i+1);
            System.out.println(bitSet);
            for(int j=0;j<N;j++){
                assertEquals( i==j,bitSet.get(j) ==1);
            }
        }

        for(int i=0;i<N;i++){
            bitSet.clear();
            bitSet.set(N-1);

            System.out.println(i);
            bitSet.shiftLeft(-N+i+1);
            bitSet.not();
            System.out.println(bitSet);
            for(int j=0;j<N;j++){
                assertEquals( i==j,bitSet.get(j) ==0);
            }
        }
    }
}