package projekt;


import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.lang.Math;
import javax.imageio.*;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;


import org.opencv.imgproc.Imgproc;

public class TestedFil {

    public static BufferedImage toTest(BufferedImage input, BufferedImage dst, int sw, int sh, int ew, int eh) {


//for(int k = 0; k < 240; k++)}
        for (int y = sh, my = 480 - sh - 1; y < eh / 2 ; y++, my--) {
            int k = 0;
            for (int x = sw, mx = 640 - sw - 1; x < ew / 2; x++, mx--){
                setDst(input,dst,x,mx,mx,x,y,my,y,my);
            }
        }
        return dst;
    }


    private static void setDst (BufferedImage input, BufferedImage dst,int x1, int x2, int x3,int x4, int y1, int y2, int y3, int y4){
        int p1 = input.getRGB(x1, y1);
        int p2 = input.getRGB(x2, y2);
       // , int y1, int y2, int y3, int y4
        dst.setRGB(x1, y1, p1);
        dst.setRGB(x2, y2, p1);
       dst.setRGB(x3, y3, p1);
        dst.setRGB(x4, y4, p1);
      //  dst.setRGB(x4, y4, p1);
    }

}

//  for (int x = sw+k