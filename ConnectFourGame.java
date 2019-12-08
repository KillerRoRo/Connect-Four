public class ConnectFourGame
{
    public final static int PvC=2;
    public final static  int PvP=1;
    private int type;
    private int[][] board;
    public static final int EMPTY = 0;
    public static final int RED = 1;
    public static final int BLACK =2;
    public static final int DRAW =3;
    public static final int RED_WINS =4;
    public static final int BLACK_WINS =5;
    public static final int PLAYING =6;
    private int turn;
    public ConnectFourGame(int type)
    {
        restart(type);
    }
    public ConnectFourGame()
    {
        restart(1);
    }
    public int getType()
    {return type;}
    public int getTurn()
    {return turn;}
    public void restart(int t)
    {
        {
            type=t;
            board = new int[6][7];
            turn=1;
            for(int r=0; r<6;r++)
            {
                for(int c=0; c<7; c++)
                {
                    board[r][c] = EMPTY;
                }
            }
        }
    }

    public boolean dropPiece(int c)
    {

        for(int r=5; r>=0; r--)
        {
            if(board[r][c] == EMPTY)
            {
                board[r][c] = turn;
                System.out.println("SHould work. Turn is " + turn);
                switchTurns();
                return true;
            }
        }
        return false;
    }
    public boolean makeMove(int c)
    {
        if(type==PvP)
        {
            if(dropPiece(c))
            {

                return true;
            }

        }
        else if(type==PvC)
        {
            if(dropPiece(c))
            {
                if(status()==PLAYING)
                while(dropPiece((int)(Math.random()*board[0].length))==false);
                return true;

            }


        }
        return false;
    }
    public void switchTurns()
    {
        turn=(turn)%2 +1;
    }

    public int[][] getBoard()
    {
        return board;
    }
    public int status()
    {
        // horizontal
        for(int r=0; r<6;r++)
        {
            for(int c=0; c<=3; c++)
            {

                if(board[r][c] == RED &&board[r][c+1] == RED
                        &&board[r][c+2] == RED &&board[r][c+3] == RED)
                    return RED_WINS;
                else if(board[r][c] == BLACK &&board[r][c+1] == BLACK
                        &&board[r][c+2] == BLACK &&board[r][c+3] == BLACK)
                    return BLACK_WINS;
            }
        }

        // veritical
        for(int r=0; r<=2;r++)
        {
            for(int c=0; c<7; c++)
            {
                if(board[r][c] == RED &&board[r+1][c] == RED
                        &&board[r+2][c] == RED &&board[r+3][c] == RED)
                    return RED_WINS;
                else if(board[r][c] == BLACK &&board[r+1][c] == BLACK
                        &&board[r+2][c] == BLACK &&board[r+3][c] == BLACK)
                    return BLACK_WINS;
            }
        }
        //
        //
        //
        //
        for(int r=0; r<=2;r++)
        {
            for(int c=3; c<7; c++)
            {

                if(board[r][c] == RED &&board[r+1][c-1] == RED
                        &&board[r+2][c-2] == RED &&board[r+3][c-3] == RED)
                    return RED_WINS;
                else if(board[r][c] == BLACK &&board[r+1][c-1] == BLACK
                        &&board[r+2][c-2] == BLACK &&board[r+3][c-3] == BLACK)
                    return BLACK_WINS;
            }
        }

        //
        //
        //
        //
        for(int r=0; r<=2;r++)
        {
            for(int c=0; c<=3; c++)
            {

                if(board[r][c] == RED &&board[r+1][c+1] == RED
                        &&board[r+2][c+2] == RED &&board[r+3][c+3] == RED)
                    return RED_WINS;
                else if(board[r][c] == BLACK &&board[r+1][c+1] == BLACK
                        &&board[r+2][c+2] == BLACK &&board[r+3][c+3] == BLACK)
                    return BLACK_WINS;
            }
        }

        // playing
        for(int r=0; r<6;r++)
        {
            for(int c=0; c<7; c++)
            {

                if(board[r][c] == EMPTY)
                    return PLAYING;
            }
        }

        return DRAW;
    }

    public void draw()
    {
        for(int r=0; r<6;r++)
        {
            System.out.print("|");
            for(int c=0; c<7; c++)
            {
                System.out.print(" ");
                if(board[r][c] == EMPTY)
                    System.out.print(" ");
                else if(board[r][c] == RED)
                    System.out.print("R");
                else
                    System.out.print("B");
                System.out.print(" |");
            }
            System.out.print("\n");
        }
        System.out.print("-----------------------------\n");
    }
}