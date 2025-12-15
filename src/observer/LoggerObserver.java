/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

public class LoggerObserver implements Observer {

    @Override
    public void update(String message) {
        System.out.println("[LOG] " + message);
    }
}

