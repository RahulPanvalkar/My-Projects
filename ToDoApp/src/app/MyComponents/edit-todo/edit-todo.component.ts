import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ToDo } from 'src/app/ToDo';

@Component({
  selector: 'app-edit-todo',
  templateUrl: './edit-todo.component.html',
  styleUrls: ['./edit-todo.component.css']
})
export class EditTodoComponent {
  @Input() editTodoVisible: boolean = false;
  @Input() addTodoVisible: boolean = true;
  @Output() editToDoEventEmitter = new EventEmitter<ToDo>();
  @Output() cancelEditEventEmitter = new EventEmitter<ToDo>();
  
  @Input() todo!: ToDo;
  title!: string;
  desc!: string;

  ngOnChanges() {
    if (this.todo) {
      console.log("Inside edit ngOnChanges..");
      console.log("Inside ngOnChanges => this.todo :: ",this.todo)
      console.log("addTodoDivVisible : ", this.addTodoVisible);
      console.log("editTodoDivVisible : ", this.editTodoVisible);

      this.title = this.todo.title;
      this.desc = this.todo.desc;
    }
  }
  adjustTextareaHeight(event: { target: any; }) {
    const textarea = event.target;
    textarea.style.height = "auto";
    textarea.style.height = (textarea.scrollHeight) + "px";
  }
  
  onEditSubmit() {
    console.log("inside onEditSubmit..");
    console.log("title : ", this.title);
    console.log("desc : ", this.desc);

    if (this.title || this.desc) {
      const todo: ToDo = {
        srno:this.todo.srno,
        title: this.title,
        desc: this.desc,
        active: true
      }
      console.log("todo : ",todo);
      this.editToDoEventEmitter.emit(todo);
      this.title = "";
      this.desc = "";
    } else {
      alert("Invalid do to!");
    }

  }

  onCancel() {
    console.log("inside onCancel..");
    console.log("addTodoDivVisible : ", this.addTodoVisible);
    console.log("editTodoDivVisible : ", this.editTodoVisible);

    console.log("this.title : ", this.title);
    console.log("this.desc : ", this.desc);
    this.title = "";
    this.desc = "";
    this.ngOnChanges();
    console.log("this.title : ", this.title);
    console.log("this.desc : ", this.desc);
    this.cancelEditEventEmitter.emit();
  }
}
