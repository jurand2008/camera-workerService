package projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private boolean isGreyScale ;


    private JButton Test_button;
    private JPanel Interface_pan ;

    Window(){
        setTitle("Window");
        setSize(640,480);
        setDefaultCloseOperation(Window.EXIT_ON_CLOSE);
    }

    Window(String a) {

        setTitle(a);
        setSize(640,480);
        setDefaultCloseOperation(Window.EXIT_ON_CLOSE);
    }

    Window(String a, int w,int h) {

        setTitle(a);
        setSize(w,h);
        setDefaultCloseOperation(Window.EXIT_ON_CLOSE);

        Test_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isGreyScale = !isGreyScale;
            }
        });

    }

    void SetPanel(Panel pan){
        add(pan,BorderLayout.CENTER);
        add(Interface_pan,BorderLayout.WEST);
    }
    public boolean isGrey(){
        return this.isGreyScale;
    }


}