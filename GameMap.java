

public class GameMap{
	private int x;
	private int y;
	
    public GameMap(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

    public GameMap getLocation(){
    	GameMap location = new GameMap(x,y);
    	return location;
    }

    public boolean equals(Object o){
    	GameMap c = (GameMap)o;

    	if(x == c.getX() && y == c.getY()){
    		return true;
    	} else {
    		return false;
    	}
    }

    public int hashCode(){
    	int hashCode = (x * 10) + y;
    	return hashCode;
    }

    public String toString(){
        return x + " " + y;
    }

}