import { Component } from '@angular/core';

@Component({
  selector: 'app-instructor-profile',
  templateUrl: './instructor-profile.component.html',
  styleUrls: ['./instructor-profile.component.css']
})
export class InstructorProfileComponent {
  userType: string = ''; // Example: 'Candidate' or 'Instructor'
  role: string = ''; // Example: 'Developer', 'Manager', etc.
  location: string = ''; // Example: 'San Francisco, CA'
  firstName: string = ''; // Example: 'John'

  constructor() {
    // Initialize variables if needed
    this.userType = 'Candidate';
    this.role = 'Developer';
    this.location = 'San Francisco, CA';
    this.firstName = 'John';
  }

}
