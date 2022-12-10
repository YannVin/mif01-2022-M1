package fr.univ_lyon1.info.m1.mes.Observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    //@Override
    public void register(final Observer observer) {
        observers.add(observer);
    }

    //@Override
    public void notifyObservers() {
        List<Observer> observerslist = new ArrayList<Observer>(observers);
        for (Observer observer : observerslist) {
            observer.update();
        }
    }
    
}
