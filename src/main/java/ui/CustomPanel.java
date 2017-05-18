package ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Function;

/**
 * Created by spksoft on 5/17/2017 AD.
 */
public class CustomPanel extends JPanel implements MouseListener {
    private int lastX = -1;
    private int lastY = -1;
    private Function<CustomPanel, Integer> onPanelPress = null;
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {
            this.lastX = e.getX();
            this.lastY = e.getY();
            onPanelPress.apply(this);
            System.out.println("Mouse Left was clicked! from panel");
        } else if(SwingUtilities.isRightMouseButton(e)) {
            System.out.println("Mouse Right was clicked!");
        } else {
            System.out.println("Unknown !");
        }
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setOnPanelPress(Function<CustomPanel, Integer> onPanelPress) {
        this.onPanelPress = onPanelPress;
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