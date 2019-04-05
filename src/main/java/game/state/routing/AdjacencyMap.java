package game.state.routing;

import game.state.basic.Index;
import game.state.IndexHash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public final class AdjacencyMap implements AdjacencyData {


    private final IndexHash hash;
    private HashMap<Integer,Set<Integer>> adjacency;


    public AdjacencyMap(IndexHash hash){
        this.hash = hash;
        adjacency=new HashMap<>();
        for(int i = 0; i<= this.hash.maxHash(); i++) {
            adjacency.put(i, new HashSet<>());
        }
    }
    @Override
    public void initGame(){
        for(int i=0;i<=hash.maxHash();i++){
            for(int j=i;j<=hash.maxHash();j++){
               addEdge(i,j);
            }
        }
    }

    private void addEdge(int i,int j){
        int a=Integer.min(i,j);
        int b=Integer.max(i,j);
        if(!adjacency.containsKey(a))
            throw new IllegalArgumentException("start of edge is invalid");
        adjacency.get(a).add(b);
    }
    @Override
    public void addEdge(Index i, Index j) {
       addEdge(hash.hash(i),hash.hash(j));
    }

    private  void removeEdge(int  i, int  j){
        int a=Integer.min(i,j);
        int b=Integer.max(i,j);
        if(!adjacency.containsKey(a))
            throw new IllegalArgumentException("start of edge is invalid");
        adjacency.get(a).remove(b);
    }
    @Override
    public  void removeEdge(Index i, Index j){
        removeEdge(hash.hash(i),hash.hash(j));
    }
    @Override
    public boolean connected(Index i, Index j){
        int a=Integer.min(hash.hash(i),hash.hash(j));
        int b=Integer.max(hash.hash(i),hash.hash(j));
        if(!adjacency.containsKey(a))
            return false;
        return adjacency.get(a).contains(b);
    }

    @Override
    public void addWall(Index i1, Index i2) {
        if(i1.left().equals(i2)||i1.right().equals(i2)){//Horizontal wall
            removeEdge(i1,i1.up());
            removeEdge(i2,i2.up());

        }
        else if (i1.up().equals(i2)||i1.down().equals(i2)){//Vertical wall
            removeEdge(i1,i1.right());
            removeEdge(i2,i2.right());
        }
        else
            throw new IllegalArgumentException("wall points are not connected");
    }

    @Override
    public AdjacencyMap clone() {
        AdjacencyMap nmap= new AdjacencyMap(hash);
        adjacency.forEach(
                (key, destset)-> destset.forEach(value-> nmap.addEdge(key,value) )
        );
        return nmap;
    }
}
