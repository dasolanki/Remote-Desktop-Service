/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.Start;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author sniper
 */
public class Main {

    JFrame jframe;
    JPanel jpanel;
    JButton clientButton;
    JToggleButton jserverButton;
    boolean Jsv = false, expire = false;

    Thread t;

    public static void main(String[] args) {
        gui gui = new gui();
        Thread t = new Thread(gui);
        t.start();
        //gui.expireSession(t);
    }

}

class gui extends Main implements Runnable {

    public void run() {
        System.out.println("main run");
        JFrame jframe = new JFrame();
        JPanel jpanel = new JPanel();
        //JButton serverButton = new JButton();
        JButton clientButton = new JButton("Create Client");
        JToggleButton jserverButton = new JToggleButton("Create Server", Jsv);
        jpanel.add(jserverButton);
        jpanel.add(clientButton);
        jframe.add(jpanel);
        jframe.setSize(300, 100);
        jframe.setVisible(true);
        jserverButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (jserverButton.isEnabled()) {
                    System.out.println("Button CLick");
                    SetPassword set = new SetPassword();
                    expire = true;
                } else if (!jserverButton.isEnabled()) {
                    try {
                        close cl = new close();
                        expire = true;
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Server closed the connection", ex);
                    }
                }
            }
        });
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Start st = new Start();
                Thread t2 = new Thread(st);
                t2.run();
            }
        });
    }

    public void expireSession(Thread t1) {
        System.out.println("Expire Session");
        this.t = t1;
        if (expire == true) {
            t.stop();
        }
    }

}
