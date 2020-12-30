package xo4x4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class main {
	static JButton buttons[];
	static JFrame frame;
	public static Board mainBoard;
	public static ComPlayer cmPl;
	public char human;
	
	class ButtonActionPerformed implements ActionListener{
		int buttIndex;

		public ButtonActionPerformed(int index) {
			buttIndex= index;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int row= buttIndex/4;
			int col= buttIndex%4;
			main.setValue(row, col, human);
			main.cmPl.playMove();
		}
		 
	}
	public main(char sym)
	{
		frame = new JFrame("Tic-tac-toe");  
        if (sym=='X')
			JOptionPane.showMessageDialog(frame,"Human goes first");
		else 
			JOptionPane.showMessageDialog(frame,"Computer goes first");

        JPanel panel = new JPanel();  
        panel.setLayout(new GridLayout(4, 4));
        
        human=sym;
        
        buttons= new JButton[16];
        for (int i=0; i<16;i++) {
        	buttons[i]= new JButton();
        	buttons[i].addActionListener(new ButtonActionPerformed(i));
        	panel.add(buttons[i]);
        }
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(300, 300);
        frame.setLocation(400, 300);
		
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        
        main.mainBoard=new Board();
        if (sym=='O') {	
        	main.cmPl=new ComPlayer('X');
        	main.cmPl.playMove();
        }
        else
        	main.cmPl=new ComPlayer('O');
        
		
	}
	
	public static void setValue(int row, int col, char sym)
	{
		buttons[row*4+col].setText(Character.toString(sym));
		buttons[row*4+col].setEnabled(false);
		main.mainBoard.setValue(row, col, sym);
		
		if (main.mainBoard.isWin('X')) { 
			JOptionPane.showMessageDialog(frame,
			    "X won");
			frame.setVisible(false); 
			frame.dispose();
		}
		else
			if(main.mainBoard.isWin('O')){ 
				JOptionPane.showMessageDialog(frame,
					    "O won");
					frame.setVisible(false); 
					frame.dispose();
				}
			else
			if (main.mainBoard.isDraw()){ 
				JOptionPane.showMessageDialog(frame,
					    "Draw");
					frame.setVisible(false); 
					frame.dispose();
				}
	}
	public static void main(String[] args) {
		/*frame = new JFrame("Tic-tac-toe");  
        JPanel panel = new JPanel();  
        JLabel jlable= new JLabel("Izaberite da li zelite da igrate prvi(X) ili drugi(O)");
        panel.add(jlable);
        JButton x=new JButton("X");
        JButton o=new JButton("O");
        x.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new main('X');
				
			}
		});
        
        o.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new main('O');
			}
		});
        panel.add(x);
        panel.add(o);
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(300, 300);
        frame.setLocation(400, 300);*/
		if (Math.random()<0.5)
		{
			new main('X');
		}
		else
		{
			new main('O');
		}
	}


}
