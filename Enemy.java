
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
public class Enemy implements Runnable{
	private int x;
	private int y;
 	
	private int width;
	private int height;

    private int xdirection;
    private int ydirection;

    private boolean visible;


    public Enemy(int x,int y,int width, int height){
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height = height;
        xdirection = (int)(Math.random()*3 + 1);
        ydirection = (int)(Math.random()*3 + 1);
        visible = true;

    }

    public void run(){

        while(true){

            try{
                Thread.sleep(200); //millisecond
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
            if(xdirection == 1){
                x = x - 14;
            }else if(xdirection == 2){
                x = x + 14;
            }

            if(ydirection == 1){
                y = y +14;
            }else if(ydirection ==2){
                y = y -14;
            }
            
            if(x > 720){
                xdirection = 1;
            }
                
            if(x < 20){
                xdirection = 2;
            }

            if(y > 700){
                ydirection = 2;
            }

            if(y < 20){
                ydirection = 1;
            }

            //System.out.println("enemy moving " + x + " " + y);

            try{
                Thread.sleep(200); //millisecond
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
    	
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
    public boolean getVisible(){
        return visible;
    }
    public void setVisible(){
        visible = false;
    }

    public void drawMe(Graphics g){
        //System.out.println("enemy drawing " + x + " " + y);
        if(visible = true){
        	g.setColor(Color.RED);
        	g.fillRect(x,y,10,10);
            g.setColor(Color.BLACK);
            g.fillOval(x+2,y+2,3,3);
            g.fillOval(x+6,y+2,3,3);
            g.drawRect(x+2,y+8,1,5);
            g.drawRect(x+8,y+8,1,5);
        }

    }
}