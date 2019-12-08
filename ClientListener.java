import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientListener implements Runnable
{
    private ObjectInputStream is;
    private Frame frame;

    public ClientListener(ObjectInputStream is, Frame frame)
    {
        this.frame=frame;
        this.is = is;
    }

    public void run()
    {
        while(true)
        {
            try
            {
                Command command = (Command) is.readObject();
                if (command.getCommand() == Command.ADD_CHIP)
                {
                    System.out.println("Clients updated");
                    frame.getPanel().getGame().makeMove((int)command.getCommandData());
                    frame.getPanel().repaint();
                }
                if(command.getCommand()==Command.RESTART)
                {
                    frame.getPanel().getGame().restart(1);
                    frame.getPanel().setII(0);
                    frame.getPanel().repaint();
                }
                //frame.updateValue((Integer)command.getCommandData());
            }
            catch(Exception e)
            {
                System.out.println("Error in client listener : ");
                e.printStackTrace();
            }
        }

    }
}
