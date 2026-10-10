package utils.wrappers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EnumMap;

public class Key extends KeyAdapter {
    public enum Keys{
        ARROW_UP(1), ARROW_DOWN(-1), ARROW_LEFT(1), ARROW_RIGHT(-1);
        public int direction;
        Keys(int direction){
            this.direction= direction;
        }
    }

    private final EnumMap<Keys, Boolean> keyStates = new EnumMap<>(Keys.class);
    private final EnumMap<Keys, Boolean> previousKeyStates = new EnumMap<>(Keys.class);
    @Override
    public void keyPressed(KeyEvent e){
        updateKeyState(e.getKeyCode(), true);
    }
    @Override
    public void keyReleased(KeyEvent e){
        updateKeyState(e.getKeyCode(), false);
    }
    private void updateKeyState(int keyCode, boolean isPressed) {
        if (keyCode == KeyEvent.VK_UP) keyStates.put(Keys.ARROW_UP, isPressed);
        if (keyCode == KeyEvent.VK_DOWN) keyStates.put(Keys.ARROW_DOWN, isPressed);
        if (keyCode == KeyEvent.VK_LEFT) keyStates.put(Keys.ARROW_LEFT, isPressed);
        if (keyCode == KeyEvent.VK_RIGHT) keyStates.put(Keys.ARROW_RIGHT, isPressed);
    }

    public boolean isPressed(Keys key){
        return keyStates.getOrDefault(key, false);
    }
    public boolean justPressed(Keys key) {
        return keyStates.getOrDefault(key, false) && !previousKeyStates.getOrDefault(key, false);
    }

    public boolean justReleased(Keys key) {
        return !keyStates.getOrDefault(key, false) && previousKeyStates.getOrDefault(key, false);
    }

    public void update() {
        for (Keys key : Keys.values()) {
            previousKeyStates.put(key, keyStates.getOrDefault(key, false));
        }
    }

}
