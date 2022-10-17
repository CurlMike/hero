import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        arena = new Arena(40, 20);
    }

    private void ProcessKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) {
            arena.moveHero(arena.MoveUp());
        }
        if (key.getKeyType() == KeyType.ArrowDown) {
            arena.moveHero(arena.MoveDown());
        }
        if (key.getKeyType() == KeyType.ArrowLeft) {
            arena.moveHero(arena.MoveLeft());
        }
        if (key.getKeyType() == KeyType.ArrowRight) {
            arena.moveHero(arena.MoveRight());
        }
    }
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() {
        try {
        while (true) {
            draw();
            com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
            ProcessKey(key);
            if (arena.verifyMonsterCollisions()) {
                screen.close();
                break;
            }
            arena.moveMonsters();
            if(arena.verifyMonsterCollisions()){
                screen.close();
                break;
            }

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q'))
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
