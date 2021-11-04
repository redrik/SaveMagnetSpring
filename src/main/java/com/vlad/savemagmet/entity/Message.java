package com.vlad.savemagmet.entity;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private String message = "";
    private List<Magnet> magnets = new ArrayList<Magnet>();
    private String error = "";

    public Message(String message, List<Magnet> magnets, String error) {
        this.message = message;
        this.magnets = magnets;
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Magnet> getMagnets() {
        return this.magnets;
    }

    public void setMagnets(ArrayList<Magnet> magnets) {
        this.magnets = magnets;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }
}