import { Component, EventEmitter, Input } from '@angular/core';
import { ToDo } from 'src/app/ToDo';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class ToDoComponent {
  @Input() editTodoVisible: boolean = false;
  @Input() addTodoVisible: boolean = true;
  todos: ToDo[] = this.getTodos();
  todo!: ToDo;
  selectedTodo!: ToDo;
  constructor() {
  }

  deleteTodo(todo: ToDo) {
    console.log("inside deleteTodo..");
    console.log("TODO:", todo);
    const idx = this.todos.indexOf(todo);
    console.log("idx:", idx);
    this.todos.splice(idx, 1);
    localStorage.setItem("todos", JSON.stringify(this.todos));
  }

  addTodo(todo: ToDo) {
    console.log("inside addTodo..");
    console.log("TODO:", todo);
    todo.srno=this.todos.length;
    this.todos.push(todo);
    localStorage.setItem("todos", JSON.stringify(this.todos));
  }

  doneTodo(todo: ToDo) {
    console.log("inside doneTodo..");
    console.log("TODO:", todo);
    const idx = this.todos.indexOf(todo);
    this.todos[idx].active = !this.todos[idx].active
    localStorage.setItem("todos", JSON.stringify(this.todos));
  }

  getTodos() {
    let todosString: string | null = localStorage.getItem("todos");
    if (todosString !== null) {
      try {
        return JSON.parse(todosString);
      } catch (error) {
        console.error("Error parsing JSON data:", error);
      }
    } else {
      console.log("No data found in localStorage");
      return [];
    }

  }

  editTodo(todo: ToDo) {
    console.log("inside editTodo..");
    console.log(this.todos);
    
    this.editTodoVisible = true;
    this.addTodoVisible = false;

    console.log("TODO:", todo);
    this.selectedTodo = todo;

    const idx = todo.srno;

    let oldTodo: ToDo = this.todos[idx];
    console.log("Old TODO:", oldTodo);

    this.todos[idx].title = todo.title;
    this.todos[idx].desc = todo.desc;
    console.log(this.todos);
    localStorage.setItem("todos", JSON.stringify(this.todos));
  }

  cancelEdit() {
    console.log("inside cancelEdit..");
    this.editTodoVisible = !this.editTodoVisible;
    this.addTodoVisible = !this.addTodoVisible;
  }

}