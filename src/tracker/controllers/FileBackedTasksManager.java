package tracker.controllers;

import tracker.model.Epic;
import tracker.model.Subtask;
import tracker.model.Task;
import tracker.util.ManagerSaveException;
import tracker.util.Status;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс менеджера для работы с файлами и с историей
 */
public class FileBackedTasksManager extends InMemoryTaskManager {
     public static void main(String[] args) {
        FileBackedTasksManager manager = new FileBackedTasksManager("nameFile");

        manager.createNewTask(new Task(0, "Имя1", Status.NEW, "Что купить1"));
        manager.createNewTask(new Task(1, "Имя2", Status.NEW, "Что купить2"));
        manager.createNewEpic(new Epic(2, "Имя1", Status.NEW, "Где купить1"));
        manager.createNewSubTask(new Subtask(3, "Саб1", Status.NEW, "Где взять", 2));
        manager.createNewSubTask(new Subtask(4, "Саб2", Status.NEW, "Где взять", 2));
        manager.createNewTask(new Task(5, "Имя3", Status.NEW, "Что купить3"));
        manager.createNewTask(new Task(6, "Имя4", Status.NEW, "Что купить4"));
        manager.createNewTask(new Task(7, "Имя5", Status.NEW, "Что купить5"));

        manager.getTask(5);
        manager.getTask(6);
        manager.getTask(7);
        manager.getEpic(2);
        manager.getSubtask(4);
        manager.getSubtask(3);
        manager.getTask(0);
        System.out.println("История:");
        System.out.println(manager.history());
    }



    private final String nameFile;                  // файл для автосохранения
    private final String nameTestFile = "E:/testDir/history.csv";                  // файл для автосохранения
    Path path = Paths.get(nameTestFile);

    public FileBackedTasksManager(String nameFile) {
        this.nameFile = nameFile;
    }






    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void createNewTask(Task task) {
        super.createNewTask(task);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void deleteTask(int id) {
        super.deleteTask(id);
        save();
    }




    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
    }

    @Override
    public void createNewSubTask(Subtask subtask) {
        super.createNewSubTask(subtask);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void deleteSubtask(int id) {
        super.deleteSubtask(id);
        save();
    }





    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    @Override
    public void createNewEpic(Epic epic) {
        super.createNewEpic(epic);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void deleteEpic(int id) {
        super.deleteEpic(id);
        save();
    }

    @Override
    public List<Task> history() {
        save();
        return super.history();
    }

    /**
     * Метод сохранения информации о задачах в файл *.csv
     * должен сохранять по порядку по id задачи
     */
    private void save(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nameTestFile, StandardCharsets.UTF_8))) {
            bw.write("id,type,name,status,description,epic");
            int fullSize = mapTasks.size() + mapEpics.size() + mapSubtasks.size();
            for (int i = 0; i < fullSize; i++) {
                if (mapTasks.containsKey(i)) {
                    bw.write(mapTasks.get(i).toString());
                }else if (mapEpics.containsKey(i)) {
                    bw.write(mapEpics.get(i).toString());
                }else if (mapSubtasks.containsKey(i)){
                    bw.write(mapSubtasks.get(i).toString());
                }
            }
            bw.write("\n\n");
            bw.write("HelloTest");//вместо этого должен быть метод тостринг у истории
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadAllTasks(){
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nameTestFile, StandardCharsets.UTF_8))) {
            while(br.ready()){
                list.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
