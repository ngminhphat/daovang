/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Administrator
 */
import Controller.Screen;
import java.util.Stack;

public class Game_Manager {
    private static Game_Manager instance;
    private Stack<Screen> stackScreen;
    private static int currentLevel = 1;

    public static int playerScore = 0; // Bắt đầu với 0 điểm
    public static int playerTnt = 1;   // Bắt đầu với 1 TNT

    public static Game_Manager getInstance() {
        if (instance == null) {
            instance = new Game_Manager();
        }
        return instance;
    }

    private Game_Manager() {
        stackScreen = new Stack<>();
    }

    public Stack<Screen> getStackScreen() {
        return stackScreen;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(int level) {
        currentLevel = level;
    }
}