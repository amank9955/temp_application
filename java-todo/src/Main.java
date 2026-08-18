import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TodoManager manager = new TodoManager();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("============================");
        System.out.println("      JAVA TODO LIST");
        System.out.println("============================");

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addTodo();
                case 2 -> showTodos();
                case 3 -> completeTodo();
                case 4 -> deleteTodo();
                case 5 -> clearCompleted();
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n1. Add Todo");
        System.out.println("2. Show Todos");
        System.out.println("3. Complete Todo");
        System.out.println("4. Delete Todo");
        System.out.println("5. Clear Completed");
        System.out.println("0. Exit");
    }

    private static void addTodo() {
        System.out.print("Enter task: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Task cannot be empty.");
            return;
        }

        Todo todo = manager.addTodo(title);
        System.out.println("Todo added: " + todo);
    }

    private static void showTodos() {
        if (manager.getTodos().isEmpty()) {
            System.out.println("No todos found.");
            return;
        }

        System.out.println("\nYour Todos:");
        for (Todo todo : manager.getTodos()) {
            System.out.println(todo);
        }
    }

    private static void completeTodo() {
        int id = readInt("Enter todo ID to complete: ");
        if (manager.completeTodo(id)) {
            System.out.println("Todo marked as completed.");
        } else {
            System.out.println("Todo not found.");
        }
    }

    private static void deleteTodo() {
        int id = readInt("Enter todo ID to delete: ");
        if (manager.deleteTodo(id)) {
            System.out.println("Todo deleted.");
        } else {
            System.out.println("Todo not found.");
        }
    }

    private static void clearCompleted() {
        manager.clearCompleted();
        System.out.println("Completed todos cleared.");
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
