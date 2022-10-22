package graphic;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImageLoader {
    // load image to hashmap
    public static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
    private int TotalPage = 0; 
    
    public static ImageLoader(String path){
        TotalPage = getFiles(path);
        LoadImages(path); 
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
        int count = 0;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                count++;
            }
        }
        return count;
    }

    // while loop load image to Hashmap
    public static void loadImages(String path) {
        int count = 0;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        while (count < listOfFiles.length) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    String name = listOfFiles[i].getName();
                    String[] parts = name.split("\\.");
                    //String part1 = parts[0];
                    String part2 = parts[1];
                    if (part2.equals("jpg")) {
                        ImageLoader.loadImage(path + "/" + name);
                        count++;
                    }
                }
            }
        }
    }
}
