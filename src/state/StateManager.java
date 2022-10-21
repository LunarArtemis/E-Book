package state;

import java.awt.Graphics2D;

import util.KeyHandler;
import util.MouseHandler;

import graphic.ImageLoader;
import graphic.Sprite;

public class StateManager {
    private State[] gs;
    private int CurrentState;

    public static Graphics2D g;
    public static Sprite button;
    public static Sprite ui;
    public static Sprite bg;

    public static final int SELECTOR = 0;
    public static final int BOOK1 = 1;
    public static final int BOOK2 = 2;
    public static final int BOOK3 = 3;

    public StateManager(Graphics2D g) {
        StateManager.g = g;
        gs = new State[6];


        ImageLoader.init();

        bg = new Sprite("menu/bg.png", 640, 384);
        ui = new Sprite("ui/ui.png", 64, 64);
        button = new Sprite("ui/buttons.png", 122, 57);


        // Set States
        CurrentState = SELECTOR;
        loadState(CurrentState);
    }

    public void loadState(int state) {
        if (state == SELECTOR) {
            gs[state] = new SelectorState(this);
        }
        if (state == BOOK1) {
            gs[state] = new BookState(this);
        }
        if (state == BOOK2) {
            gs[state] = new BookState(this);
        }
        if (state == BOOK3) {
            gs[state] = new BookState(this);
        }
    }

    public void unloadState(int state) {
        gs[state] = null;
    }

    public boolean isStateActive(int state) {
        return gs[state] != null;
    }

    public State getState(int state) {
        return gs[state];
    }

    public void addAndpop(int state) {
        addAndpop(state, 0);
    }

    public void addAndpop(int state, int remove) {
        unloadState(state);
        loadState(state);
    }

    public void update(double time) {
        for (int i = 0; i < gs.length; i++) {
            if (gs[i] != null) {
                gs[i].update(time);
            }
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        for (int i = 0; i < gs.length; i++) {
            if (gs[i] != null) {
                gs[i].input(mouse, key);
            }
        }
    }

    public void render(Graphics2D g) {
        
    }

}
