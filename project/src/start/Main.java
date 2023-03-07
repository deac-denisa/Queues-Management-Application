package start;

import logic.Controller;
import logic.SimulationManager;
import view.GUI;
import view.Log;

public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
        Controller c = new Controller(gui);

    }
}
