package org.advantiste.ffja.sud.gdc.mygdcapplication.controller;

import com.google.common.eventbus.EventBus;

public class EventManager {

    private static EventBus eventBus;
    private static EventManager eventManager=null;


    private EventManager (){
        this.eventBus = new EventBus();

    }

    public  EventBus getEventBus() {
        return eventBus;
    }


    public static EventManager getInstance(){
        if(eventManager==null){
            eventManager =  new EventManager();
        }
        return eventManager;
    }

}
