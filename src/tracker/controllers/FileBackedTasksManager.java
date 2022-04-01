package tracker.controllers;

import tracker.history.HistoryManager;
import tracker.model.Epic;
import tracker.model.Subtask;
import tracker.model.Task;
import tracker.util.ManagerSaveException;
import tracker.util.Status;
import tracker.util.TypeOfTasks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс менеджера для работы с файлами и с историей
 */
public class FileBackedTasksManager extends InMemoryTaskManager {

    public static void main(String[] args) {
        FileBackedTasksManager manager = new FileBackedTasksManager("E:/testDir/history.csv");

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


//    private final String nameFile;                  // файл для автосохранения
    private final String nameTestFile;                  // файл для автосохранения
    private final Path path;


    public FileBackedTasksManager(String nameTestFile) {
        this.nameTestFile = nameTestFile;
        path = Paths.get(nameTestFile);

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
    public Task getTask(int id) {
        if (mapTasks.containsKey(id)) {
            historyList.add(mapTasks.get(id));             // добавление задачи в лист истории
            save();
            return mapTasks.get(id);
        } else {
            return null;
        }
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
    public Subtask getSubtask(int id) {
        if (mapSubtasks.containsKey(id)) {
            historyList.add(mapSubtasks.get(id));             // добавление задачи в лист истории
            save();
            return mapSubtasks.get(id);
        } else {
            return null;
        }
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
    public Epic getEpic(int id) {
        if (mapEpics.containsKey(id)) {
            historyList.add(mapEpics.get(id));          // добавление задачи в лист истории
            save();
            return mapEpics.get(id);
        } else {
            return null;
        }
    }

    //    @Override
//    public List<Task> history() {
//        save();
//        return historyList.getHistory();
//    }

    /**
     * Метод сохранения информации о задачах в файл *.csv
     * должен сохранять по порядку по id задачи
     */
    private void save(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nameTestFile, StandardCharsets.UTF_8))) {
            bw.write("id,type,name,status,description,epic");
            int fullSize = mapTasks.size() + mapEpics.size() + mapSubtasks.size();      //получение всех задач в порядке возрастания ID
            for (int i = 0; i < fullSize; i++) {
                if (mapTasks.containsKey(i)) {
                    bw.write(mapTasks.get(i).toString());
                }else if (mapEpics.containsKey(i)) {
                    bw.write(mapEpics.get(i).toString());
                }else if (mapSubtasks.containsKey(i)){
                    bw.write(mapSubtasks.get(i).toString());
                }
            }
            bw.write("\n\n");                           //пустая строка
//            bw.write(toString(historyList));                //получение истории
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private static FileBackedTasksManager loadFromFile(File file){
//        FileBackedTasksManager manager = new FileBackedTasksManager();
//        try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
//            while(br.ready()){
//                if (br.readLine() != null){
//                    String line = br.readLine();
//                    FileBackedTasksManager.fromStringTask(line);
//                }else {
//                    String line = br.readLine();
//                    String line2 = br.readLine();
//                    fromString(line2);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public void loadAllTasks(){
////        try {
////            String line = Files.readString(Path.of(nameTestFile));
////            if (line != null){
////                fromStringTask(line);
////            }
////            save();
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        try (BufferedReader br = new BufferedReader(new FileReader(nameTestFile, StandardCharsets.UTF_8))) {
//            while(br.ready()){
//                if (br.readLine() != null){
//                    String line = br.readLine();
//                    fromStringTask(line);
//                }else {
//                    String line = br.readLine();
//                    String line2 = br.readLine();
//                    fromString(line2);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * Метод сохранения задачи в строку
     * @param task
     * @return
     */
    public String toString(Task task){
        String fullTaskName = null;
        TypeOfTasks typeOfTask = task.getTypeOfTask();
        if (typeOfTask.equals(TypeOfTasks.TASK) || typeOfTask.equals(TypeOfTasks.EPIC)){
            fullTaskName = task.getIdTask() +
                    "," + task.getTypeOfTask() +
                    "," + task.getName() +
                    "," + task.getStatus() +
                    "," + task.getDiscription();
        }else if (typeOfTask.equals(TypeOfTasks.SUBTASK)){
            fullTaskName = task.getIdTask() +
                    "," + task.getTypeOfTask() +
                    "," + task.getName() +
                    "," + task.getStatus() +
                    "," + task.getDiscription() +
                    "," + task.getIdEpic();
        }
        return fullTaskName;
    }

    /**
     * Метод создания задачи из строки
     */
    private Task fromString(String value){
        String[] arrayList = value.split(",");
        Task task = null;

        if (arrayList[1].equals(TypeOfTasks.TASK)){
            if (arrayList[3].equals(Status.NEW)){
                task = new Task(Integer.parseInt(arrayList[0]), arrayList[2], Status.NEW, arrayList[4]);
            }else if (arrayList[3].equals(Status.IN_PROGRESS)){
                task = new Task(Integer.parseInt(arrayList[0]), arrayList[2], Status.IN_PROGRESS, arrayList[4]);
            }else if (arrayList[3].equals(Status.DONE)){
                task = new Task(Integer.parseInt(arrayList[0]), arrayList[2], Status.DONE, arrayList[4]);
            }
        }else if (arrayList[1].equals(TypeOfTasks.SUBTASK)){
            if (arrayList[3].equals(Status.NEW)){
                task = new Subtask(Integer.parseInt(arrayList[0]), arrayList[2], Status.NEW, arrayList[4], Integer.parseInt(arrayList[5]));
            }else if (arrayList[3].equals(Status.IN_PROGRESS)){
                task = new Subtask(Integer.parseInt(arrayList[0]), arrayList[2], Status.IN_PROGRESS, arrayList[4], Integer.parseInt(arrayList[5]));
            }else if (arrayList[3].equals(Status.DONE)){
                task = new Subtask(Integer.parseInt(arrayList[0]), arrayList[2], Status.DONE, arrayList[4], Integer.parseInt(arrayList[5]));
            }

        }else if (arrayList[1].equals(TypeOfTasks.EPIC)){
            if (arrayList[3].equals(Status.NEW)){
                task = new Epic(Integer.parseInt(arrayList[0]), arrayList[2], Status.NEW, arrayList[4]);
            }else if (arrayList[3].equals(Status.IN_PROGRESS)){
                task = new Epic(Integer.parseInt(arrayList[0]), arrayList[2], Status.IN_PROGRESS, arrayList[4]);
            }else if (arrayList[3].equals(Status.DONE)){
                task = new Epic(Integer.parseInt(arrayList[0]), arrayList[2], Status.DONE, arrayList[4]);
            }
        }
        return task;
    }

//
//    /**
//     * логичнее перенести его в класс хистори
//     * @param manager
//     * @return
//     */
//    private static String toString(HistoryManager manager){
//        StringBuilder stringBuilder = new StringBuilder();
//        List<Task> list = manager.getHistory();
//        for (int i = 0; i < list.size(); i++) {
//            if (i == list.size() - 1){
//                stringBuilder.append(list.get(i).getIdTask());
//            }else {
//                stringBuilder.append(list.get(i).getIdTask()).append(",");
//            }
//        }
////        for (Task task : list) {
////        }
//        return stringBuilder.toString();
//    }
//
//    private static List<Integer> fromString(String value){
//        String[] array = value.split(",");
//        List<Integer> listTaskHistory = new ArrayList<>();
//        for (int i = 0; i < array.length; i++) {
//            listTaskHistory.add(Integer.parseInt(array[i]));
//        }
//        return listTaskHistory;
//    }


}
