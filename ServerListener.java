import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerListener implements Runnable
{
    //private static int value = 0;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();
    private int player;
    private ConnectFourGame game;

    public ServerListener(ObjectInputStream is, ObjectOutputStream os, int player, ConnectFourGame game)
    {
        this.os = os;
        this.is = is;
        this.player=player;
        this.game=game;
        outs.add(os);
    }

    public void run()
    {
        try
        {
            while(true)
            {
                Command command = (Command) is.readObject();
                if (command.getCommand() == Command.LOG_OFF)
                {
                    break;
                }

                else if(command.getCommand()==Command.ADD_CHIP)
                {
                    //int operand = (Integer)command.getCommandData();
                    if( player== game.getTurn() && game.makeMove((int)command.getCommandData() ) )
                    {
                        for(ObjectOutputStream out:outs)
                        {
                            System.out.println("outs updated");
                            out.writeObject(new Command(Command.ADD_CHIP, (int)command.getCommandData()));
                            out.flush();
                        }
                    }
                }
                else
                {
                    for(ObjectOutputStream out:outs)
                    {
                        game.restart(1);
                        out.writeObject(new Command(Command.RESTART, "whocares"));
                        out.flush();
                    }
                }

                //tell everyone

            }
        }
        catch(Exception e)
        {
            System.out.println("Error in server listener : ");
            e.printStackTrace();
        }
        finally
        {
            outs.remove(os);
        }

    }
}
