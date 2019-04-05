package game.state.impl;

import game.state.*;
import game.state.basic.Index;
import game.state.basic.Move;
import game.state.routing.AdjacencyMap;

import java.util.PriorityQueue;

public class GameStateAdjacency implements GameState {

    private final Integer boardSize;
    private final IndexHash hash;
    private final AdjacencyMap map;


    public GameStateAdjacency(Integer boardsize){
        this.boardSize=boardsize;
        this.hash= new IndexHash(this);
        this.map= new AdjacencyMap(hash);

    }

    public void  initGame(){
        map.initGame();
    }


    public GameState applyMove(Move move) {
        if( move == null)
            throw new IllegalArgumentException("move cannot be null");
        switch(move.getType()){
            case Move:
            case WallVertical:
            case WallHorizontal:
            default:
                break;
        }
        return null;
    }

    public Integer score() {
        return null;
    }

    public Integer heuristic() {
        return null;
    }

    public Index location(Player player) {
        return null;
    }

    public Integer remainingWalls(Player player) {
        return null;
    }

    public boolean isConnected(Index i, Index j) {
        return map.connected(i,j);
    }
//
//    @Override
//    public int isTransitivelyConnected(Index i, Index j) {
//        AdjacencyMap pathMap=this.map.clone();
//        PriorityQueue<Index> queue= new PriorityQueue<>((e1,e2)->e1.manhattanDistance(j)-e2.manhattanDistance(j) );
//
//        queue.add(i);
//        int steps=0;
//        while(!queue.isEmpty()){
//            Index k= queue.poll();
//            steps++;
//            System.out.println(k);
//            if(k.equals(j))
//                return steps;
//            for(Index n:k.neighbours(this)){
//                if(pathMap.connected(k,n)){
//                    pathMap.removeEdge(k,n);
//                    queue.offer(n);
//                }
//            }
//        }
//       return -1;
//    }

    public void attachHeuristic(Heuristic heuristic) {

    }

    public int getBoardSize() {
        return boardSize;
    }


}
