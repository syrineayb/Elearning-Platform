import {Component, OnInit} from '@angular/core';
import {CoursePageResponse} from "../../../../models/course/course-page-response";
import {CourseService} from "../../../../services/course/course.service";

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit{
    courses : CoursePageResponse = {};
  constructor(private courseService: CourseService) { }
  loadCourses(): void {
    this.courseService.findAllCourses().subscribe({
      next:(response : CoursePageResponse) => {
        this.courses = response;
        console.log(response);
    },
      error: (error) => {
      console.error('Error loading courses:', error);
      // Handle error loading topics
    }
  });
}
  ngOnInit(): void {
    this.loadCourses();
  }

  protected readonly customElements = customElements;
}
