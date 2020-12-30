package xo4x4;

import java.util.ArrayList;

public class ComPlayer {

	private char sym;
	private Board board;
	
	public ComPlayer(char sym)
	{
		this.sym=sym;
	}
	
	public void playMove ()
	{
		try {
			board = (Board) main.mainBoard.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}  
   
        ArrayList<Integer> posibleMoves= board.posibleMoves();
        ArrayList<minMax> childThreads= new ArrayList<minMax>();
        
        for (Integer move : posibleMoves)
        {
				board.setValue(move/10, move%10, 'X');
				minMax m= new minMax(board,sym=='X',16);
				m.start();
				childThreads.add(m);
				board.setValue(move/10, move%10, 'e');        	
        }
        
        int col = 0;
        int row = 0;
        int highestVal = Integer.MIN_VALUE;
		int lowestVal = Integer.MAX_VALUE;
		int index=-1;
        for (minMax ch : childThreads) {
        	index++;
        	
        	try {
				ch.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (sym=='X')
			{
				if (ch.retValue>highestVal)
				{
					row=posibleMoves.get(index)/10;
					col=posibleMoves.get(index)%10;
					highestVal=ch.retValue;
				}
			}
			else
			{
				if (ch.retValue<lowestVal)
				{
					row=posibleMoves.get(index)/10;
					col=posibleMoves.get(index)%10;
					lowestVal=ch.retValue;
				}
			}
		}
        main.setValue(row,col,sym);
	}
	
}
