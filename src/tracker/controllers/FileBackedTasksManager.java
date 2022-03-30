package tracker.controllers;

import tracker.model.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Класс менеджера для работы с файлами и с историей
 */
public class FileBackedTasksManager extends InMemoryTaskManager {
    private final String nameFile;                  // файл для автосохранения
    private final String nameTestFile = "D://test/history.csv";                  // файл для автосохранения
    Path path = Paths.get(nameTestFile);

    public FileBackedTasksManager(String nameFile) {
        this.nameFile = nameFile;
    }

    static void main(String[] args) {

    }

    @Override
    public ArrayList<Task> getTasks() {
        return super.getTasks();
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
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
    }

    @Override
    public void deleteTask(int id) {
        super.deleteTask(id);
    }

    /**
     * Метод сохранения информации о задачах в файл *.csv
     * должен сохранять по порядку по id задачи
     */
    private void save(){
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nameTestFile, StandardCharsets.UTF_8, true))) {
            bw.write("id,type,name,status,description,epic");
            bw.write();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Task> listTask = getTasks();
        for (Task task : listTask) {
            stringBuilder.append(task);
        }
        stringBuilder.append("\n"); //Пустая строка перед историей
        stringBuilder.append("\n"); //Пустая строка перед историей




    }


}
