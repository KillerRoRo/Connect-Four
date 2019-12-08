import java.awt.*;
import java.io.ObjectOutputStream;
import javax.swing.*;

public class Frame extends JFrame
{
    //private ObjectOutputStream os;
    //private ConnectFourGame game;
    private Panel p;
    public Frame(String s, ObjectOutputStream os, ConnectFourGame game)
    {
        super(s);
        //this.os=os;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        //this.game=game;
        p = new Panel(game, os);
        Insets frameInsets = getInsets();
        int w = p.getWidth() + (frameInsets.left + frameInsets.right);
        int h = p.getHeight() + frameInsets.top + frameInsets.bottom;

        setPreferredSize(new Dimension(w, h));
        setLayout(null);
        add(p);
        pack();
        setVisible(true);
    }
    public Panel getPanel()
    {return p;}
}
