package game.state.basic;

import game.state.GameState;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Index {
    private  final int i;
    private  final int j;
    public final static Index ZERO= new Index(0,0);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return i == index.i &&
                j == index.j;
    }

    @Override
    public int hashCode() {

        return Objects.hash(i, j);
    }

    public Index(){
        this(0,0);
    }
    public Index(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Index up(){
        return new Index(i,j+1);
    }
    public Index down(){
        return new Index(i,j-1);
    }
    public Index left(){
        return new Index(i-1,j);
    }
    public Index right(){
        return new Index(i+1,j);
    }
    public Index next(GameState state){
        if(!isValid(state))
            throw new IllegalArgumentException("index out of bounds for this state");

        Index u=up();
        if(u.isValid(state ))
            return u;

        Index l=new Index(i,0).right();
        if(l.isValid(state))
            return l;

        return null;

    }

    public Set<Index> neighbours(GameState state){
        return Arrays.stream(new Index[] { up(),down(),left(),right()})
                .filter(e->e.isValid(state))
                .collect(Collectors.toSet());
    }



    private boolean isValid(GameState state) {
        int boardSize=state.getBoardSize();
        return i>=0&&j>=0&& j<boardSize&& i <boardSize;
    }


    public boolean hasNext(GameState state) {
        return i<state.getBoardSize()-1||j<state.getBoardSize()-1;
    }

    public int manhattanDistance(Index other) {
        return Math.abs(this.i-other.i)+Math.abs(this.j-other.j);
    }

    @Override
    public String toString() {
        return "Index{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
