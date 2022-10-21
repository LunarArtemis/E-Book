package state;

import java.awt.Graphics2D;

import graphic.ImageLoader;
import util.KeyHandler;
import util.MouseHandler;

public class Book1 extends BookState {
    private String path = "src\\state\\Book1.java";
    private int totalFiles = 0;

    public static void init(){
        
    }

    public Book1(StateManager sm) {
        super(sm);

        totalFiles = ImageLoader.getFiles(path);
        System.out.println(totalFiles);
    }
    
    public void update(double time) {
        
    }
    
    public void render(Graphics2D g) {
        
    }
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        
    }
}
