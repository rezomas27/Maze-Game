
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class Time implements Runnable{
	private int time;
	public Time(int time){
		this.time = time;
	}

	public void run(){
		while(true){
			time--;
			try{
	            Thread.sleep(1000); //millisecond
	        }catch(InterruptedException ex){
	            Thread.currentThread().interrupt();
	        }
	        //System.out.println(time);
	    }
	}

	public void drawTime(Graphics g){
		g.drawString("Timer : " + time,400,10);
	}

	public int getTime(){
		return time;
	}
}