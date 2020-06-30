package projekt;

import java.awt.image.BufferedImage;

public class MirrorReflection {

    public static BufferedImage verticalSymmetryMirror(BufferedImage input, BufferedImage dst, int sw, int sh, int ew, int eh){

            for (int y = sh; y < eh; y++){
                for (int x = sw, mx = 640 - sw - 1; x < ew / 2; x++, mx--){
                 setDst(input,dst,x,mx,y,y);

                }
            }

        return dst;
    }

    public static BufferedImage horizontalSymmetryMirror(BufferedImage input, BufferedImage dst, int sw, int sh, int ew, int eh){
            for (int y = sh, my = 480 - sh - 1; y < eh / 2 ; y++, my--) {
                for (int x = sw; x < ew; x++) {
                    setDst(input, dst, x, x, y, my);


                }

            }
        return dst;
    }

    private static void setDst(BufferedImage input, BufferedImage dst, int x1, int x2, int y1, int y2){
        int p1 = input.getRGB(x1, y1);
        int p2 = input.getRGB(x2, y2);
        dst.setRGB(x1,y1,p2);
        dst.setRGB(x2, y2, p1);
    }

}
