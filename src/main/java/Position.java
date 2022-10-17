public class Position {
    private int x;
    private int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void PosSetX(int x) { this.x = x; }
    public void PosSetY(int y) { this.y = y; }
    public int PosGetX() { return x; }
    public int PosGetY() { return y; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        return (((this.x == ((Position) o).PosGetX() && ((Position) o).PosGetY() == this.y)));
    }
}
