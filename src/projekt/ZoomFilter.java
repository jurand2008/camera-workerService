package projekt;

import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

import java.awt.image.BufferedImage;

public class ZoomFilter {


    public static BufferedImage toZoom(BufferedImage input, BufferedImage dst, int sw, int sh, int ew, int eh) {


        for (int y = sh + 180, my1 =sh,my2 = sh + 120,my3 = sh + 240,my4 = sh + 360; y < 300; y++,my1++,my2++,my3++, my4++) {
            int k = 0;
            for (int x = sw + 240, mx1 = sw,mx2 = sw + 160,mx3 = sw + 320,mx4 = sw + 480; x < 400; x++,mx1++,mx2++,mx3++, mx4++){
                setDst(input,dst,x,my1,mx1,my1,mx2,my1,mx3,my1, mx4, my1,
                       my2,mx1,my2,mx2,my2,mx3,my2,mx4,
                        my3,mx1,my3,mx2,my3,mx3,my3,mx4,
                        my4,mx1,my4,mx2,my4,mx3,my4,mx4);
            }
        }
        return dst;
    }


    private static void setDst (BufferedImage input, BufferedImage dst,int x1,int y1, int x2,int y2, int x3, int y3,int x4,int y4,int x5, int y5,
                                int y21, int x21, int y22, int x22, int y23, int x23, int y24, int x24,
                                int y31, int x31, int y32, int x32, int y33, int x33, int y34, int x34,
                                int y41, int x41, int y42, int x42, int y43, int x43, int y44, int x44){
        int p1 = input.getRGB(x1, y1);
        int p2 = input.getRGB(x2, y2);
        // , int y1, int y2, int y3, int y4
        dst.setRGB(x1, y1, p1);
        dst.setRGB(x2, y2, p1);
        dst.setRGB(x3, y3, p1);
        dst.setRGB(x4, y4, p1);
        dst.setRGB(x5, y5, p1);
        dst.setRGB(x21, y21, p1);
        dst.setRGB(x22, y22, p1);
        dst.setRGB(x23, y23, p1);
        dst.setRGB(x24, y24, p1);
        dst.setRGB(x31, y31, p1);
        dst.setRGB(x32, y32, p1);
        dst.setRGB(x33, y33, p1);
        dst.setRGB(x34, y34, p1);
        dst.setRGB(x41, y41, p1);
        dst.setRGB(x42, y42, p1);
        dst.setRGB(x43, y43, p1);
        dst.setRGB(x44, y44, p1);
    }

}






