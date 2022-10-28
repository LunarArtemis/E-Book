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
    private Button btnBook2;
    private Button btnBook3;

    // Edit Book names here
    private String book1 = "Chapter1";
    private String book2 = "Chapter2";
    private String book3 = "Chapter3";

    private Button bg;
    private Font font;

    public SelectorState(StateManager sm) {
        super(sm);

        BufferedImage imgButton = StateManager.button.getSubimage(0, 0, 121, 26);
        BufferedImage imgHover = StateManager.button.getSubimage(0, 0, 122, 28);
        BufferedImage imgBg = StateManager.bg.getSubimage(0, 0, 640, 384);

        // Set Font
        font = new Font("MeatMadness", Font.PLAIN, 48);

        btnBook1 = new Button(book1, imgButton, font, new Vector2f(Panel.width / 2 - 300, Panel.height / 2 + 10), 32, 16);
        btnBook2 = new Button(book2, imgButton, font, new Vector2f(Panel.width / 2, Panel.height / 2 + 10), 32, 16);
        btnBook3 = new Button(book3, imgButton, font, new Vector2f(Panel.width / 2 + 300, Panel.height / 2 + 10), 32, 16);

        btnBook1.addHoverImage(
                btnBook1.createButton(book1, imgHover, font, btnBook1.getWidth(),
                        btnBook1.getHeight(), 32, 20));
        btnBook2.addHoverImage(
                btnBook2.createButton(book2, imgHover, font, btnBook2.getWidth(),
                        btnBook2.getHeight(), 32, 20));
        btnBook3.addHoverImage(
                btnBook3.createButton(book3, imgHover, font, btnBook3.getWidth(),
                        btnBook3.getHeight(), 32, 20));
        bg = new Button("", imgBg, font, new Vector2f(Panel.width / 2, Panel.height / 2), Panel.width,
                Panel.height);

        btnBook1.addEvent(e -> {
            sm.unloadState(StateManager.SELECTOR);
            BookSelected.SetPath(book1);
            System.out.println("UNLOAD SELECTOR");
            BookSelected.setName(book1);
            BookSelected.book1 = true;
            sm.loadState(StateManager.BOOK);
        });

        btnBook2.addEvent(e -> {
            sm.unloadState(StateManager.SELECTOR);
            BookSelected.SetPath(book2);
            System.out.println("UNLOAD SELECTOR");
            BookSelected.setName(book2);
            BookSelected.book1 = true;
            sm.loadState(StateManager.BOOK);
        });

        btnBook3.addEvent(e -> {
            sm.unloadState(StateManager.SELECTOR);
            BookSelected.SetPath(book3);
            System.out.println("UNLOAD SELECTOR");
            BookSelected.setName(book3);
            BookSelected.book1 = true;
            sm.loadState(StateManager.BOOK);
        });

    }

    public void update(double time) {

    }

    public void render(Graphics2D g) {
        bg.render(g);
        btnBook1.render(g);
        btnBook2.render(g);
        btnBook3.render(g);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        btnBook1.input(mouse, key);
        btnBook2.input(mouse, key);
        btnBook3.input(mouse, key);
    }
}