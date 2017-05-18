package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

/**
 * Created by spksoft on 5/17/2017 AD.
 */
public class Fish extends JLabel implements MouseListener {
    private int id;

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    private String way = null;
    private Function<Fish, Integer> onFishClicked = null;
    private Function<Fish, Integer> onFishMove = null;
    private boolean running = true;
    private Thread thread = null;
    public Fish(int id, int x, int y, String way) throws IOException {

        BufferedImage dimg = null;
        if(way.equals(models.Fish.FISH_WAY_LEFT)) {
//            System.out.println(System.getProperty("user.dir"));
            dimg = ImageIO.read(new File("src/main/resources/fishLeft.png"));
        } else {
            dimg = ImageIO.read(new File("src/main/resources/fishRight.png"));
        }
        BufferedImage img = toBufferedImage(dimg.getScaledInstance(150, 100, Image.SCALE_SMOOTH));
        ImageIcon icon = new ImageIcon(img);
        this.setIcon(icon);
        this.setSize(150, 100);
        this.setLocation(x, y);
        this.id = id;
        this.way = way;
        this.thread = new Thread(() -> {
            while(this.running) {
                if(this.way.equals(models.Fish.FISH_WAY_LEFT)) {
                    int newX = this.getX() - 3;
                    this.setLocation(newX, this.getY());
                    this.onFishMove.apply(this);
                } else {
                    int newX = this.getX() + 3;
                    this.setLocation(newX, this.getY());
                    this.onFishMove.apply(this);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void run() {
        this.running = true;
        this.thread.start();
    }
    public void stop() {
        this.running = false;
        this.thread.stop();
    }
    public Integer setOnFishClicked(Function<Fish, Integer> onFishClicked) {
        this.onFishClicked = onFishClicked;
        return 0;
    }
    public Integer setOnFishMove(Function<Fish, Integer> onFishMove) {
        this.onFishMove = onFishMove;
        return 0;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {

        } else if(SwingUtilities.isRightMouseButton(e)) {
            onFishClicked.apply(this);
        } else {
            System.out.println("Unknown !");
        }
    }
    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
}