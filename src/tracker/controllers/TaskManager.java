package tracker.controllers;

import tracker.model.Epic;
import tracker.model.Subtask;
import tracker.model.Task;

import java.util.ArrayList;

public interface TaskManager {
    /**
     * Удаление всех подзадач
     */
    void deleteAllSubtasks();

    /**
     * Получение списка подзадач
     *
     * @return
     */
    ArrayList<Subtask> getSubtasks();

    /**
     * Получение подзадачи по id
     * по этому методу есть добавление в список истории
     *
     * @param id
     * @return
     */
    Subtask getSubtask(int id);

    /**
     * Создание новой задачи
     *
     * @param subtask
     */
    void createNewSubTask(Subtask subtask);

    /**
     * обновление подзадачи(Замена существующей)
     *
     * @param subtask
     */
    void updateSubtask(Subtask subtask);

    /**
     * Удаление подзадачи по ее ID
     *
     * @param id
     */
    void deleteSubtask(int id);

    /**
     * Удаление всех эпиков и их подзадач
     */
    void deleteAllEpics();

    /**
     * Получание списка эпиков и их подзадач
     *
     * @return
     */
    ArrayList<Epic> getEpics();

    /**
     * Получение эпика и его подзадач по ID
     * по этому методу есть добавление в список истории
     *
     * @param id
     * @return
     */
    Epic getEpic(int id);

    /**
     * Создание нового эпика
     *
     * @param epic
     */
    void createNewEpic(Epic epic);

    /**
     * Обновление эпика (Замена существующего)
     *
     * @param epic
     */
    void updateEpic(Epic epic);

    /**
     * Удаление эпика и его подзадач по ID эпика
     *
     * @param id
     */
    void deleteEpic(int id);

    /**
     * Метод автомачиского выставления статуса исходя из его статуса его подзадач
     */
    void updateStatusEpic();

    /**
     * Получение только списка подзадач по ID эпика
     *
     * @param idEpic
     * @return
     */
    ArrayList<Subtask> getSubtasksByEpicId(int idEpic);

    /**
     * Удаление всех обычных задач
     */
    void deleteAllTasks();

    /**
     * Получение списка всех задач
     *
     * @return
     */
    ArrayList<Task> getTasks();

    /**
     * Получение задачи по его ID
     * по этому методу есть добавление в список истории
     *
     * @param id
     * @return
     */
    Task getTask(int id);

    /**
     * Создание новой задачи
     *
     * @param task
     */
    void createNewTask(Task task);

    /**
     * Обновление задачи (Замена существующей)
     *
     * @param task
     */
    void updateTask(Task task);

    /**
     * Удаление задачи по его ID
     *
     * @param id
     */
    void deleteTask(int id);

}
