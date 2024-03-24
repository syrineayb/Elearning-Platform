import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],

})
export class ProfileComponent implements OnInit {
  @Input() username: string = '';
  @Input() location: string = '';
  @Input() biography: string = '';
  @Input() role: string = ''; // 'instructor' or 'candidate'
  @Input() userDetails: any;

  ngOnInit(): void {
  } // Object containing user details


}
