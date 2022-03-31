package tracker.controllers;

import tracker.model.Epic;
import tracker.model.Subtask;
import tracker.model.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Класс менеджера для работы с файлами и с историей
 */
public class FileBackedTasksManager extends InMemoryTaskManager {
    private final String nameFile;                  // файл для автосохранения
    private final String nameTestFile = "E:/testDir/history.csv";                  // файл для автосохранения
    Path path = Paths.get(nameTestFile);

    public FileBackedTasksManager(String nameFile) {
        this.nameFile = nameFile;
    }

    static void main(String[] args) {

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
        StringBuilder stringBuilder = new StringBuilder();
        int fullSize = mapTasks.size() + mapEpics.size() + mapSubtasks.size();
        for (int i = 0; i < fullSize; i++) {
            if (mapTasks.containsKey(i)) {
                stringBuilder.append(mapTasks.get(i));
            }else if (mapEpics.containsKey(i)) {
                stringBuilder.append(mapEpics.get(i));
            }else if (mapSubtasks.containsKey(i)){
                stringBuilder.append(mapSubtasks.get(i));
            }
        }
        stringBuilder.append("\n\n");
        List<Task> list = historyList.getHistory();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                stringBuilder.append(list.get(i).getIdTask());
            }else{
                stringBuilder.append(list.get(i).getIdTask()).append(",");
            }
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nameTestFile, StandardCharsets.UTF_8))) {
            bw.write("id,type,name,status,description,epic");
            bw.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAllTasks(){
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nameTestFile, StandardCharsets.UTF_8));) {
            stringBuilder.append(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
