package org.isep.cleancode.application;

import org.isep.cleancode.Todo;
import java.util.List;

public class TodoManager {
        private final ITodoRepository ITodoRepository;

        public TodoManager(ITodoRepository ITodoRepository) {
            this.ITodoRepository = ITodoRepository;
        }

        public void addTodo(Todo todo) throws IllegalArgumentException {
            if (!todo.nameIsValid()) {
                throw new IllegalArgumentException("Todo name is invalid. It must be non-empty and shorter than 64 characters.");
            }

            if (ITodoRepository.nameExists(todo.getName())) {
                throw new IllegalArgumentException("Todo name must be unique.");
            }

            if (todo.getDueDate() != null &&!todo.getDueDate().isBlank() && !todo.dueDateIsValid()) {
                throw new IllegalArgumentException("Due date is invalid. It must be a valid date in the future.");
            }

            ITodoRepository.addTodo(todo);
        }

        public List<Todo> getAllTodos() {
            return ITodoRepository.getAllTodos();
        }
}
