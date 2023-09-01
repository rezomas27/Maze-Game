import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
 
public class Projectile{
    private int x;
    private int y;
     
    private int width;
    private int height;
     
    private Color red;
	private boolean visible;
	private int score;
	private Color blue;
     
    public Projectile(int x, int y){
         
        this.x = x;
        this.y = y;
         
        this.width = 10;
        this.height = 10;
         
        this.red = new Color(255,0,0);
		this.visible = false;
		blue = new Color(72,61,139);
		
    }
     
 
    public void drawMe(Graphics g){
         
		if(visible == true){
			
			g.setColor(red);
			g.fillOval(x,y,width,height);
		}
    }
	
	public void moveRight(){
		
		if(visible == true){	
			x = x +5;
		}
		if(x > 750){
			visible = false;
		}
	}
	
	public void shoot(){
		visible = true;
	}

	
	public void setPosition(int x, int y){
		if(visible == false){
			this.x = x + 22;
			this.y = y;
		}
	}

	public void changeVisible(){
		visible = false;
	}
	
	public boolean checkCollision(Enemy e){
		
		if(e.getVisible() == true){
			
			int pX = x;
			int pY = y;
			int pWidth = width;
			int pHeight = height;
			int tX = e.getX();
			int tY = e.getY();
			int tWidth = e.getWidth();
			int tHeight = e.getHeight();
			
			
			if(pX+pWidth >= tX && pX <= tX + tWidth  &&  pY+pHeight >= tY && pY <= tY + tHeight ){
				return true;
			}
		}
		return false;
	}

}

