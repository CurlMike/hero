import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private Position position;

    public Element(int x, int y) {
        position = new Position(x, y);
    }
    public Position GetPosition() { return position; }
    public void SetPosition(Position position) { this.position = position; }
    public abstract void draw(TextGraphics screen);
}
