package logic;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private  int nrQueues;
    private static List<Server> servers = new ArrayList<>();
    private static int totalWaitingTime = 0;

    public Scheduler( int nrQueues) {
        this.nrQueues = nrQueues;
        for(int i=1; i<= nrQueues; i++){
            Server s = new Server();
            Thread thread = new Thread(s);
            servers.add(s);
            thread.start();
        }
    }

     public void dispatchTask( Task t){

        int minWait = Integer.MAX_VALUE;
        int pos = 0;

        for(Server s: servers){
            if(minWait > s.getWaitingPeriod()){
                pos = servers.indexOf(s);
                minWait = s.getWaitingPeriod();
            }
        }

    //calculate total waiting time
    // => each client has waiting time = queueWaitingTime + his service time
        totalWaitingTime = totalWaitingTime + minWait + t.getServiceTime();
         servers.get(pos).addTask(t);
    }

    public static float avarageWaitingTime(int nrQueues){
        return (float) (totalWaitingTime*(1.0)/nrQueues);
    }
    public static List<Server> getServers() {
        return servers;
    }
}
