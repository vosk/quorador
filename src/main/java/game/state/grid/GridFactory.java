package game.state.grid;

public interface GridFactory {

    Grid emptyGrid(int size);
    WalledGrid emptyWalledGrid(int size);
}
