import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x;
    private int y;

    public Hero(int x, int y) {
    }

    public void HeroSetX(int x1) {
        x = x1;
    }

    public void HeroSetY(int y1) {
        y = y1;
    }

    public int HeroGetX() {
        return x;
    }

    public int HeroGetY() {
        return y;
    }

    public void MoveUp() {
        y -= 1;
    }

    public void MoveDown() {
        y += 1;
    }

    public void MoveLeft() {
        x -= 1;
    }

    public void MoveRight() {
        x += 1;
    }

    public void draw(Screen screen) {
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }
}