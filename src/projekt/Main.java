package projekt;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        int delay = 10;
        Window frame = new Window("Kamera",1200,800);

        Panel panel = new Panel();
        panel.setBackground(Color.LIGHT_GRAY);
        frame.SetPanel(panel);

        MatToImg MTI = new MatToImg();


        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Open CV version is " + Core.VERSION);

        VideoCapture Camera = new VideoCapture(0);

        if(!Camera.isOpened()){
            System.out.println("Nie można odnaleźć kamery");
        }else System.out.println("Znaleziono kamerę: "+ Camera.toString());

        frame.setVisible(true);
        Mat cam_img = new Mat();
        System.out.println(cam_img);

        if(Camera.isOpened()) {
            while (true) {
                Camera.read(cam_img);
                if (!cam_img.empty()) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ie) {
                        System.out.println("Problem");
                    }

                    MTI.setMatrix(cam_img, ".jpg");
                    BufferedImage bufImg = MTI.GetBufferedImage();

                    BufferedImage dstImage = bufImg;
//------------------------------------ Początek kodu rozdzielającego na forki
                    if(frame.isGrey()){
//                        SplitAndDo split = new SplitAndDo(bufImg,dstImage,350000,0,0,bufImg.getWidth(),bufImg.getHeight(),0);
//                        ForkJoinPool pool = new ForkJoinPool();
//                        pool.invoke(split);
                    }
 //------------------------------------ Koniec kodu
                    panel.setImg(dstImage);
                    panel.repaint();
                } else {
                    System.out.println("Nic nie pobrano z kamery");
                    break;
                }
            }
            Camera.release();
        }


    }
}
