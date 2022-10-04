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
    private int x = 10;
    private int y = 10;
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
    }
    private void ProcessKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) {
            y -= 1;
        }
        if (key.getKeyType() == KeyType.ArrowDown) {
            y += 1;
        }
        if (key.getKeyType() == KeyType.ArrowLeft) {
            x -= 1;
        }
        if (key.getKeyType() == KeyType.ArrowRight) {
            x += 1;
        }
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
        }
    }
    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }
    public void run() throws IOException {
        while (true) {
            draw();
            com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
            ProcessKey(key);
            if (key.getKeyType() == KeyType.EOF) break;
        }
    }
}
