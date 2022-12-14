package main;

import javax.swing.JFrame;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {

    public static final long serialVersionUID = 1L;

    private BufferStrategy bs;
    private Panel gp;
    
    public Window(){
        setTitle("E-Book Reader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addNotify() {
        super.addNotify();

        createBufferStrategy(2);
        bs = getBufferStrategy();

        gp = new Panel(bs, 1280, 720);
        //add(gp);
        setContentPane(gp);
        
    }

}
