package state;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Font;

import main.Panel;
import math.Vector2f;
import ui.Button;
//import graphic.ImageLoader;
import graphic.Sprite;
import util.KeyHandler;
import util.MouseHandler;

public class BookSelected extends BookState {

    private static String bookName = "";
    private String path = "src\\state\\Book1.java";
    private int totalFiles = 100;  // ******** SET TOTAL FILE IN FOLDER

    private Font font;

    private Button btnMenu;
    private Button btnNext;
    private Button btnPrev;
    private int currentPage = 1;

    public static void init() {

    }

    public BookSelected(StateManager sm) {
        super(sm);

        System.out.println("LOAD SUCCESSFUL");
        System.out.println("BookSelected Loaded : " + bookName);
        System.out.println("Path : " + path);
        System.out.println("Total Files : " + totalFiles);

        // totalFiles = ImageLoader.getFiles(path+"/"+bookName);
        // System.out.println(totalFiles);

        BufferedImage imgButton = StateManager.button.getSubimage(0, 0, 121, 26);
        BufferedImage imgHover = StateManager.button.getSubimage(0, 0, 122, 28);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnMenu = new Button("Menu", imgButton, font,
                new Vector2f(Panel.width / 2 - 530, Panel.height / 2 + 300), 32, 16);
        btnNext = new Button(">", imgButton, font,
                new Vector2f(Panel.width / 2 + 50, Panel.height / 2 + 300), 32, 16);
        btnPrev = new Button("<", imgButton, font,
                new Vector2f(Panel.width / 2 - 75, Panel.height / 2 + 300), 32, 16);

        btnMenu.addHoverImage(
                btnMenu.createButton("Menu", imgHover, font, btnMenu.getWidth(), btnMenu.getHeight(), 32, 20));
        btnNext.addHoverImage(
                btnNext.createButton(">", imgHover, font, btnNext.getWidth(), btnNext.getHeight(), 32, 20));
        btnPrev.addHoverImage(
                btnPrev.createButton("<", imgHover, font, btnPrev.getWidth(), btnPrev.getHeight(), 32, 20));
        btnMenu.addEvent(e -> {
            sm.unloadState(StateManager.BOOK);
            sm.loadState(StateManager.SELECTOR);
        });

        btnNext.addEvent(e -> {
            if (currentPage < totalFiles) {
                currentPage++;
                System.out.println("Current Page : " + currentPage);
            }
        });
        btnPrev.addEvent(e -> {
            if (currentPage > 0) {
                currentPage--;
                System.out.println("Current Page : " + currentPage);
            }
        });
    }

    public static void setName(String name) {
        bookName = name;
    }

    public void update(double time) {
    }

    public void render(Graphics2D g) {
        // render current page
        if(currentPage > 0) {
            Sprite.drawArray(g, String.valueOf(currentPage),
                new Vector2f(Panel.width / 2 - String.valueOf(currentPage).length() + 500,
                        Panel.height / 2 - 330),
                68, 68,
                50);
        }
        btnMenu.render(g);
        btnNext.render(g);
        btnPrev.render(g);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        btnNext.input(mouse, key);
        btnPrev.input(mouse, key);
        btnMenu.input(mouse, key);

        // press left right to change page
        if (key.left.down) {
            if (currentPage > 0) {
                currentPage--;
                System.out.println("Current Page : " + currentPage);
            }
        }
        if (key.right.down) {
            if (currentPage < totalFiles) {
                currentPage++;
                System.out.println("Current Page : " + currentPage);
            }
        }

    }
}