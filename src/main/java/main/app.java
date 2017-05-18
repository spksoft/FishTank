package main;


import com.google.gson.Gson;
import models.Command;
import models.Fish;
import models.FishAction;
import ui.CustomPanel;
import ui.Tank;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by spksoft on 5/16/2017 AD.
 */
public class app {
    static  Tank tank;
    static final int PORT = 8080;
    static  int counting = 0;
    static  Socket s;
    static Command command = null;

    public static String readData(Socket i) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(i.getInputStream()));
        return in.readLine();
    }
    private static void sendData(Socket o, String data) throws IOException {
        PrintWriter out = new PrintWriter(o.getOutputStream(), true);
        out.println(data);
    }

    public static void main(String args[]) throws IOException {

        try {
            ServerSocket ss = new ServerSocket(PORT);
            s = ss.accept();//establishes connection
            tank = new Tank(0, "localhost");
            tank.setVisible(true);

        } catch(Exception e){
            s = new Socket("127.0.0.1",PORT);
            tank = new Tank(1, "localhost1");
            tank.setVisible(true);
        }

        tank.setOnFishPress((ui.Fish f) -> {
            tank.removeFish(f.getId());
            return 0;
        });
        tank.setOnFishMove((ui.Fish f) -> {
            System.out.println("Move " + f.getX() + " " + f.getY());
            if(f.getWay().equals(Fish.FISH_WAY_LEFT)) {
                if(f.getX() < - 130) {
                    Command command = new Command();
                    FishAction option = new FishAction();
                    option.setWay(Fish.FISH_WAY_LEFT);
                    option.setCurrentTank(0);
                    option.setId(f.getId());
                    option.setX(f.getX());
                    option.setY(f.getY());
                    command.setCommand("MOVE");
                    command.setFishAction(option);
                    Gson g = new Gson();
                    try {
                        sendData(s, g.toJson(command));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tank.removeFish(f.getId());
                }
            } else {
                if(f.getX() > tank.getSize().getWidth() + 100) {
                    Command command = new Command();
                    FishAction option = new FishAction();
                    option.setWay(Fish.FISH_WAY_RIGHT);
                    option.setCurrentTank(0);
                    option.setId(f.getId());
                    option.setX(f.getX());
                    option.setY(f.getY());
                    command.setCommand("MOVE");
                    command.setFishAction(option);
                    Gson g = new Gson();
                    try {
                        sendData(s, g.toJson(command));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tank.removeFish(f.getId());
                }
            }
            return 0;
        });
        tank.setOnTankPress((CustomPanel p) -> {
            String way = null;
            Random rand = new Random();
            int  n = rand.nextInt(20);
            if(n % 2 == 0) {
                way = Fish.FISH_WAY_LEFT;
            } else {
                way = Fish.FISH_WAY_RIGHT;
            }
            try {
                tank.addFish(++counting, p.getLastX(), p.getLastY(), way);
                counting++;
                Command command = new Command();
                command.setCommand("ADD");
                Gson g = new Gson();
                try {
                    sendData(s, g.toJson(command));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        });
        while (true) {
            String rawData = readData(s);
            Command command = new Gson().fromJson(rawData, Command.class);
            if(command.getCommand().equals("MOVE")) {
                tank.removeFish(command.getFishAction().getId());
                if(command.getFishAction().getWay().equals(Fish.FISH_WAY_RIGHT)) {
                    tank.addFish(command.getFishAction().getId(), -100, command.getFishAction().getY(), Fish.FISH_WAY_RIGHT);
                } else {
                    tank.addFish(command.getFishAction().getId(), tank.getSize().width + 20, command.getFishAction().getY(), Fish.FISH_WAY_LEFT);
                }
            } else if(command.getCommand().equals("ADD")) {
                counting++;
            }
        }

    }
}
