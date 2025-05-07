package org.isep.cleancode.presentation;

import com.google.gson.Gson;
import org.isep.cleancode.Todo;
import org.isep.cleancode.application.TodoManager;
import org.isep.cleancode.persistence.csvfiles.TodoCsvFilesRepository;
import org.isep.cleancode.persistence.inmemory.TodoInMemoryRepository;
import spark.Request;
import spark.Response;

import java.util.List;

public class TodoController {

    private static final Gson gson = new Gson();
    private final TodoManager todoManager;

    public TodoController() {
        // Instantiate TodoManager with a TodoRepository
        this.todoManager = new TodoManager(new TodoCsvFilesRepository());
    }

    public Object getAllTodos(Request req, Response res) {
        res.type("application/json");
        List<Todo> todos = todoManager.getAllTodos();
        return gson.toJson(todos);
    }

    public Object createTodo(Request req, Response res) {
        Todo newTodo = gson.fromJson(req.body(), Todo.class);
        if (newTodo == null) {
            res.status(400);
            return "Invalid Todo";
        }

        try {
            todoManager.addTodo(newTodo);
            res.status(201);
            res.type("application/json");
            return gson.toJson(newTodo);
        } catch (IllegalArgumentException e) {
            res.status(400);
            return e.getMessage();
        }
    }
}