/*package projekt;

import java.awt.image.BufferedImage;

public class PuzzleFilter {
    public static BufferedImage toZoom(BufferedImage input, BufferedImage dst, int sw, int sh, int ew, int eh) {


        for (int y = sh + 120, my = 480 - sh - 1; y < eh / 2; y++, my--) {
            int k = 0;
            for (int x = sw + k, L = 150 - x - 1; x < (ew / 2) && x >= sw + k; k++, L--) {
                for ( )
                    setDst(input, dst, x, x, y, y);
                setDst2(input, dst, x, L, y, my);
            }
        }
        return dst;
    }


    private static void setDst(BufferedImage input, BufferedImage dst, int x1, int x2, int y1, int y2) {
        int p1 = input.getRGB(x1, y1);
        int p2 = input.getRGB(x2, y2);
        dst.setRGB(x1, y1, p2);
        dst.setRGB(x2, y2, p1);
    }

    private static void setDst2(BufferedImage input, BufferedImage dst, int x1, int x2, int y1, int y2) {
        int p1 = input.getRGB(x1, y1);
        int p2 = input.getRGB(x2, y2);
        dst.setRGB(x1, y1, p2);
        dst.setRGB(x2, y2, p1);

    }
}





}
*/