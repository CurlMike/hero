import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element {
    public Coin(int x, int y) {
        super(x,y);
    }
    public void draw(TextGraphics screen){
        screen.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(GetPosition().PosGetX(), GetPosition().PosGetY()), "$");
    }
}
