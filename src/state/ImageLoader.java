package state;

import java.util.HashMap;

import graphic.Sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics2D;
import java.awt.Font;

import main.Panel;
import math.Vector2f;
import ui.Button;

public class ImageLoader{

    public static HashMap<String, Button> images = new HashMap<String, Button>();
    public static Graphics2D g;
    private static Font font;;

    // Temporary path
    public static Button tmpBTN;
    public static Sprite tmpSprite;

    public static void init() {
        font = new Font("MeatMadness", Font.PLAIN, 48);
        loadImages(BookSelected.getPath());
    }

    public ImageLoader(String path) {
        System.out.println("IMAGE LOADER INITIALIZED");
        font = new Font("MeatMadness", Font.PLAIN, 48);
        loadImages(path);
    }

    // get total file in folder
    public static int getFiles(String path) {
        File folder = new File("res/" + path);
        int count = folder.list().length;
        System.out.println(count);
        return count;
    }

    // while loop load image to Hashmap
    public static void loadImages(String path) {
        int count = getFiles(path);
        int i = 1;
        while (i <= count) {
            try {
                System.out.println("Loading Image : " + i);
                tmpSprite = new Sprite("content/Volume1/" + i + ".jpg", 795, 1153);
                tmpBTN = new Button("", Buffer(tmpSprite, 0, 0, 795, 1153), font,
                        new Vector2f(Panel.width / 2, Panel.height / 2), Panel.width / 2, Panel.height / 2);
                images.put(String.valueOf(i), tmpBTN);
                i++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static Button getImage(String key) {
        return images.get(key);
    }

    public static BufferedImage Buffer(Sprite sprites, int x, int y, int width, int height) {
        BufferedImage image = sprites.getSubimage(x, y, width, height);
        return image;
    }
}
