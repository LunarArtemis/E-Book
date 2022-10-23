package state;

import java.awt.Graphics2D;

//import graphic.ImageLoader;
import util.KeyHandler;
import util.MouseHandler;

public class BookSelected extends BookState {

    private static String bookName = "";
    private String path = "src\\state\\Book1.java";
    private int totalFiles = 0;
    //private int currentPage = 0; 

    public static void init(){
    }

    public BookSelected(StateManager sm) { 
        super(sm);
    
        System.out.println("LOAD SUCCESSFUL");
        System.out.println("BookSelected Loaded : " + bookName);
        System.out.println("Path : " + path);
        System.out.println("Total Files : " + totalFiles);
        
        // totalFiles = ImageLoader.getFiles(path+"/"+bookName);
        // System.out.println(totalFiles);
    }
    
    // Method setName 
    public static void setName(String name){
        bookName = name;
    }
    
    public void update(double time) {
        
    }
    
    public void render(Graphics2D g) {
        
    }
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        
    }
}
