import { Component } from '@angular/core';

@Component({
  selector: 'app-contactus',
  templateUrl: './contactus.component.html',
  styleUrls: ['./contactus.component.css']
})
export class ContactusComponent {
  // Define variables to store form data
  name: string = '';
  email: string = '';
  message: string = '';

  // Handle form submission
  submitForm() {
    // Add your logic for form submission here
    console.log('Form submitted:', this.name, this.email, this.message);
  }
}
