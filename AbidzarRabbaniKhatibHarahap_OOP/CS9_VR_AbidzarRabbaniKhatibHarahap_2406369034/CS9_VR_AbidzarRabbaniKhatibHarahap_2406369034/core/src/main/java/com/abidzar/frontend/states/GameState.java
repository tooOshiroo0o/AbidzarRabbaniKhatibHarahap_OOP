package core.src.main.java.com.abidzar.frontend.states;

public interface GameState {
    void update(float deltaTime);
    void render(SpriteBatch batch);
    void dispose();
}
