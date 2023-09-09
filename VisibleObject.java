package spacegame;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

import spacegame.*;
//extends the world object class, extend this to make stuff we can render
public abstract class VisibleObject extends WorldObject {
    public abstract void draw(Graphics g, Camera camera);

    public BufferedImage loadImage(String path) {
        BufferedImage img = null;
        try {
            File imgFile = new File(path);
            System.out.println(imgFile.getAbsolutePath());
            img = ImageIO.read(imgFile);
            
        } catch (IOException e) {
        }

        //checkTransparency(img);
        return img;
    }

    private static void checkTransparency(BufferedImage image){
        if (containsAlphaChannel(image)){
            System.out.println("image contains alpha channel");
        } else {
            System.out.println("image does NOT contain alpha channel");
        }

        if (containsTransparency(image)){
            System.out.println("image contains transparency");
        } else {
            System.out.println("Image does NOT contain transparency");
        }
    }

    private static boolean containsAlphaChannel(BufferedImage image){
        return image.getColorModel().hasAlpha();
    }

    private static boolean containsTransparency(BufferedImage image){
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if (isTransparent(image, j, i)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isTransparent(BufferedImage image, int x, int y ) {
        int pixel = image.getRGB(x,y);
        return (pixel>>24) == 0x00;
    }
}
