import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDatasetComponent } from './admin-dataset.component';

describe('AdminDatasetComponent', () => {
  let component: AdminDatasetComponent;
  let fixture: ComponentFixture<AdminDatasetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminDatasetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminDatasetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
