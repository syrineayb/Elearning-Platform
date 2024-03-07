import { Component } from '@angular/core';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent {
  courses = [
    {
      title: 'Web Design & Development Course for Beginners',
      imageUrl: 'assets/img/course-1.jpg',
      rating: 5,
      instructor: 'John Doe',
      duration: '1.49 Hrs',
      students: 30
    },
    {
      title: 'Graphic Design Course',
      imageUrl: 'assets/img/course-2.jpg',
      rating: 5,
      instructor: 'Jane Smith',
      duration: '2.5 Hrs',
      students: 25
    },
    {
      title: 'Video Editing Masterclass',
      imageUrl: 'assets/img/course-3.jpg',
      rating: 4.5,
      instructor: 'Alice Johnson',
      duration: '3 Hrs',
      students: 40
    }
  ];
}
