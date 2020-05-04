package model;

import java.util.List;

public class IteratorQuestion {

    private int index;
    private final List<Question> list;

    public IteratorQuestion(List<Question> list) {
        this.list = list;
        index = 0;
    }

    public Question item() {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        else {
            return null;
        }
    }

    public void next() {
        index++;
    }

    public void previous() {
        index--;
    }

    public int lastIndex(){
        return index = list.size() -1;
    }

    public void setLastIndex() {
        index = lastIndex();
    }

    public void firstIndex() {
        index = -1;
    }

    public int getIndex() {
        return index;
    }

    public int size() {
        return list.size();
    }

    public void remove() {
        list.remove(index);
    }

    public boolean hasNext() {
        return index < list.size();
    }

    public boolean hasPrevious() {
        return index - 1 >= -1;
    }
}
