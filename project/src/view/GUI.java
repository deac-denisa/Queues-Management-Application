package view;

import logic.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

//labels
    private final JLabel titleLabel = new JLabel("Queue management system");
    private final JLabel instructions = new JLabel("To start the simulation, please introduce the following parameters:");
    private final JLabel clientsLabel = new JLabel("Number of clients");
    private final JLabel queueLabel = new JLabel("Number of queues");
    private final JLabel simLabel = new JLabel("Simulation interval");
    private final JLabel minArrLabel = new JLabel("Minimum arrival time");
    private final JLabel maxArrLabel = new JLabel("Maximum arrival time");
    private final JLabel minServLabel = new JLabel("Minimum service time");
    private final JLabel maxServLabel = new JLabel("Maximum service time");
//text fields
    private final JTextField clientsField = new JTextField();
    private final JTextField queueField = new JTextField();
    private final JTextField simField = new JTextField();
    private final JTextField minArrField = new JTextField();
    private final JTextField maxArrField = new JTextField();
    private final JTextField minServField = new JTextField();
    private final JTextField maxServField = new JTextField();
//buttons
    private final JButton inputButton = new JButton("INTRODUCE DATA");
    private final JButton simButton = new JButton("START SIMULATION");


    public GUI() {
        initGUI();
        addHeaderLabels();
        addNrClients();
        addNrQueue();
        addSimulationParameters();
        addArrivalParameters();
        addServiceParameters();
        addButtons();

    }
    public void addSimulationListener(ActionListener m){
        simButton.addActionListener(m);
    }
    public void addInputListener(ActionListener m) {
        inputButton.addActionListener(m);
    }



    private void initGUI(){
        this.setSize(500, 600);
        this.setTitle("Queue management system");
        this.setLayout(null);
        this.setLocation(300,100);
        this.getContentPane().setBackground( new Color(238,221,222));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addHeaderLabels(){
        //title
        titleLabel.setBounds(80,20,300,50);
        titleLabel.setVisible(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        this.add(titleLabel);

        //instruction
        instructions.setBounds(20,100,450,30);
        instructions.setVisible(true);
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        instructions.setFont(new Font("Serif", Font.ITALIC, 13));
        this.add(instructions);

    }

    private void addNrClients(){
        //label
        clientsLabel.setBounds(100,150,130,30);
        clientsLabel.setVisible(true);
        clientsLabel.setForeground(new Color(86,56,60));
        this.add(clientsLabel);
        //field
        clientsField.setBounds(300,150,30,30);
        clientsField.setBackground(new Color(123,81,86));
        clientsField.setForeground(new Color(255, 255, 255));
        clientsField.setVisible(true);
        this.add(clientsField);
    }

    private void addNrQueue(){
        //label
        queueLabel.setBounds(100,190,130,30);
        queueLabel.setVisible(true);
        queueLabel.setForeground(new Color(86,56,60));
        this.add(queueLabel);
        //field
        queueField.setBounds(300,190,30,30);
        queueField.setBackground(new Color(123,81,86));
        queueField.setForeground(new Color(255, 255, 255));
        queueField.setVisible(true);
        this.add(queueField);
    }

    private void addSimulationParameters(){
        //label
        simLabel.setBounds(100,230,130,30);
        simLabel.setForeground(new Color(86,56,60));
        simLabel.setVisible(true);
        this.add(simLabel);
        //field
        simField.setBounds(300,230,30,30);
        simField.setBackground(new Color(123,81,86));
        simField.setForeground(new Color(255, 255, 255));
        simField.setVisible(true);
        this.add(simField);
    }

    private void addArrivalParameters(){
        //labels
        minArrLabel.setBounds(40,270,130,30);
        minArrLabel.setForeground(new Color(86,56,60));
        minArrLabel.setVisible(true);
        this.add(minArrLabel);

        maxArrLabel.setBounds(260,270,130,30);
        maxArrLabel.setForeground(new Color(86,56,60));
        maxArrLabel.setVisible(true);
        this.add(maxArrLabel);

        //field
        minArrField.setBounds(180,270,30,30);
        minArrField.setBackground(new Color(123,81,86));
        minArrField.setForeground(new Color(255, 255, 255));
        minArrField.setVisible(true);
        this.add(minArrField);

        maxArrField.setBounds(400,270,30,30);
        maxArrField.setBackground(new Color(123,81,86));
        maxArrField.setForeground(new Color(255, 255, 255));
        maxArrField.setVisible(true);
        this.add(maxArrField);
    }

    private void addServiceParameters(){
        //labels
        minServLabel.setBounds(40,310,130,30);
        minServLabel.setForeground(new Color(86,56,60));
        minServLabel.setVisible(true);
        this.add(minServLabel);

        maxServLabel.setBounds(260,310,130,30);
        maxServLabel.setForeground(new Color(86,56,60));
        maxServLabel.setVisible(true);
        this.add(maxServLabel);

        //field
        minServField.setBounds(180,310,30,30);
        minServField.setBackground(new Color(123,81,86));
        minServField.setForeground(new Color(255, 255, 255));
        minServField.setVisible(true);
        this.add(minServField);

        maxServField.setBounds(400,310,30,30);
        maxServField.setBackground(new Color(123,81,86));
        maxServField.setForeground(new Color(255, 255, 255));
        maxServField.setVisible(true);
        this.add(maxServField);
    }

    private void addButtons(){
        //introduce data
        inputButton.setBounds(160,380,160,40);
        inputButton.setBackground(new Color(123,81,86));
        inputButton.setForeground(new Color(255, 255, 255));
        inputButton.setFocusable(true);
        inputButton.setVisible(true);
        this.add(inputButton);
        //simulation
        simButton.setBounds(160,460,160,40);
        simButton.setBackground(new Color(123,81,86));
        simButton.setForeground(new Color(255, 255, 255));
        simButton.setFocusable(true);
        simButton.setVisible(true);
        this.add(simButton);
    }

    public int getNrClients(){
        int nr = Integer.parseInt(clientsField.getText());
        //System.out.println(nr);
        return nr;
    }

    public int getNrQueues(){
        int nr = Integer.parseInt(queueField.getText());
       // System.out.println(nr);
        return nr;
    }

    public int getSimTime(){
        int nr = Integer.parseInt(simField.getText());
        return nr;
    }

    public int getNrMinArr(){
        int nr = Integer.parseInt(minArrField.getText());
        return nr;
    }

    public int getNrMaxArr(){
        int nr = Integer.parseInt(maxArrField.getText());
        return nr;
    }

    public int getNrMinServ(){
        int nr = Integer.parseInt(minServField.getText());
        return nr;
    }

    public int getNrMaxServ(){
        int nr = Integer.parseInt(maxServField.getText());
        return nr;
    }

    public JButton getInputButton(){ return inputButton;}
    public JButton getSimButton(){ return simButton;}

}


