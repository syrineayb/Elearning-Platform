import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-categories',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoriesComponent {
    categories = [
        {
            title: 'Web Design',
            imageUrl: 'assets/img/course-1.jpg',
            nbCourses: 130
        },
        {
            title: 'Graphic Design Course',
            imageUrl: 'assets/img/course-2.jpg',
            nbCourses: 200

        },
        {
            title: 'Video Editing Masterclass',
            imageUrl: 'assets/img/course-3.jpg',
            nbCourses: 130

        }
    ];}
