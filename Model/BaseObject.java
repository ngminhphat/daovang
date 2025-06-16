/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Image;

/**
 *
 * @author Administrator
 */
public abstract class BaseObject {
    private int position_x,position_y;
    public boolean biKeo = false;
    public BaseObject(int posX, int posY){
        this.position_x=posX;
        this.position_y=posY;
}
    public int get_x() {
        return position_x;
    }
    public int get_y() {
        return position_y;
    }

    public void set_x(int position_x) {
        this.position_x = position_x;
    }

    public void set_y(int position_y) {
        this.position_y = position_y;
    }
    public abstract void draw(Image bufferedImage); 
}
