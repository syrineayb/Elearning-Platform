import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Topic} from "../../../../models/topic";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private apiUrl = 'http://localhost:8080/api/topics';

  constructor(private http: HttpClient) {}

  addTopic(topic: Topic): Observable<void> {
    return this.http.post<void>(this.apiUrl, topic);
  }

  getAllTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.apiUrl);
  }

  deleteTopic(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
