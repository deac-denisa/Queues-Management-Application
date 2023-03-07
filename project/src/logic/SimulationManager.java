package logic;

import model.Server;
import model.Task;
import view.GUI;
import view.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingDeque;

public class SimulationManager implements Runnable{
    private GUI view;
    private static Scheduler scheduler;
    private static List<Task> clients = new ArrayList<>();
    private static int timeLimit;
    private static int minArr;
    private static int maxArr;
    private static int minService;
    private static int maxService;
    private static int nrQueues;
    private static int nrClients;

    public SimulationManager(int maxArr, int minArr, int maxService, int minService, int nrClients, int nrQueues, int timeLimit) {
        this.maxArr= maxArr;
        this.minArr= minArr;
        this.maxService = maxService;
        this.minService = minService;
        this.nrQueues = nrQueues;
        this.nrClients = nrClients;
        this.timeLimit = timeLimit;

        scheduler = new Scheduler(nrQueues);
        clientsGenerator();

    }

    public void clientsGenerator(){

        //generate n clients with random arrival and service time
        for( int id = 1; id <= nrClients; id++){
            Task client = new Task(id, maxArr, minArr, maxService, minService);
            clients.add(client);
        }

        //sort the clients based on their arrivalTime
        Collections.sort(clients, new Comparator<Task>(){
            @Override
            public int compare(Task o1, Task o2) {
                if(o1.getArrivalTime() < o2.getArrivalTime()){
                    return -1;
                }
                else if( o1.getArrivalTime() == o2.getArrivalTime()){
                    return 0;
                }
                else{
                    return 1;
                }
            }
        });

    }

    private static void writeLog(String s){

        try {
            FileWriter file = new FileWriter("output.txt");
            file.write(s);

            file.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private float avarageServiceTime(){
        int sum = 0;
        for(Task t:clients){
            sum = sum + t.getServiceTime();
        }

        return (float) (sum*(1.0)/nrClients);
    }


    @Override
    public void run() {
        int currentTime = 0;
        String s="";
        String s1="";
        int maxPeakHour = 0;
        int taskPeakHour = 0;
        int timePeakHour = 0;

        float avarageServiceTime = avarageServiceTime();

        while( currentTime <= timeLimit ){

            s1 ="\n.........................................Time: "+currentTime+"...........................................\n"+"Clients: "+ clients.toString()+"\n";
            s = s+ s1;

            System.out.println("\n.............Time: "+currentTime+"................\n");
            System.out.println("Clients: "+clients.toString()+"\n");

            for(int i = 0; i < nrClients; i ++){
                if(clients.size() > 0) {
                    Task t = clients.get(i);
                    if (t.getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(t);
                        clients.remove(t);
                        i--;
                    }
                    if(t.getArrivalTime() != currentTime){
                        break;
                    }
                }
            }

        //calculate peak hour
            taskPeakHour = 0;
            for(Server server: Scheduler.getServers()){
                taskPeakHour = taskPeakHour + server.getTasks().size();
            }
                if( taskPeakHour > maxPeakHour){
                    maxPeakHour = taskPeakHour;
                    timePeakHour = currentTime;
                }

            for(int i = 0; i < nrQueues; i ++){
                System.out.println("\nQueue " + (i+1) + " : ");
                BlockingDeque<Task> t =  Scheduler.getServers().get(i).getTasks();

                System.out.println(Scheduler.getServers().get(i).getTasks().toString());

                s1 = s1+ "\nQueue " + (i+1) + " : "+ t.toString()+"\n" ;
                s = s+ s1;
            }
            Log.setTextArea(s1);

            for(int i = 0; i < nrQueues; i ++) {
                Task task = Scheduler.getServers().get(i).getTasks().peek();
                if ( task != null) {
                    int t = task.getServiceTime();
                    if (t == 0) {
                        Scheduler.getServers().get(i).getTasks().removeFirst();
                    } else
                        task.setServiceTime(t - 1);
                        Scheduler.getServers().get(i).setWaitingPeriod(  );
                }

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentTime++;

        }
    //compute avarage waiting time, avarage service time and peak hour
        float avarageWaitingTime = Scheduler.avarageWaitingTime(nrQueues);
        System.out.println("\nAvarage waiting time for a client: "+ avarageWaitingTime);
        System.out.println("Avarage service time for a client: "+ avarageServiceTime+ "\nPeak hour: "+timePeakHour);

        s = s + "\nAvarage waiting time for a client: "+ avarageWaitingTime+"\n"+"Avarage service time for a client: "+ avarageServiceTime+"\nPeak hour: "+timePeakHour;
        Log.setTextArea("\nAvarage waiting time for a client: "+ avarageWaitingTime+"\n"+"Avarage service time for a client: "+ avarageServiceTime+"\nPeak hour: "+timePeakHour);

        writeLog(s);

    }
}
