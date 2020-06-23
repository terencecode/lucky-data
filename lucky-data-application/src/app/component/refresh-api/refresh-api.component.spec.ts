import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefreshApiComponent } from './refresh-api.component';

describe('RefreshApiComponent', () => {
  let component: RefreshApiComponent;
  let fixture: ComponentFixture<RefreshApiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefreshApiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefreshApiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
