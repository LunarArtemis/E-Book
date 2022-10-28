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
    private String book1 = "Chapter1";

    private Button bg;
    private Font font;

    public SelectorState(StateManager sm) {
        super(sm);

        BufferedImage imgButton = StateManager.button.getSubimage(0, 0, 121, 26);
        BufferedImage imgHover = StateManager.button.getSubimage(0, 0, 122, 28);
        BufferedImage imgBg = StateManager.bg.getSubimage(0, 0, 640, 384);

        // Set Font
        font = new Font("MeatMadness", Font.PLAIN, 48);

        btnBook1 = new Button(book1, imgButton, font, new Vector2f(Panel.width / 2, Panel.height / 2 + 10), 32, 16);

        btnBook1.addHoverImage(
                btnBook1.createButton(book1, imgHover, font, btnBook1.getWidth(),
                        btnBook1.getHeight(), 32, 20));
        bg = new Button("", imgBg, font, new Vector2f(Panel.width / 2, Panel.height / 2), Panel.width, Panel.height);

        btnBook1.addEvent(e -> {
            sm.unloadState(StateManager.SELECTOR);
            BookSelected.SetPath(book1);
            System.out.println("UNLOAD SELECTOR");
            BookSelected.setName(book1);
            BookSelected.book1 = true;
            sm.loadState(StateManager.BOOK);
        });

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