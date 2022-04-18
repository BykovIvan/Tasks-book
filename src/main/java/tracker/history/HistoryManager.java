package main.java.tracker.history;

import java.util.List;

public interface HistoryManager<T> {
    /**
     * Метод добавление задачи, эпика, подзадачи в список
     *
     * @param task
     */
    void add(T task);

    /**
     * Метод получения листа истории (которая получается по методам getTask/Epic/Subtask(id)
     *
     * @return
     */
    List<T> getHistory();

    /**
     * Метод удаления задачи из списка истории (используется когда удаляем задачу)
     *
     * @param Id
     */
    void remove(int Id);
}
