package academy.learnprogramming.service;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;

public interface TodoItemService {

     void addItem(TodoItem todoItem);

     void removeItem(int id);

     TodoItem getItem(int id);

     void updateItem(TodoItem todoItem);

     TodoData getData();

}
