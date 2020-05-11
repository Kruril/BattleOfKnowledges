package model;


import java.util.List;

public interface Pack {

    /**
     * returns a list contained in the class that
     * will implement this interface
     * @return a list of questions
     */
    List<Question> getList();

    /**
     * add a question to a list
     * @param questionAdd questions that we will add
     *                    to the list
     * @return true if it added or false if not
     */
    boolean addQuestion(Question questionAdd);

    /**
     * remove a question from a list
     * @param questionRemove question that we will
     *                       remove from the list
     * @return true if it removed or false if not
     */
    boolean removeQuestion(Question questionRemove);

    /**
     * create a iterator from a list of questions
     * @return the itertor of questions
     */
    IteratorQuestion createIterator();

    /**
     * Check if list is empty return true if list empty and false if list contain questions
     * @return boolean true (list is empty) or false (list contain questions)
     */
    boolean isEmpty();
}
