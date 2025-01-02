package org.example.Common;

public class PostisivelntengerCounter {
    private int count;

    public PostisivelntengerCounter(){
        this.count = 0;
    }
    public void increase() {
        this.count++;
    }

    public void descrease() {
        if(count <= 0){
            return;
        }
        this.count--;
    }

    public int getCount(){
        return count;
    }
}

