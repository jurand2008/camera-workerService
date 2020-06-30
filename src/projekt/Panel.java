package projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {
    private BufferedImage img;

    public Panel(){

        super();
    }

    public void setImg(BufferedImage imag){
        img = imag;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(this.img == null){
            return ;
        }
        g.drawImage(this.img,0,0,this.img.getWidth(),this.img.getHeight(),null);

    }
}

