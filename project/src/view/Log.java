package view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class Log extends JFrame {

    private static JTextArea result = new JTextArea();
    private static JLabel title = new JLabel("Simulation reuslt");
    private static JScrollPane scrollBar;

    public Log(){
        initLog();
        addTitle();
        addTextArea();
    }

    private void addTitle(){
        title.setBounds(80,20,300,50);
        title.setVisible(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        this.add(title);
    }
    private void initLog(){
        this.setSize(500, 600);
        this.setTitle("Queue management system");
        this.setLayout(null);
        this.setLocation(800, 100);
        this.getContentPane().setBackground( new Color(238,221,222));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addTextArea(){
        result.setBounds(30, 90, 420, 440);
        result.setEditable(false);
        result.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Result"), result.getBorder()));
        result.setBackground(new Color(123,81,86));
        result.setForeground(new Color(255, 255, 255));
        result.setFont(new Font("Times New Roman", Font.BOLD , 15));

        scrollBar = new JScrollPane(result);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(30, 100, 420, 444);
        getContentPane().add(scrollBar);
        setVisible(true);
    }

    public static void setTextArea(String s){
        result.append(s);
    }
}
