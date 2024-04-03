package org.example.domain;

public class Client {
    private boolean isDirty = false;

    private int balance;

    public void setBalance(int balance){
        this.balance = balance;
    }


    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        System.out.println("instance context is dirty");
        isDirty = dirty;
    }
}
