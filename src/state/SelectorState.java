package state;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.Font;

import util.MouseHandler;
import util.KeyHandler;

import graphic.Sprite;
import main.Panel;
import math.Vector2f;
import ui.Button;
import util.KeyHandler;
import util.MouseHandler;

public class SelectorState extends State {
    private Button btnBook1;

    private String book1 = "Volume 1";

    private Button bg;
    private Font font;

    public SelectorState(StateManager sm) {
        super(sm);

        BufferedImage imgButton = StateManager.button.getSubimage(0, 0, 0, 0);
        BufferedImage imgHover = StateManager.button.getSubimage(0, 0, 0, 0);
        BufferedImage imgBg = StateManager.bg.getSubimage(0, 0, 640, 384);

        bg = new Button("", imgBg, font, new Vector2f(Panel.width / 2, Panel.height / 2), Panel.width,Panel.height);
    }

    public void update(double time) {

    }

    public void render(Graphics2D g) {
        bg.render(g);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }
}