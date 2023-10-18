import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllmissionsComponent } from './allmissions.component';

describe('AllbookingsComponent', () => {
  let component: AllmissionsComponent;
  let fixture: ComponentFixture<AllmissionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllmissionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllmissionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
