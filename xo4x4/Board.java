package xo4x4;

import java.util.ArrayList;

public class Board implements Cloneable{

	public int board[][]= new int[4][4];
	
	public Board()
	{
		for (int i=0;i<4;i++)
			for (int j=0;j<4;j++)
				board[i][j]=0;
	}
	
	public Object clone() throws CloneNotSupportedException 
	{ 
			return super.clone(); 
	}
	
	public boolean isWin(char sym)
	{
		int valSym = (sym=='X')?1:2;//get value from simbol
		
		for (int i=0;i<4;i++)//check row/column victory
		{
			int row=0;
			int col=0;
			for (int j=0;j<4;j++)
			{
				if (board[i][j]==valSym) row++;
				if (board[j][i]==valSym) col++;
			}
			if (row==4 || col==4) return true;
		}
		
		int mainDia=0;
		int altDia=0;
		for (int i=0; i<4;i++)//check diagonals 
		{
			if (board[i][i]==valSym) mainDia++;
			if (board[i][3-i]==valSym) altDia++;
		}
		if (mainDia==4 || altDia==4) return true;
		
		
		
		for (int i=0;i<3;i++) //check box shape
			for (int j=0;j<3;j++)
			{
				if (board[i][j]==valSym && board[i+1][j]==valSym && board[i][j+1]==valSym && board[i+1][j+1]==valSym ) return true;
			}
		
		return false;
	}

	public boolean isDraw()
	{
		if (isWin('X')==true) return false;
		if (isWin('O')==true) return false;
		if (posibleMoves().size()!=0) return false;
		return true;
	}
	
	public int boardValue()
	{
		if (isWin('X')) return 10;
		if (isWin('O')) return -10;
		return 0;
	}
	
	public void setValue(int row, int col, char sym)
	{
		int valSym = (sym=='X')?1:2;
		if (sym=='e') valSym=0;
		board[row][col]=valSym;
	}
	
	
	public ArrayList<Integer> posibleMoves()
	{
		ArrayList<Integer> list= new ArrayList<Integer>();
		
		for (int i=0; i<4;i++)
			for (int j=0;j<4;j++)
				if (board[i][j]==0) list.add(i*10+j);
		return list;
	}
}
