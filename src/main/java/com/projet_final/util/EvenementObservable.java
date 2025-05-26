package com.projet_final.util;

import java.util.ArrayList;
import java.util.List;

public abstract class EvenementObservable {
    private List<ParticipantObserver> participantObservers = new ArrayList<>();

    public void addObserver(ParticipantObserver participant){
        participantObservers.add(participant);
    }

    public void deleteObserver(ParticipantObserver participant){
        participantObservers.remove(participant);
    }

    public void notifyObserver(String message){
        for (ParticipantObserver participant : participantObservers){
            participant.update(message);
        }
    }
}
