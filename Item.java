
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
public class Item{
	private int x;
	private int y;
 	
	private int width;
	private int height;
    private boolean collect;
    private Color brown = new Color(210,105,30);

    private String name;

    public Item(String name, int x, int y, int width, int height){
    	this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        collect = false;
    }

    public void moveUp(){
        y = y -14;
    }

    public void moveDown(){
        y = y + 14;
    }

    public void moveRight(){
        x = x + 14;
    }

    public void moveLeft(){
        x = x - 14;
    }

    public int getX(){
    	return x;
	}

    public String getName(){
        return name;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
 	
	public int getY(){
    	return y;
	}
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public void drawMe(Graphics g){
        if(name.equals("water")){
            g.setColor(Color.BLUE);
            g.fillRect(x,y,14,14);
        }
        if(name.equals("coin")){
            g.setColor(Color.YELLOW);
            g.fillOval(x,y,10,10);
        }
        if(name.equals("tree")){
            g.setColor(brown);
            g.fillRect(x+3,y+4,4,9);
            g.setColor(Color.GREEN);
            g.fillOval(x+2,y,6,6);
        }   
    }

}