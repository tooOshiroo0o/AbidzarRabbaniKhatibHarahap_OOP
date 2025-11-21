package core.src.main.java.com.abidzar.frontend.states;

import java.util.Stack;

public class GameStateManager {
    private final Stack<GameState> states;
    public void push(GameState state) {

    }

    public GameStateManager(Stack<GameState> states) {
        Stack<GameState> states1 = new Stack<>();
    }

    public void pop () {
        GameState top = states.pop();
        top.dispose();
    }

    public void set(GameState state) {
        pop();
        push(state);
    }

    public void update(float delta) {
        states.peek().update(delta);
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }
}
