package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FishAction {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("currentTank")
    @Expose
    private Integer currentTank;
    @SerializedName("way")
    @Expose
    private String way;
    @SerializedName("x")
    @Expose
    private Integer x;
    @SerializedName("y")
    @Expose
    private Integer y;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurrentTank() {
        return currentTank;
    }

    public void setCurrentTank(Integer currentTank) {
        this.currentTank = currentTank;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

}