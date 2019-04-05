package game.state.routing;

import game.state.GameState;
import game.state.basic.Index;
import game.state.IndexHash;

import java.util.PriorityQueue;

public class PathPlanningAstar {

    public interface DistanceHeuristic{
        Integer guessDistance(GameState state, Index i, Index j);
    }
    public final static DistanceHeuristic manhattan=(s,i,j)->i.manhattanDistance(j);

    private final GameState state;
    private final IndexHash hash;
    private final AdjacencyData adjacencyData;

    public PathPlanningAstar(GameState state,AdjacencyData data) {
        this.state = state;
        this.hash= new IndexHash(state);
        this.adjacencyData=data;

    }


    public int isTransitivelyConnected(Index i, Index j) {
        AdjacencyData pathMap=this.adjacencyData.clone();
        PriorityQueue<Index> queue= new PriorityQueue<>((e1, e2)->manhattan.guessDistance(state,e1,e2));

        queue.add(i);
        int steps=0;
        while(!queue.isEmpty()){
            Index k= queue.poll();
            steps++;
            System.out.println(k);
            if(k.equals(j))
                return steps;
            for(Index n:k.neighbours(state)){
                if(pathMap.connected(k,n)){
                    pathMap.removeEdge(k,n);
                    queue.offer(n);
                }
            }
        }
        return -1;
    }
}
