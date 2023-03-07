package model;

public class Task {
    private int id;
    private int arrivalTime;
    private int serviceTime;

    public Task(int id, int aMax, int aMin, int sMax, int sMin) {
        //the constructor automatically generates random values for arrival and service time in the given interval
        this.id = id;
        this.arrivalTime =  ( (int)Math.floor(Math.random() * (aMax - aMin + 1) + aMin) );
        this.serviceTime =( (int)Math.floor(Math.random() * (sMax - sMin + 1) + sMin) );
    }


    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "(" + id +
                " " + arrivalTime +
                " " + serviceTime +
                ") ";
    }
}
