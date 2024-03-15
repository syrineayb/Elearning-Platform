import { Component,Input  } from '@angular/core';
/*import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
 */
@Component({
  selector: 'app-candidate-profile',
  templateUrl: './candidate-profile.component.html',
  styleUrls: ['./candidate-profile.component.css'],
  //imports: [MatButtonModule, MatMenuModule],

})
export class CandidateProfileComponent {
  @Input() username: string = '';
  @Input() location: string = '';
  @Input() biography: string = '';
  @Input() role: string = ''; // 'instructor' or 'candidate'
  @Input() userDetails: any; // Object containing user details

}
