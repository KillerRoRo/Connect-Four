import java.io.Serializable;

public class Command implements Serializable
{
    public static final int RESTART=3;
    public static final int LOG_OFF=2;
    public static final int ADD_CHIP=1;
    private int command=0;
    private Object commandData=0;

    public Command(int command)
    {
        this.command=command;
    }
    public Command(int command, Object commandData)
    {
        this.command=command;
        this.commandData=commandData;
    }
    public int getCommand()
    {return command;}
    public Object getCommandData()
    {return commandData;}
    public void setCommand(int command)
    {this. command=command;}
    public void setCommandData(Object commandData)
    {this. commandData=commandData;}
}
