package state;

import java.awt.Graphics2D;

import util.KeyHandler;
import util.MouseHandler;
import graphic.Font;
import graphic.Fontf;
import graphic.Sprite;


public class StateManager {
    private BookState[] gs;
    private int CurrentState;

    public static Graphics2D g;
    public static Sprite button;
    public static Sprite ui;
    public static Sprite bg;
    public static Font font;
    public static Fontf fontf;

    public static final int SELECTOR = 0;
    public static final int BOOK = 1;

    public StateManager(Graphics2D g) {
        StateManager.g = g;
        gs = new BookState[6];


        //ImageLoader.init();

        font = new Font("font/font.png", 10, 10);
        fontf = new Fontf();
        fontf.loadFont("font/Stackedpixel.ttf", "MeatMadness");
        Sprite.currentFont = font;

        bg = new Sprite("menu/bg.png", 640, 384);
        ui = new Sprite("ui/ui.png", 64, 64);
        button = new Sprite("ui/buttons.png", 122, 57);


        // Set States
        CurrentState = BOOK;
        loadState(CurrentState);
    }

    public void loadState(int state) {
        if (state == SELECTOR) {
            gs[state] = new SelectorState(this);
        }
        if (state == BOOK) {
            gs[state] = new BookSelected(this);
        }
    }

    public void unloadState(int state) {
        gs[state] = null;
    }

    public boolean isStateActive(int state) {
        return gs[state] != null;
    }

    public BookState getState(int state) {
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
        g.setFont(StateManager.fontf.getFont("MeatMadness"));
        for (int i = 0; i < gs.length; i++) {
            if (gs[i] != null) {
                gs[i].render(g);
            }
        }
    }

}
