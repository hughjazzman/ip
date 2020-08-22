package ip.task;

public class TaskManager {
    private Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    public static int getTasksCount() {
        return tasksCount;
    }

    public Task addTask(Task task) {
        tasks[tasksCount] = task;
        tasksCount++;
        return task;
    }

    public Task addTodo(String description) {
        Todo todo = new Todo(description);
        return this.addTask(todo);
    }

    public Task addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        return this.addTask(deadline);
    }

    public Task addEvent(String description, String at) {
        Event event = new Event(description, at);
        return this.addTask(event);
    }



    public Task markAsDone(int id) {
        Task task = tasks[id-1];
        task.markAsDone();
        return task;
    }

    public void listTasks() {
        for (int i=0;i<tasksCount;i++) {
            System.out.println(" " + (i+1) + "." + tasks[i].toString() );
        }
    }


}
