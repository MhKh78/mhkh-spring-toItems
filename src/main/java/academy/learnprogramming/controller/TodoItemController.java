package academy.learnprogramming.controller;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;
import academy.learnprogramming.service.TodoItemService;
import academy.learnprogramming.util.AttributeNames;
import academy.learnprogramming.util.Mappings;
import academy.learnprogramming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Slf4j
@Controller
public class TodoItemController {
    // == fields ==
    private final TodoItemService todoItemService;

    // == constructors ==
    @Autowired
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    // == model attributes ==
    @ModelAttribute
    public TodoData todoData(){
        return todoItemService.getData();
    }

    // == handler methods ==

    // http://localhost:3000/todo-list/items
    @GetMapping(Mappings.ITEMS)
    public String items(){
        return ViewNames.ITEMS_LIST;
    }

    // http://localhost:3000/todo-list/add_item
    @GetMapping(Mappings.ADD_ITEM)
    public String addEditItem(
            @RequestParam(required = false, defaultValue = "-1") int id, Model model){

        log.info("editing id = {}", id);
        TodoItem todoItem = todoItemService.getItem(id);
        if(todoItem == null){
            todoItem = new TodoItem("", "", LocalDate.now());
        }

        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.ADD_ITEM;
    }

    // http://localhost:3000/todo-list/add_item
    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(
            @ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem){
        log.info("todoItem from form = {}", todoItem);

        if(todoItem.getId() == 0){
            todoItemService.addItem(todoItem);
        } else {
            todoItemService.updateItem(todoItem);
        }
        return "redirect:/" + Mappings.ITEMS;
    }

    // http://localhost:3000/todo-list/delete_item
    @GetMapping(Mappings.DELETE_ITEM)
    public String DeleteItem(@RequestParam int id){
        log.info("Deleting item with id = {}", id);
        todoItemService.removeItem(id);
        return "redirect:/" + Mappings.ITEMS;
    }
    // http://localhost:3000/todo-list/items
    @GetMapping(Mappings.VIEW_ITEM)
    public String viewItem(@RequestParam int id, Model model){
        TodoItem todoItem =todoItemService.getItem(id);
        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.VIEW_ITEM;
    }
}
