import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(8001);

            ConnectFourGame game=new ConnectFourGame();
            // accepts a users connection
            Socket redsocket = serverSocket.accept();

            ObjectOutputStream redos = new ObjectOutputStream(redsocket.getOutputStream());
            ObjectInputStream redis = new ObjectInputStream(redsocket.getInputStream());

            ServerListener redsl = new ServerListener(redis,redos, ConnectFourGame.RED, game);
            redos.writeObject(1);
            redos.flush();
            Thread t = new Thread(redsl);

            t.start();

            Socket blacksocket = serverSocket.accept();

            ObjectOutputStream blackos = new ObjectOutputStream(blacksocket.getOutputStream());
            ObjectInputStream blackis = new ObjectInputStream(blacksocket.getInputStream());

            ServerListener blacksl = new ServerListener(blackis,blackos, ConnectFourGame.BLACK, game);
            blackos.writeObject(2);
            blackos.flush();
            t = new Thread(blacksl);
            t.start();

        }
        catch(Exception e)
        {
            System.out.println("Error in server main: ");
            e.printStackTrace();
        }
    }
}
