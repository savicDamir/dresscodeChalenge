package xo4x4;

import java.util.ArrayList;

public class minMax extends Thread {

	private Board b;
	private boolean isMax;
	private int depth;
	public int retValue;
	
	public minMax(Board b,boolean isMax, int depth)
	{
		this.b=b;
		this.isMax=isMax;
		this.depth=depth;
	}
	
	@Override
	public void run()
	{
		ArrayList<Integer> list= b.posibleMoves();
		ArrayList<minMax> childThreads= new ArrayList<minMax>();
		
		int boardValue= b.boardValue();
		 
		if (boardValue!=0 || list.size()==0 ||depth==0)
		{
			retValue=boardValue;
			return;
		}
		
		for (Integer move : list) {
			
			try {
				b.setValue(move/10, move%10, 'X');
				minMax m= new minMax((Board) b.clone(), !isMax, depth-1);
				m.start();
				childThreads.add(m);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			} finally {
				b.setValue(move/10, move%10, 'e');
			}
		}
		int highestVal =0; //Integer.MIN_VALUE;
		int lowestVal =0;// Integer.MAX_VALUE;
		for (minMax ch : childThreads) {
			try {
				ch.join();
				if (isMax)
				{
					highestVal+=ch.retValue;
					//highestVal = Math.max(highestVal, ch.retValue);
				}
				else
				{
					lowestVal-=ch.retValue;
					//lowestVal = Math.min(lowestVal, ch.retValue);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		retValue=(isMax)?highestVal:lowestVal;
		
		
	}
}
