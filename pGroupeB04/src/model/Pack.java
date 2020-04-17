package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Pack {

    protected List<Question> questions;


    public Pack() {
        questions = new ArrayList<>();
    }

    public List<Question> getList() {
        return new ArrayList<>(questions);
    }

    public boolean addQuestion(Question questionAdd) {
        if (questions.contains(questionAdd) || !questionAdd.checkQuestion()) return false;
        questions.add(questionAdd);
        return true;
    }

    public boolean removeQuestion(Question questionRemove) {
        if (!questions.contains(questionRemove)) return false;
        questions.remove(questionRemove);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder tpm = new StringBuilder();
        for (Question q : questions) {
            tpm.append(q.toString()).append("\n");
        }
        return tpm.toString();
    }

    public IteratorQuestion createIterator(){
        return new IteratorQuestion(questions);
    }

    public boolean isEmpty() {
        if (questions == null) return true;
        return questions.size() == 0;
    }
}
