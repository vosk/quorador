package game.state.basic;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Index {
    private  final int i;
    private  final int j;
    public final static Index ZERO;
    private final static Map<Integer,Index> cache;

    static {
        cache= new ConcurrentHashMap<>();
        ZERO = get(0,0);
    }
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

    private Index(int i, int j) {
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
        return  get(i,j+1);
    }
    public Index down(){
        if(j>0){
            return get(i,j-1);
        }
        return null;

    }
    public Index left(){
        if(i>0){
            return get(i-1,0);
        }
        return null;
    }
    public Index right(){
        return get(i+1,j);
    }

    public int manhattanDistance(Index other) {
        return Math.abs(this.i-other.i)+Math.abs(this.j-other.j);
    }

    public static int cantorPairingFunction(int i, int j){
        return (i+j)*(i+j+1)/2+i;
    }
    public static Index get(int i, int j){
        int map=cantorPairingFunction(i,j);
        if(!cache.containsKey(map)){
            cache.put(map, new Index(i,j));
        }
        return cache.get(map);
    }

    @Override
    public String toString() {
        return "Index{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
