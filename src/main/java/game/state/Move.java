package game.state;

public class Move {
    public Move(Type type, Index start, Index end) {
        this.type = type;
        this.start = start;
        this.end = end;
    }

    public enum Type{Move,WallHorizontal,WallVertical}

    private final Type type;
    private final Index start;
    private final Index end;

    public Type getType() {
        return type;
    }

    public Index getStart() {
        return start;
    }

    public Index getEnd() {
        return end;
    }
}
