package projekt;

import java.awt.*;
import java.awt.image.BufferedImage;


public class GrayScale {

    public static void toGrayScale(BufferedImage input, BufferedImage dst, int sw,int sh, int ew, int eh){

        for(int y = sh; y < eh; y++){
            for(int x = sw; x < ew; x++){
                Color color = new Color(input.getRGB(x,y));
                int red = (int)(color.getRed()* 0.2126);
                int green = (int)(color.getGreen()* 0.7152);
                int blue = (int)(color.getBlue()* 0.0722);
                int sum = red + green + blue;
                Color shadeOfGray = new Color(sum, sum, sum);
                dst.setRGB(x,y,shadeOfGray.getRGB());

            }
        }
    }
}
