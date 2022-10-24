package graphic;

import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImageLoader {
    // load image to hashmap
    public static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
    
    public ImageLoader(String path){
        loadImages(path); 
    }

    public static BufferedImage loadImage(String path) {
        BufferedImage image = images.get(path);
        if (image == null) {
            image = ImageLoader.loadImage(path);
            images.put(path, image);
        }
        return image;
    }

    // get total file in folder
    public static int getFiles(String path) {
        File folder = new File(path);
        int count = folder.list().length;
        return count;
    }

    // while loop load image to Hashmap
    public static void loadImages(String path) {
        
    }
}
