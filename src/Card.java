import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Card 
{
	private String[] imageNames = {
			"Ada-Lovelace.jpg",
			"ADM-Grace-Hopper.jpg",
			"Alan-Turing.jpg",
			"Bjarne-Straustroup.jpg",
			"Dennis-Ritchie.jpg",
			"James-Gosling.jpg",
			"Joe-ORourke.jpg",
			"Steve-Wozniak.jpg"
	};
	private Image[] faceImages;
	
	private Image backImage;
	private Image faceImage;
	
	private boolean selected = false;
	private boolean matched = false;
	
	private int xPos;
	private int yPos;
	
	public static final int SIZE = 200;
	private static final int PADDING = 10;
	private static final int INSET = 10;
	
	public Card(int index, int x, int y)
	{
		super();
		try
		{
			backImage = new ImageIcon("../images/back-of-card.png").getImage();
			faceImages = new Image[imageNames.length];
			for(int i = 0; i < imageNames.length; i++)
			{
				faceImages[i] = new ImageIcon("../images/" + imageNames[i]).getImage();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		faceImage = faceImages[index];
		xPos = x;
		yPos = y;
	}
	
	public void swapImage(Card c)
	{
		Image i = faceImage;
		faceImage = c.faceImage;
		c.faceImage = i;
	}
	
	public void toggleSelected()
	{
		if(selected)
			selected = false;
		else
			selected = true;
	}
	
	public boolean isSelected(int x, int y)
	{
		selected = x > this.xPos && x < this.xPos + SIZE && y < this.yPos + SIZE;
		return selected;
	}
	
	public Image getImage()
	{
		return faceImage;
	}
	
	public boolean isMatch(Card c)
	{
		if(faceImage == c.faceImage)
		{
			return true;
		}
		else
			return false;
	}
	
	public void setMatched()
	{
		matched = true;
	}
	
	public boolean getMatched()
	{
		return matched;
	}
	
	
	public void draw(Graphics g)
	{
		if(matched)
			return;
		if(selected)
			g.drawImage(faceImage, xPos + INSET, yPos + INSET, SIZE - 2 * INSET, SIZE-2*INSET, null);
		else
			g.drawImage(backImage, xPos + INSET, yPos + INSET, SIZE - 2 * INSET, SIZE-2*INSET, null);
	}
	
	
}
