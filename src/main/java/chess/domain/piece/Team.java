package chess.domain.piece;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public boolean isWhite() {
        return this == WHITE;
    }

    public Team opponent() {
        if (this == EMPTY) {
            return this;
        }
        return this == WHITE ? BLACK : WHITE;
    }
}
