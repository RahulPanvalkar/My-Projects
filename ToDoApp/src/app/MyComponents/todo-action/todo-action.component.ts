import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ToDo } from 'src/app/ToDo';

@Component({
  selector: 'todo-action',
  templateUrl: './todo-action.component.html',
  styleUrls: ['./todo-action.component.css']
})
export class ToDoActionComponent {
  @Input() todo!: ToDo;
  @Input() i! : number;
  @Output() deleteEventEmitter = new EventEmitter<any>();
  @Output() doneEventEmitter = new EventEmitter<any>();
  @Output() editEventEmitter = new EventEmitter<any>();

  onDelete(todo: ToDo) {
    console.log("onDelete triggered..");
    const isConfirm:boolean = confirm(`Delete "${todo.title}" todo?`);
    if(isConfirm)
      this.deleteEventEmitter.emit(todo);
  }

  onDone(todo: ToDo) {
    console.log("onDone triggered..");
    this.doneEventEmitter.emit(todo);
  }

  onEdit(todo:ToDo){
    console.log("onEdit triggered => todo : ",todo);
    todo.srno=this.i;
    console.log("onEdit triggered => todo : ",todo);
    this.editEventEmitter.emit(todo);
  }

}
