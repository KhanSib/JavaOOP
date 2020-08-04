package ru.academits.khanov.temparature.main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("My first frame");
                frame.setSize(300, 200); // размер окна
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true); // показать фрейм

                JButton b = new JButton("Button");
                frame.add(b);
                // Зарегистрировать прослушиватель
               // b.addActionListener();
            }
        });
    }
}
