package model;


import java.util.List;

public interface Pack {

    List<Question> getList();

    boolean addQuestion(Question questionAdd);

    boolean removeQuestion(Question questionRemove);

    IteratorQuestion createIterator();

    /**
     * Check if list is empty return true if list empty and false if list contain questions
     * @return boolean true (list is empty) or false (list contain questions)
     */
    boolean isEmpty();
}
