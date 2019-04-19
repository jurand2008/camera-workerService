package projekt;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class SplitAndDo extends RecursiveAction {

    private BufferedImage sourceImg;
    private BufferedImage destinationIMG;
    private int limit;
    private ArrayList filtrID;
    private int startHeight,startWidth,endHeight,endWidth;

    public SplitAndDo(BufferedImage sourceImg, BufferedImage destinationIMG, int limit, int sw, int sh, int ew, int eh, ArrayList filtrID){

    this.sourceImg = sourceImg;
    this.destinationIMG = destinationIMG;
    this.limit = limit;
    this.startHeight = sh;
    this.endHeight = eh;
    this.startWidth = sw;
    this.endWidth = ew;
    this.filtrID = filtrID;
}



    private void computeDirectly() {

        switch((int)filtrID.get(0)){
            case 1:
                GrayScale.toGrayScale(sourceImg, destinationIMG, startWidth, startHeight, endWidth, endHeight);
                break;
            case 2:
                MirrorReflection.horizontalSymmetryMirror(sourceImg, destinationIMG, startWidth, startHeight, endWidth, endHeight);
                break;
            case 3:
                MirrorReflection.verticalSymmetryMirror(sourceImg, destinationIMG, startWidth, startHeight, endWidth, endHeight);
                break;
            case 4:
                double R = (double) filtrID.get(1);
                double G = (double) filtrID.get(2);
                double B = (double) filtrID.get(3);
                ColorChange.coloChangeRGB(sourceImg, destinationIMG, startWidth, startHeight, endWidth, endHeight,R,G,B);
                break;
            case 5:
                FlipFilter.toFlipMy(sourceImg, destinationIMG, startWidth, startHeight, endWidth, endHeight);
                break;
            case 6:
                ZoomFilter.toZoom(sourceImg, destinationIMG, startWidth, startHeight, endWidth, endHeight);
                break;
            case 7:
                TestedFil.toTest(sourceImg, destinationIMG, startWidth, startHeight, endWidth, endHeight);
                break;
        }


}
    @Override
    public void compute() {
        if((startHeight-endHeight)*(startWidth-endWidth)<limit){
            computeDirectly();
            return;
        }
        int splitWidth = (endWidth - startWidth) / 2;
        int splitHeight = (endHeight - startHeight) / 2;
        invokeAll(new SplitAndDo(sourceImg, destinationIMG, limit,
                                startWidth, startHeight, startWidth+ splitWidth, startHeight+ splitHeight,filtrID),
                    new SplitAndDo(sourceImg, destinationIMG, limit,
                            startWidth+ splitWidth,startHeight+ splitHeight, endWidth, endHeight,filtrID));
        invokeAll(new SplitAndDo(sourceImg, destinationIMG, limit,
                            startWidth+ splitWidth, startHeight, endWidth, startHeight+ splitHeight,filtrID),
                    new SplitAndDo(sourceImg, destinationIMG, limit,
                            startWidth,startHeight+ splitHeight, startWidth+ splitWidth, endHeight,filtrID));

    }

    public static class TestFilter {
        public static BufferedImage toTest(BufferedImage input, BufferedImage dst, int sw, int sh, int ew, int eh) {


            for (int y = sh + 120; y < 360; y++) {

                for (int x = sw + 150; x < 480 && x >= sw + 150; x++) {


                    for (int yy = sh; yy < 240; yy++) {
                        for (int xx = ew; xx < 320; xx++) {



                            for (int yyy = sh; yyy < 240; yyy++) {
                                for (int xxx = 320; xxx < 640; xxx++) {



                                    for (int yyyy = 240; yyyy < 480; yyyy++) {
                                        for (int xxxx = ew; xxxx < 320; xxxx++) {


                                            for (int yyyyy = 240; yyyyy < 480; yyyyy++) {
                                                for (int xxxxx = 320; xxxxx < 640; xxxxx++) {



                                                    setDst(input,dst,x,y,xx,yy,xxx,yyy,xxxx,yyyy,xxxxx,yyyyy);

                                                }
                                            }}   } }}

                        }
                    }
                }


            }

            return dst;
        }


        private static void setDst(BufferedImage input, BufferedImage dst, int x1, int x2, int x3, int x4, int y1, int y2, int y3, int y4, int x5, int y5) {
            int p1 = input.getRGB(x1, y1);
            dst.setRGB(x2, y2, p1);
            dst.setRGB(x3, y3, p1);
            dst.setRGB(x4, y4, p1);
            dst.setRGB(x5, y5, p1);


        }


    }
}
