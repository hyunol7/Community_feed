package org.example.Common;

public class PositivelntegerCounter {
    private int count;

    public PositivelntegerCounter(){
        this.count = 0;
    }

    public PositivelntegerCounter(int count){
        this.count = count;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if(count <= 0){
            return;
        }
        this.count--;
    }

    public int getCount(){
        return count;
    }
}

