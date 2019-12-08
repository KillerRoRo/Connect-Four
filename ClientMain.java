import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientMain
{
    public static void main(String[] args)
    {
        try
        {

            // creates a connection to the server at the given address & port
            Socket socket = new Socket("127.0.0.1",8001);

            // make input/output streams to this client
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

            ConnectFourGame game=new ConnectFourGame();
            if((int)is.readObject()==ConnectFourGame.RED)
            {
                ClientListener cl = new ClientListener(is,new Frame("Red", os, game));
                Thread t = new Thread(cl);
                t.start();
            }
            else
            {
                ClientListener cl = new ClientListener(is,new Frame("Black", os, game));
                Thread t = new Thread(cl);
                t.start();
            }

        }
        catch(Exception e)
        {
            System.out.println("Error in client main: ");
            e.printStackTrace();
        }
    }
}
