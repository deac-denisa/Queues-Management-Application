package model;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingDeque<Task> tasks;
    private AtomicInteger waitingPeriod;

//constructor
    public Server() {
        this.tasks = new LinkedBlockingDeque();
        this.waitingPeriod = new AtomicInteger();
    }

//getters and setters
    public BlockingDeque<Task> getTasks() {return tasks;}

    public int getWaitingPeriod() {
        return waitingPeriod.intValue();
    }

    public void setWaitingPeriod() {
        this.waitingPeriod.addAndGet(-1);
    }

    //my methods
    public void addTask(Task t){
        this.tasks.add(t);
        this.waitingPeriod.addAndGet(t.getServiceTime());
    }

    @Override
    public void run() {

        while(true){
            if(!tasks.isEmpty()){
                Task t  = tasks.peek();
                if(t != null){
                    try {
                        Thread.sleep(t.getServiceTime()*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
