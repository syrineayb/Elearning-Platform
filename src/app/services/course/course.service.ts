import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {TopicPageResponse} from "../../models/topic/topic-page-response";
import {catchError, Observable} from "rxjs";
import {CourseResponse} from "../../models/course/course-response";
import {CourseRequest} from "../../models/course/course-request";
import {CoursePageResponse} from "../../models/course/course-page-response";

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  baseUrl = environment.app.baseUrl;

  constructor(private http: HttpClient) {
  }

  findAllCourses(page = 0, size = 3) {
    return this.http.get<CoursePageResponse>(`${this.baseUrl}/api/courses/findAll`,
      {
        params: {
          'page': page,
          'size': size
        }
      }
    );
  }
  getAllCourses(): Observable<CourseResponse[]> {
    return this.http.get<CourseResponse[]>(`${this.baseUrl}/api/courses`);
  }

  findById(courseId: number) {
    return this.http.get<CourseResponse>(`${this.baseUrl}/courses/${courseId}`);
  }
  saveCourse(course: CourseRequest) {
    return this.http.post<number>(`${this.baseUrl}/courses`, course);
  }

}
