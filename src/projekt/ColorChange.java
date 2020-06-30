package projekt;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorChange {
    public static void coloChangeRGB(BufferedImage input, BufferedImage dst, int sw, int sh, int ew, int eh,double R,double G,double B){

        for(int y = sh; y < eh; y++){
            for(int x = sw; x < ew; x++){
                Color color = new Color(input.getRGB(x,y));
                double red = (color.getRed()* R);
                double green = (color.getGreen()* G);
                double blue = (color.getBlue()* B);
                if(red>=255.0){
                    red=255.0;
                }
                if(green>=255.0){
                    green=255.0;
                }
                if(blue>=255.0){
                    blue=255.0;
                }
                Color newColor = new Color((int)red, (int)green, (int)blue);
                dst.setRGB(x,y,newColor.getRGB());

            }
        }
    }
}
