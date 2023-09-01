
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.*;

public class Screen extends JPanel implements KeyListener,ActionListener{
    private Player p1;
    private Time t1;

    private HashMap<GameMap,Item> grid = new HashMap<GameMap,Item>();
    private Color yellow = new Color(255,255,0);
    private Color brown = new Color(160,82,50);
    private Color purple = new Color(128,0,128);
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private int coins; 
    private boolean checkCollisions;
    private int time;
    private boolean cantright;
    private boolean cantleft;
    private boolean cantup;
    private boolean cantdown;
    private int background;
    private int totalcoins;
    private Projectile p2;

    public Screen() {
        this.setLayout(null);
        addKeyListener(this);
        this.setFocusable(true);

        p1 = new Player(0,0,10,10);
        t1 = new Time(100);
        p2 = new Projectile(p1.getX(),p1.getY());
        coins = 0;
        background =1;

        checkCollisions = false;
        cantright = false;
        cantleft = false;
        cantdown = false;
        cantup = false;

        String coin = "coin";
        String empty = "empty";
        String water = "water";
        String enemy = "enemy";
        String tree = "tree";
        int x = 42;
        int y = 14;

        for(int r = 0; r < 50; r++){
            for(int c =0; c < 54; c++){
                int random = (int)(Math.random()*200);
                //System.out.println("RRRRRR" + random);
                if(random == 0 || random == 12){
                    grid.put(new GameMap(x,y), new Item(coin,x,y,10,10));
                    totalcoins++;
                    System.out.println(totalcoins);
                }
                if(random == 1 || random == 2 || random == 3 || random == 9 || random == 10){
                    grid.put(new GameMap(x,y), new Item(water,x,y,14,14));
                }
                if(random == 4 || random == 5 || random == 6 || random == 7 || random == 8 || random == 11){
                    grid.put(new GameMap(x,y), new Item(tree,x,y,14,14));
                }

                //System.out.println(c);

                int xrandom = (int)(Math.random()*54 + 1);
                int yrandom = (int)(Math.random()*51 + 1);
                //System.out.println("yrando" + xrandom);
                //System.out.println("yrando" + yrandom);
                
                if(x <= 770){
                    x = 0 + (14 * xrandom);
                }else if(x >= 770){
                    x = (14 * xrandom);
                }
                if(y <= 715){
                    y = (14 * yrandom);
                }else if(y >= 715){
                    y = (14 * yrandom); 
                }
                if(y < 7){
                    y = 14;
                }
                if(y > 715){
                    y = -28;
                }

                if(x >= 770){
                    x = 28;
                }
                if(x <= 0){
                    x = 70;
                }
                //System.out.println("x : " + x);
                //System.out.println("y : " + y);

            }
            //System.out.println(r);
        
        }

        //System.out.println("**********" + grid.size());


        int x2 = 14;
        int y2 = 14;
        for(int i =0; i < 30; i++){
            enemies.add(new Enemy(x,y,2,2));

            int xrandom = (int)(Math.random()*55 + 1);
            int yrandom = (int)(Math.random()*55 + 1);

            if(x <= 770){
                x = x + (14 * xrandom);
            }else if(x >= 770){
                x = x - (14 * xrandom);
            }
            if(y <= 715){
                y = y + (14 * yrandom);
            }else if(y >= 715){
                y = y - (14 * yrandom); 
            }

        }

        for(int i =0; i < 30; i++){
            Enemy e = (Enemy)enemies.get(i);
            Thread moveEnemy = new Thread(e,"Enemy");
            moveEnemy.start();
        }

        p1.checkReset();

        Thread timer = new Thread(t1,"Timer");   
        timer.start();
    
    }

    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(770,728);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setColor(Color.BLACK);


