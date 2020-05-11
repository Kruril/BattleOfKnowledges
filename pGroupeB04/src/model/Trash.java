package model;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Pack {

    private List<Question> questions;

    public Trash() {
        questions = new ArrayList<>();
    }

    @Override
    public List<Question> getList() {
        return questions;
    }

    @Override
    public boolean addQuestion(Question questionAdd) {
        if (questions.contains(questionAdd) || !questionAdd.checkQuestion()) return false;
        questions.add(questionAdd);
        return true;
    }

    @Override
    public boolean removeQuestion(Question questionRemove) {
        if (!questions.contains(questionRemove)) return false;
        questions.remove(questionRemove);
        return true;
    }

    @Override
    public IteratorQuestion createIterator(){
        return new IteratorQuestion(questions);
    }

    @Override
    public boolean isEmpty() {
        if (questions == null) return true;
        return questions.size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder tpm = new StringBuilder();
        for (Question q : questions) {
            tpm.append(q.toString()).append("\n");
        }
        return tpm.toString();
    }
}
