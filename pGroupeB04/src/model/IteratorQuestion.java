package model;

import java.util.List;

public class IteratorQuestion {

    private int index;
    private List<Question> list;

    public IteratorQuestion(List<Question> list) {
        this.list = list;
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

    public void preview() {
        index--;
    }

    public int lastIndex(){
        return index = list.size() -1;
    }

    public int firstIndex() {
        return index = -1;
    }

    public int getIndex() {
        return index;
    }

    public int getNbIndex() {
        return list.size();
    }

    public void remove() {
        list.remove(index);
    }

    public boolean hasNext() {
        return index < list.size() -1;
    }
}
