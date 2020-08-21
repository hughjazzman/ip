package ip.task;

public class TaskManager {
    private Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    public void addTask(String description) {
        tasks[tasksCount] = new Task(description);
        tasksCount++;
    }

    public Task markAsDone(int id) {
        Task task = tasks[id-1];
        task.markAsDone();
        return task;
    }

    public void listTasks() {
        for (int i=0;i<tasksCount;i++) {
            System.out.println(" " + (i+1) + "." +
                    tasks[i].getStatusIcon() +
                    " " + tasks[i].getDescription() );
        }
    }


}
