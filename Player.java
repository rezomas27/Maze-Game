
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
public class Player{
	private int x;
	private int y;
 	
	private int width;
	private int height;
    private int coins;
    private Color purple = new Color(128,0,128);


    public Player(int x, int y, int width, int height){
    	this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        coins = 0;
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
    	g.setColor(Color.PINK);
        g.fillRect(x,y,10,10);
        g.setColor(Color.BLACK);
        g.fillOval(x+2,y+2,3,3);
        g.fillOval(x+6,y+2,3,3);
        g.drawRect(x+2,y+8,1,5);
        g.drawRect(x+8,y+8,1,5);

    }

    public void checkCollisionEnemy(Enemy e){
            
        int pX = x;
        int pY = y;
        int pWidth = width;
        int pHeight = height;
        int tX = e.getX();
        int tY = e.getY();
        int tWidth = e.getWidth();
        int tHeight = e.getHeight();
            
            
        if(pX+pWidth >= tX && pX <= tX + tWidth  &&  pY+pHeight >= tY && pY <= tY + tHeight ){
            //System.out.println("Collision");
            x =0; 
            y =0;
        }

    }


    public void checkReset(){
        if(x > 770 || x < 0 || y < 0 || y > 715){
            x = 0;
            y = 0;
        }
    }

    public void reset(){
        x = 0;
        y = 0;
    }

}