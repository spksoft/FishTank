package ui;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by spksoft on 5/16/2017 AD.
 */


public class Tank extends JFrame {
    private String ip = null;
    private ArrayList<ui.Fish> listOfFish = null;
    private int id;
    private CustomPanel panel = null;
//    private models.Tanks data = null;
    private Function<ui.Fish, Integer> onFishPress = null;
    private Function<ui.Fish, Integer> onFishMove = null;
    private Function<CustomPanel, Integer> onTankPress = null;
    public Tank(int id, String ip) throws IOException {
        super("Tank ID : " + String.valueOf(id));
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.id = id;
        this.ip = ip;
        this.panel = new CustomPanel();
        this.listOfFish = new ArrayList<Fish>();
        panel.setLayout(null);
        panel.addMouseListener(panel);
        panel.setFocusable(true);
        getContentPane().add(panel);
    }
    public void addFish(int id, int x, int y, String way) throws IOException {
//        ui.Fish newFish = new ui.Fish(id, x, y, way);
////        ui.Fish f = new ui.Fish(id, x, y, way);
//        newFish.addMouseListener(newFish);
//        newFish.setOnFishClicked((ui.Fish ff) -> {
//            this.onFishPress.apply(ff);
//            return 0;
//        });
//        newFish.run();
//        listOfFish.add(newFish);
//        this.panel.add(newFish);
        ui.Fish f = new ui.Fish(id, x, y, way);
        f.addMouseListener(f);
        f.setOnFishClicked((ui.Fish ff) -> {
            this.onFishPress.apply(ff);
            return 0;
        });
        f.setOnFishMove((ui.Fish ff) -> {
            this.onFishMove.apply(ff);
            return 0;
        });
        f.run();
        listOfFish.add(f);
        this.panel.add(f);
    }
    public void removeFish(int id) {
        System.out.println("In Remove");
        for(ui.Fish f : this.listOfFish) {
            System.out.println("CHECK " + String.valueOf(f.getId()) + " " + String.valueOf(id));
            if(f.getId() == id) {
                f.stop();
                System.out.println("Stopped");
                this.panel.remove(f);
                this.panel.revalidate();
                this.panel.repaint();
            }
        }
    }
    public void removeFish(Fish f) {
        System.out.println("In Remove");
        this.panel.remove(f);
        this.panel.revalidate();
        this.panel.repaint();
    }
    public void addSampleFish() throws IOException {
        ui.Fish f = new ui.Fish(1, 200, 200, models.Fish.FISH_WAY_LEFT);
        f.addMouseListener(f);
        f.setOnFishClicked((ui.Fish ff) -> {
            this.onFishPress.apply(ff);
            return 0;
        });
        f.setOnFishMove((ui.Fish ff) -> {
            this.onFishMove.apply(ff);
           return 0;
        });
        f.run();
        this.panel.add(f);
        f = new ui.Fish(1, 200, 300, models.Fish.FISH_WAY_RIGHT);
        f.addMouseListener(f);
        f.setOnFishClicked((ui.Fish ff) -> {
            this.onFishPress.apply(ff);
            return 0;
        });
        f.setOnFishMove((ui.Fish ff) -> {
            this.onFishMove.apply(ff);
            return 0;
        });
        f.run();
        this.panel.add(f);
    }
    public void setOnFishPress(Function<ui.Fish, Integer> onFishPress) {
        this.onFishPress = onFishPress;
    }
    public void setOnFishMove(Function<ui.Fish, Integer> onFishMove) {
        this.onFishMove = onFishMove;
    }
    public void setOnTankPress(Function<CustomPanel, Integer> onTankPress) {
        this.onTankPress = onTankPress;
        this.panel.setOnPanelPress(this.onTankPress);
    }
//    public void render() throws IOException {
//        for (models.Tank t :  this.data.getTanks()) {
//            if(t.getIp().equals(this.ip) && t.getId() == this.id) {
//                panel.validate();
//                for(models.Fish f : t.getFishs()) {
//                    ui.Fish newFish = new ui.Fish(f.getId(), f.getLocation().get(0), f.getLocation().get(1), f.getWay());
//                    newFish.addMouseListener(newFish);
//                    newFish.setOnFishClicked((ui.Fish ff) -> {
//                        this.onFishPress.apply(ff);
//                        return 0;
//                    });
//                    newFish.setOnFishMove((ui.Fish ff) -> {
//                        this.onFishMove.apply(ff);
//                       return 0;
//                    });
//                    panel.add(newFish);
//                }
//            }
//        }
//    }
}
