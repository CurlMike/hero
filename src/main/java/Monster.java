import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {
    public Monster(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics screen){
        screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(GetPosition().PosGetX(), GetPosition().PosGetY()), "§");
    }

    public Position move(Arena arena) {
        Random random = new Random();
        while (true) {
            Position newpos = new Position(this.GetPosition().PosGetX() + random.nextInt(3) - 1,
                    this.GetPosition().PosGetY() + random.nextInt(3) - 1);
            if ((newpos.PosGetX() > 0 && newpos.PosGetX() < arena.GetWidth()-1) &&
                (newpos.PosGetY() > 0 && newpos.PosGetY() < arena.GetHeight()-1)) return newpos;
        }
    }
}
