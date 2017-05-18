package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Command {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("command")
    @Expose
    private String command;
    @SerializedName("FishAction")
    @Expose
    private FishAction fishAction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public FishAction getFishAction() {
        return fishAction;
    }

    public void setFishAction(FishAction fishAction) {
        this.fishAction = fishAction;
    }

}