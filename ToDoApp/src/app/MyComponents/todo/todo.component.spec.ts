import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToDoComponent } from './todo.component';

describe('TodoComponent', () => {
  let component: ToDoComponent;
  let fixture: ComponentFixture<ToDoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ToDoComponent]
    });
    fixture = TestBed.createComponent(ToDoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
