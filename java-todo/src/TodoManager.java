import java.util.ArrayList;
import java.util.List;

public class TodoManager {
    private final List<Todo> todos = new ArrayList<>();
    private int nextId = 1;

    public Todo addTodo(String title) {
        Todo todo = new Todo(nextId++, title);
        todos.add(todo);
        return todo;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public Todo findById(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    public boolean completeTodo(int id) {
        Todo todo = findById(id);
        if (todo == null) return false;
        todo.setCompleted(true);
        return true;
    }

    public boolean deleteTodo(int id) {
        Todo todo = findById(id);
        if (todo == null) return false;
        return todos.remove(todo);
    }

    public void clearCompleted() {
        todos.removeIf(Todo::isCompleted);
    }
}
