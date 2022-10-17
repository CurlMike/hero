import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private Screen screen;
    private int width, height;
    private Hero hero;

    private List<Wall> walls;

    private List<Coin> coins;

    private List<Monster> monsters;

    private int CoinCount = 0;

    public int GetWidth() { return width; }

    public int GetHeight() { return height; }

    public boolean CanHeroMove(Position position) {
        return (position.PosGetX() >= 0 && position.PosGetX() < width) &&
                (position.PosGetY() >= 0 && position.PosGetY() < height) &&
                (!walls.contains(new Wall(position.PosGetX(), position.PosGetY())));
    }
    public void moveHero(Position position) {
        if (CanHeroMove(position))
            hero.setPosition(position);
        RetrieveCoins();
    }

    public void ProcessKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) {
            moveHero(MoveUp());
        }
        if (key.getKeyType() == KeyType.ArrowDown) {
            moveHero(MoveDown());
        }
        if (key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(MoveLeft());
        }
        if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(MoveRight());
        }
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
        }
    }
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        walls = createWalls();
        hero = new Hero(10,10);
        coins = createCoins();
        monsters = createMonsters();
    }

    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(screen);

        for (Wall wall : walls)
            wall.draw(screen);
        for (Coin coin: coins)
            coin.draw(screen);
        for (Monster monster: monsters)
            monster.draw(screen);
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Coin NC = new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1);
            if (!coins.contains(NC) && !NC.GetPosition().equals(hero.GetPosition()))
                coins.add(NC);
        }
        return coins;
    }

    private void RetrieveCoins() {
        for (Coin coin: coins) {
            if (coin.GetPosition().equals(hero.GetPosition())) {
                coins.remove(coin);
                CoinCount++;
                break;
            }
        }
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Monster NM = new Monster(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1);
            if (!monsters.contains(NM) && !NM.GetPosition().equals(hero.GetPosition()))
                monsters.add(NM);
        }
        return monsters;
    }

    public void moveMonsters() {
        for (Monster monster: monsters) {
            monster.SetPosition(monster.move(this));
        }
    }

    public boolean verifyMonsterCollisions() {
        for (Monster monster: monsters) {
            if (monster.GetPosition().equals(hero.GetPosition())) {
                System.out.println("End of the Line.");
                String message = String.format("Total coins collected: %d", CoinCount);
                System.out.println(message);
                return true;
            }
        }
        return false;
    }

    public Position MoveUp() {
        return new Position(hero.GetPosition().PosGetX(), hero.GetPosition().PosGetY() - 1);
    }

    public Position MoveDown() {
        return new Position(hero.GetPosition().PosGetX(), hero.GetPosition().PosGetY() + 1);
    }

    public Position MoveLeft() {
        return new Position(hero.GetPosition().PosGetX() - 1, hero.GetPosition().PosGetY());
    }

    public Position MoveRight() {
        return new Position(hero.GetPosition().PosGetX() + 1, hero.GetPosition().PosGetY());
    }

    private class Hero extends Element{

        public Hero(int x, int y) { super(x,y); }

        public void draw(TextGraphics screen) {
            screen.setForegroundColor(TextColor.Factory.fromString("#1F51FF"));
            screen.enableModifiers(SGR.BOLD);
            screen.putString(new TerminalPosition(GetPosition().PosGetX(), GetPosition().PosGetY()), "X");
        }

        public void setPosition(Position position) {
            hero.SetPosition(position);
        }
    }
}
