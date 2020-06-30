package projekt;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MatToImg {
    Mat matrix;
    String filExeten;
    MatOfByte mob;

    public MatToImg(){}


    public void setMatrix(Mat mat, String fileExt){
        matrix = mat;
        this.filExeten = fileExt;
        mob = new MatOfByte();
    }
    public BufferedImage GetBufferedImage(){
        Imgcodecs.imencode(filExeten,matrix,mob);
        byte[] byteArray = mob.toArray();
        BufferedImage bufImage = null;
        try{
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        }catch(Exception e){
            System.out.println("Coś nie tak albo zamknąłeś program");
        }
        return bufImage;


    }


}
