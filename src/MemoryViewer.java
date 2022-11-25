import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MemoryViewer extends JFrame implements MouseListener
{
	public static final int BOARDER_WIDTH = 20;
	public static final int NUM_OF_CARDS = 16;
	public static final int NUM_OF_ROWS = 4;
	public static final int NUM_OF_COLUMNS = 4;
	public static final int MARGIN = 10;
	private Card[] cards;
	
	private static int numSelected = 0;
	
	private static Card cardOne;
	private static Card cardTwo;
	
	public MemoryViewer()
	{
		super("Memory Game");
		
		int width = 2 * MARGIN + NUM_OF_COLUMNS * Card.SIZE;
		int height = 2 * MARGIN + (NUM_OF_CARDS / NUM_OF_COLUMNS) * Card.SIZE;
		
		this.setBounds(200, 50, 0, 0);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setPreferredSize(new Dimension(width, height));
		
		cards = new Card[NUM_OF_CARDS];
		for(int i = 0; i < NUM_OF_CARDS; i++)
		{
			cards[i] = new Card(i / 2, MARGIN + i % NUM_OF_COLUMNS * Card.SIZE, MARGIN + i / NUM_OF_COLUMNS * Card.SIZE);
		}
		
		for(int i = 0; i < NUM_OF_CARDS; i++)
		{
			cards[i].swapImage(cards[(int) (Math.random() * NUM_OF_CARDS)]);
		}
		
		this.pack();
		this.addMouseListener(this);
	}
	
	public boolean gameWon()
	{
		for(int i = 0; i < NUM_OF_CARDS; i++)
		{
			if(!cards[i].getMatched())
				return false;
		}
		return true;
	}
	
	@Override
	public void mousePressed(MouseEvent p) 
	{
		for(int i = 0; i < NUM_OF_CARDS; i++)
		{
			if(!cards[i].getMatched() && cards[i].isSelected(p.getX(), p.getY()))
			{
				if(numSelected == 0)
				{
					cardOne = cards[i];
					cardOne.toggleSelected();
					numSelected = 1;
				}
				
				else if(numSelected == 1)
				{
					if(cards[i] == cardOne)
						return;
					cardTwo = cards[i];
					cardTwo.toggleSelected();
					
					checkMatch();
				}
				this.repaint();
				break;
			}
		}
	}
	
	public static void checkMatch()
	{
		if(cardOne != null && cardTwo != null)
		{
			if(cardOne.isMatch(cardTwo))
			{
				cardOne.setMatched();
				cardTwo.setMatched();
			}
			else
			{
				numSelected = 0;
				cardOne.toggleSelected();
				cardTwo.toggleSelected();
			}
		}
	}
	
	public static void main(String[] args)
	{
		MemoryViewer mv = new MemoryViewer();
		mv.setVisible(true);
		
		while(true)
		{
			checkMatch();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
