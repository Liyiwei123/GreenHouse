package user;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/4.
 */

public class Item implements Serializable {
    private int id;
    private int ItemInsided;
    private int itemInWet;
    private int ItemOut;
    private int Itemoutwet;
    private boolean judge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemInsided() {
        return ItemInsided;
    }

    public void setItemInsided(int itemInsided) {
        ItemInsided = itemInsided;
    }

    public int getItemInWet() {
        return itemInWet;
    }

    public void setItemInWet(int itemInWet) {
        this.itemInWet = itemInWet;
    }

    public int getItemOut() {
        return ItemOut;
    }

    public void setItemOut(int itemOut) {
        ItemOut = itemOut;
    }

    public int getItemoutwet() {
        return Itemoutwet;
    }

    public void setItemoutwet(int itemoutwet) {
        Itemoutwet = itemoutwet;
    }

    public boolean isJudge() {
        return judge;
    }

    public void setJudge(boolean judge) {
        this.judge = judge;
    }

    public Item(int itemInsided, int itemInWet, int itemOut, int itemoutwet) {
        this.ItemInsided = itemInsided;
        this.itemInWet = itemInWet;
        this.ItemOut = itemOut;

        this.Itemoutwet = itemoutwet;
    }
}
