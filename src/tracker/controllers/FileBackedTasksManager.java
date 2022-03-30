package tracker.controllers;

import tracker.model.Task;

import java.util.ArrayList;

/**
 * Класс менеджера для работы с файлами и с историей
 */
public class FileBackedTasksManager extends InMemoryTaskManager {
    private final String nameFile;                  // файл для автосохранения

    public FileBackedTasksManager(String nameFile) {
        this.nameFile = nameFile;
    }

    static void main(String[] args) {

    }

    @Override
    public ArrayList<Task> getMapTasks() {
        return super.getMapTasks();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
    }

    @Override
    public Task getTask(int id) {
        return super.getTask(id);
    }

    @Override
    public void createNewTask(Task task) {
        super.createNewTask(task);
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
    }

    @Override
    public void deleteTask(int id) {
        super.deleteTask(id);
    }


    private void save(){
        StringBuilder stringBuilder = new StringBuilder();

    }


}
