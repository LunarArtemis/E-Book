package state;

import java.awt.Graphics2D;

import util.MouseHandler;
import util.KeyHandler;

public abstract class BookState {
    protected StateManager sm;

    public BookState(StateManager sm){
        this.sm = sm;
    }

    public abstract void update(double time);
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);
}
