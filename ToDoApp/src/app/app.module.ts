import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ToDoComponent } from './MyComponents/todo/todo.component';
import { AddTodoComponent } from './MyComponents/add-todo/add-todo.component';
import { ToDoActionComponent } from './MyComponents/todo-action/todo-action.component';
import { FormsModule } from '@angular/forms';
import { EditTodoComponent } from './MyComponents/edit-todo/edit-todo.component';

@NgModule({
  declarations: [
    AppComponent,
    ToDoComponent,
    AddTodoComponent,
    ToDoActionComponent,
    EditTodoComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
