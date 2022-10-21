package state;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Font;

import main.Panel;
import math.Vector2f;
import ui.Button;

import util.MouseHandler;
import util.KeyHandler;
public class SelectorState extends BookState {
    private Button btnBook1;


    // Edit Book names here
    private String book1 = "Volume 1";

    private Button bg;
    private Font font;

    public SelectorState(StateManager sm) {
        super(sm);

        BufferedImage imgButton = StateManager.button.getSubimage(0, 0, 0, 0);
        BufferedImage imgHover = StateManager.button.getSubimage(0, 0, 0, 0);
        BufferedImage imgBg = StateManager.bg.getSubimage(0, 0, 640, 384);

        btnBook1 = new Button(book1,imgButton, font, new Vector2f(Panel.width / 2, Panel.height / 2 + 10),
        32, 16);

        btnBook1.addHoverImage(btnBook1.createButton(book1, imgHover, font, 0, 0, 0, 0));

        bg = new Button("", imgBg, font, new Vector2f(Panel.width / 2, Panel.height / 2), Panel.width,Panel.height);
    }

    public void update(double time) {

    }

    public void render(Graphics2D g) {
        bg.render(g);
        btnBook1.render(g);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        btnBook1.input(mouse, key);
        
    }
}