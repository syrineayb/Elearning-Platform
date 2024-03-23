import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileSidebarComponent } from './profile-sidebar.component';

describe('ProfileSidebarComponent', () => {
  let component: ProfileSidebarComponent;
  let fixture: ComponentFixture<ProfileSidebarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProfileSidebarComponent]
    });
    fixture = TestBed.createComponent(ProfileSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
