/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.vang;

/**
 *
 * @author Administrator
 */
import com.mycompany.dao.vang.Screen;
import java.util.Stack;

public class Game_Manager {
    private Stack<Screen> stackScreen;
    private static Game_Manager ourInstance = new Game_Manager();

    public static Game_Manager getInstance() {
        return ourInstance;
    }

    private Game_Manager() {
        stackScreen = new Stack<>();
    }

    public Stack<Screen> getStackScreen() {
        return stackScreen;
    }
}