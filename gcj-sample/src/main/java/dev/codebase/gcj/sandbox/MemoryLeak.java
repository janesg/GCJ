package dev.codebase.gcj.sandbox;

import java.util.ArrayDeque;
import java.util.Deque;

public class MemoryLeak {

    public static void main(String[] args) {
        
        TaskList taskList = new TaskList();
        final TaskCreator taskCreator = new TaskCreator(taskList);
        
        Thread memHungry = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    taskCreator.createTask();
                }
            }
        });
        
        memHungry.start();
        
        try {
            memHungry.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("Completed successfully");
    }
    
    private static class TaskCreator {
        private TaskList taskList;
        
        public TaskCreator(TaskList taskList) {
            this.taskList = taskList;
        }
        
        public void createTask() {
            taskList.addTask(new Task());
        }
    }
    
    private static class TaskList {
        private Deque<Task> tasks = new ArrayDeque<Task>();
        
        public void addTask(Task task) {
            tasks.add(task);
            tasks.peek().execute();  //Memory leak!
        }
    }
    
    private static class Task {
        private Object[] array = new Object[10000];
        
        public void execute() {
           //dostuff
        }
    }
}