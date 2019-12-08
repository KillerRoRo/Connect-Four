

import java.awt.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Panel extends JPanel implements KeyListener, MouseListener//, Runnable
{
    public static final int dd=800;
    public static final int ee=800;
    private BufferedImage buffer;
    private ConnectFourGame game;
    private ObjectOutputStream os;
    private int ii;
    //private int ups=100;
   // private int updateCount=0;

    public Panel(ConnectFourGame game, ObjectOutputStream os)
    {
        super();
        setSize(dd+1, ee+1);
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        addKeyListener(this);
        addMouseListener(this);

        ii =0;
        this.game= game;
        this.os=os;
    }
    public void setII(int t){
        ii=t;
    }
    public void paint(Graphics g)
    {
        int z = 0;


        g.setColor(Color.YELLOW);
        g.drawRect(0, 0, getWidth(), getHeight());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        for (int l = 50; l < 750; l = l + 100) {
            z = 0;
            for (int p = 0; p < 510; p = p + 100) {
                g.fillOval(l, p, 70, 70);

            }
        }
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                if (game.getBoard()[r][c] == 1) {

                    g.setColor(Color.RED);
                    g.fillOval(c * 100 + 50, r * 100, 70, 70);
                }
                if (game.getBoard()[r][c] == 2) {
                    g.setColor(Color.BLACK);

                    g.fillOval(c * 100 + 50, r * 100, 70, 70);
                }
            }
        }
        if(!(ii==1) && !(ii==2)) {
            if ( game.getTurn() % 2 == 0) {
                g.setColor(Color.BLUE);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
                g.drawString("Blacks's turn", 300, 650);
            }
            if (!(game.getTurn() % 2 == 0)) {
                g.setColor(Color.BLUE);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
                g.drawString("Red's turn", 300, 650);
            }
        }

        if (game.status() == 4) {
            double p = 0;


            ii=1;
            repaint();


        }
        if(game.status() == 5){
            g.setColor(Color.GREEN);

            ii = 2;
            repaint();
        }



        if(ii ==1){
            g.setColor(Color.BLUE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g.drawString("Click again if you want to play again", 0, 650);
            g.drawString("Red Wins",300,750);
        }
        if(ii==2){
            g.setColor(Color.BLUE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g.drawString("Click again if you want to play again", 00, 650);
            g.drawString("Black Wins",300,750);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times new Roman", Font.BOLD, 20));

        g.drawString("Press d to play with AI", 600,730 );
        g.drawImage(buffer, 0,0, null);
    }
    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }
    public void keyPressed(KeyEvent e )
    {
        try{


                ii =0;
                repaint();
                os.writeObject(new Command(Command.RESTART, "whocares"));
                os.flush();

        }catch(Exception ex)
        {
            System.out.println("Error in server listener : ");
            ex.printStackTrace();
        }


        //if(e.getKeyChar()=='c' || e.getKeyChar()=='C')
          //  game.restart(ConnectFourGame.PvC);
        repaint();
    }
    public void keyTyped(KeyEvent e )
    {

    }
    public void keyReleased(KeyEvent e )
    {
        repaint();
    }
    public void mousePressed(MouseEvent e)
    {
        try{

            if(game.status()==4 || game.status() ==5) {
                ii = 0;
                repaint();
                os.writeObject(new Command(Command.RESTART, "whocares"));
                os.flush();
            }

        }catch(Exception ex)
        {
            System.out.println("Error in server listener : ");
            ex.printStackTrace();
        }
        if(game.status()!=ConnectFourGame.PLAYING)
            return;
        //System.out.println("Hi");
        for(int c=0; c<game.getBoard()[0].length; c++)
        {
            if(e.getX()>c*100 && e.getX()<c*100 +100)
            {
                //game.makeMove(c);
                try{
                    System.out.println("sent from panel");
                    os.writeObject(new Command(Command.ADD_CHIP, (Integer)c));
                    os.flush();
                }catch(Exception ex)
                {
                    System.out.println("Error in Panel: ");
                    ex.printStackTrace();
                }

            }
        }
        repaint();
    }

    public ConnectFourGame getGame() {
        return game;
    }

    public ObjectOutputStream getOs() {
        return os;
    }

    public void mouseReleased(MouseEvent e)
    {

    }
    public void mouseClicked(MouseEvent e)
    {

    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }


}
