package org.isep.cleancode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Todo {

    // this Todo class should be completed to achieve Step 1

    private String name;
    public String dueDate;

    public Todo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Todo(String name, String dueDate) {
        this.name = name;
        this.dueDate = dueDate; // Allow dueDate to be null
    }
    public String getDueDate() {
        return dueDate;
    }
    public boolean dueDateIsValid() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            Date dueDateObject = dateFormat.parse(dueDate);
            return dueDateObject.after(new Date());
        } catch (ParseException e) {
            return false;
        }
    }


    public boolean nameIsValid() {
        return name != null && !name.trim().isEmpty() && name.length() <= 64;
    }
}
