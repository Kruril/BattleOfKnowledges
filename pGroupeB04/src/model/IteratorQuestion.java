package model;

import java.util.List;

public class IteratorQuestion {

    private int index;
    private final List<Question> list;

    public IteratorQuestion(List<Question> list) {
        this.list = list;
        index = 0;
    }

    /**
     * Give the question we are on when the method is called
     * @return Returns the question if we are on a question
     * otherwise returns null
     */
    public Question item() {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        else {
            return null;
        }
    }

    /**
     * go to the next item in the list of questions
     */
    public void next() {
        index++;
    }

    /**
     * go to the previous item in the list of questions
     */
    public void previous() {
        index--;
    }

    /**
     * will bring us to the last item in the list
     * @return Returns the index of the last question
     */
    public int lastIndex(){
        return index = list.size() -1;
    }

    /**
     * Will pass the index to the last item in the list of
     * questions
     */
    public void setLastIndex() {
        index = lastIndex();
    }

    /**
     * Brings the list back to the element just before the first
     * one after this method you will have to call the method
     * next()
     */
    public void firstIndex() {
        index = -1;
    }

    /**
     * tells us what index we are on
     * @return Returns the index of the question where we are
     */
    public int getIndex() {
        return index;
    }

    public int size() {
        return list.size();
    }

    /**
     * delete the questions we are currently in
     */
    public void remove() {
        list.remove(index);
    }

    /**
     * Check if there is a next item in the question list
     * @return Returns true if it has a next question
     * otherwise false
     */
    public boolean hasNext() {
        return index < list.size() - 1;
    }

    /**
     * Check if there is a previous item in the question list
     * @return Returns true if it has a previous question
     * otherwise false
     */
    public boolean hasPrevious() {
        return index - 1 >= -1;
    }
}