        if(background == 1){
            g.setColor(brown);
            g.fillRect(0,0,770,728);
            Color brown1 = new Color(200,82,50);
            g.setColor(brown1);

            int x1 = 0;
            int y1 = 0; 
            int x2 = 770;
            int y2 = 0;
            for(int i = 0; i < 60; i++){
                g.drawLine(x1,y1,x2,y2);
                y1+=14;
                y2+=14;
            }
            x1 = 0;
            y1 = 0;
            x2 = 0;
            y2 = 728;
            for(int i = 0; i < 60; i++){
                g.drawLine(x1,y1,x2,y2);
                x1+=14;
                x2+=14;
            }
            p1.drawMe(g);


            // DRAW ITEMS 
            Iterator<GameMap> it1 = grid.keySet().iterator();
            while(it1.hasNext()){
                GameMap k = it1.next();
            
                if(grid.get(k).getName().equals("water")){
                    int x  = k.getX();
                    int y = k.getY();
                    Item water = new Item("water",x,y,10,10);
                    water.drawMe(g);
                }else if(grid.get(k).getName().equals("tree")){
                    int x  = k.getX();
                    int y = k.getY();
                    Item tree = new Item("tree",x,y,10,10);
                    tree.drawMe(g);
                }else if(grid.get(k).getName().equals("coin")){
                    int x  = k.getX();
                    int y = k.getY();
                    Item coin = new Item("coin",x,y,10,10);
                    coin.drawMe(g);

                }
            }

            p2.drawMe(g);
            
            for(int i =0; i < enemies.size(); i++){
                enemies.get(i).drawMe(g);
                p1.checkCollisionEnemy(enemies.get(i));
                p1.checkReset();

                if(p2.checkCollision(enemies.get(i)) == true){
                    System.out.println("Kill");
                    enemies.remove(enemies.get(i));
                }
            }
            p1.drawMe(g);
            t1.drawTime(g);
            g.drawString("You have " + coins + " coins",600,10);
            g.drawString("Press the space bar to shoot",200,10);



            if(coins == totalcoins){
                background = 2;
            }
            if(t1.getTime() == 0){
                background = 3;
                
            }
        }

        if(background == 2){
            g.setColor(Color.GREEN);
            g.fillRect(0,0,800,800);
            g.setColor(purple);
            Font fontbig = new Font("Arial", Font.PLAIN, 30);
            g.setFont(fontbig);
            g.drawString("YOU WIN!",200,200);
        }
        if(background == 3){
            g.setColor(Color.GREEN);
            g.fillRect(0,0,800,800);
            g.setColor(purple);
            Font fontbig = new Font("Arial", Font.PLAIN, 30);
            g.setFont(fontbig);
            g.drawString("YOU LOSE!",200,200);
        }
    }

    public Map<GameMap,Item> getGrid(){
        return grid;
    }

    public void keyPressed(KeyEvent e){
        //System.out.println( "key code: " + e.getKeyCode());



        if(cantright == false){
            if(e.getKeyCode() == 39){//right arrow
                p1.moveRight();
                cantleft = false;
                cantup = false;
                cantdown = false;
            }
        }

        if(cantleft == false){
            if(e.getKeyCode() == 37){//left arrow
                p1.moveLeft();
                cantright = false;
                cantup = false;
                cantdown = false;
            }
        }

        if(cantup == false){
            if(e.getKeyCode() == 38){//up arrow
                p1.moveUp();
                cantright = false;
                cantdown = false;
                cantleft = false;
            }
        }
        if(cantdown == false){
            if(e.getKeyCode() == 40){//down arrow
                p1.moveDown();
                cantright = false;
                cantup = false;
                cantleft = false;
            }
        }

        if(e.getKeyCode() == 32){
            p2.setPosition(p1.getX(),p1.getY());
            p2.shoot();
            System.out.println("shoot1");
        }
            

        System.out.println("Player is at x:"+p1.getX()+", "+p1.getY());


        if(e.getKeyCode() == 39 || e.getKeyCode() == 40 || e.getKeyCode() == 37 || e.getKeyCode() == 38 ){
            
            GameMap key = new GameMap(p1.getX(), p1.getY());
            if( grid.containsKey(key) ){
                Item value = grid.get(key);
                if( value.getName().equals("coin")){
                    grid.remove(key);
                    coins++;
                }
                if(value.getName().equals("water")){
                    p1.setX(0);
                    p1.setY(0);
                }
            }
            GameMap treekey1 = new GameMap(p1.getX() + 14,p1.getY());
            GameMap treekey2 = new GameMap(p1.getX() - 14,p1.getY());
            GameMap treekey3 = new GameMap(p1.getX(),p1.getY() + 14);
            GameMap treekey4 = new GameMap(p1.getX(),p1.getY() - 14);

            if(grid.containsKey(treekey1)){
                Item value = grid.get(treekey1);
                if(value.getName().equals("tree")){
                    cantright = true;
                }
            }
            if(grid.containsKey(treekey2)){
                Item value = grid.get(treekey2);
                if(value.getName().equals("tree")){
                    cantleft = true;
                }
            }
            if(grid.containsKey(treekey3)){
                Item value = grid.get(treekey3);
                if(value.getName().equals("tree")){
                    cantdown = true;
                }
            }
            if(grid.containsKey(treekey4)){
                Item value = grid.get(treekey4);
                if(value.getName().equals("tree")){
                    cantup = true;
                }
            }


        }


    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void actionPerformed(ActionEvent e) {
    }

    public void animate(){
        
        while(true){
            //wait for .01 second
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            p2.moveRight();
            
            //System.out.println("repaint");
            
            //repaint the graphics drawn
            repaint();
        }
    }
    
    

}