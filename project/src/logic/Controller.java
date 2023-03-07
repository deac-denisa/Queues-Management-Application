package logic;

import logic.SimulationManager;
import view.GUI;
import view.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    GUI view;
    public Controller(GUI view){
        this.view = view;
        view.addInputListener(e->{
                if(e.getSource() == view.getInputButton()) {
                    //read values from the view
                    int timeLimit = view.getSimTime();
                    int  nrQueues = view.getNrQueues();
                    int  nrClients = view.getNrClients();
                    int minArr = view.getNrMinArr();
                    int  maxArr = view.getNrMaxArr();
                    int  minService = view.getNrMinServ();
                    int  maxService = view.getNrMaxServ();

                    if (maxArr < minArr) {
                        JOptionPane.showMessageDialog(null, "Incorrect 'Arrival Time' parameters", "Invalid Parameters", JOptionPane.ERROR_MESSAGE);
                    }
                    if (maxService < minService) {
                        JOptionPane.showMessageDialog(null, "Incorrect 'Service Time' parameters", "Invalid Parameters", JOptionPane.ERROR_MESSAGE);
                    }
                    if (maxArr > timeLimit) {
                        JOptionPane.showMessageDialog(null, "Incorrect parameters. Arrival time cannot be greater than the maximum simulation time", "Invalid Parameters", JOptionPane.ERROR_MESSAGE);
                    }
                    if (maxService > timeLimit) {
                        JOptionPane.showMessageDialog(null, "Incorrect parameters. Service time cannot be greater than the maximum simulation time", "Invalid Parameters", JOptionPane.ERROR_MESSAGE);
                    }
                }

        });


       view.addSimulationListener(e->{
                if( e.getSource() == view.getSimButton()){
                    int timeLimit = view.getSimTime();
                    int  nrQueues = view.getNrQueues();
                    int  nrClients = view.getNrClients();
                    int minArr = view.getNrMinArr();
                    int  maxArr = view.getNrMaxArr();
                    int  minService = view.getNrMinServ();
                    int  maxService = view.getNrMaxServ();
                    Log log = new Log();
                    log.setVisible(true);

                    SimulationManager sim = new SimulationManager(maxArr, minArr, maxService, minService, nrClients, nrQueues, timeLimit);
                    Thread t = new Thread(sim);
                    t.start();
                }
       });

    }
}
