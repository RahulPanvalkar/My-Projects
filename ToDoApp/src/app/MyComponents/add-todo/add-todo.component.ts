import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { ToDo } from 'src/app/ToDo';


@Component({
  selector: 'app-add-todo',
  templateUrl: './add-todo.component.html',
  styleUrls: ['./add-todo.component.css']
})
export class AddTodoComponent {
  @Input() editTodoVisible: boolean = false;
  @Input() addTodoVisible: boolean = true;
  @Output() addToDoEventEmitter = new EventEmitter<ToDo>();
  @Input() todo!: ToDo;
  title!: string;
  desc!: string;
  @Output() editToDoEventEmitter = new EventEmitter<ToDo>();
  @Output() cancelEditEventEmitter = new EventEmitter<ToDo>();

  ngOnChanges() {
    console.log("Inside add ngOnChanges..");
    console.log("Inside ngOnChanges => this.todo :: ", this.todo);
    console.log("addTodoDivVisible : ", this.addTodoVisible);
    console.log("editTodoDivVisible : ", this.editTodoVisible);
    this.title = "";
    this.desc = "";
  }

  @ViewChild('descTextarea') descTextarea!: ElementRef;

  adjustTextareaHeight(event: { target: any; }) {
    const textarea = event.target;
    textarea.style.height = "auto";
    textarea.style.height = (textarea.scrollHeight) + "px";
  }

  onAdd() {
    console.log("inside onSubmit..");
    console.log("title : ", this.title);
    console.log("desc : ", this.desc);

    if (this.title || this.desc) {
      const todo: ToDo = {
        srno : 0,
        title: this.title,
        desc: this.desc,
        active: true
      }

      this.addToDoEventEmitter.emit(todo);
      this.title = "";
      this.desc = "";
    } else {
      alert("Invalid do to!");
    }
  }

}
