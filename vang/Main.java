/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.vang;



/**
 *
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        game_window window = new game_window();
        Thread thread = new Thread(window);
        thread.start();
    }
}
