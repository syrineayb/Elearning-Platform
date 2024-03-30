import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { TopicResponse } from '../../models/topic/topic-response';
import { TopicRequest } from '../../models/topic/topic-request';
import { TopicPageResponse } from '../../models/topic/topic-page-response';

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  baseUrl = environment.app.baseUrl;

  constructor(private http: HttpClient) { }

  saveTopic(topic: TopicRequest): Observable<number> {
    return this.http.post<number>(`${this.baseUrl}/api/topics`, topic);
  }

  findAllTopics(page = 0, size = 3): Observable<TopicPageResponse> {
    const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString());

    return this.http.get<TopicPageResponse>(`${this.baseUrl}/api/topics/findAll`, { params });
  }

  getAllTopics(): Observable<TopicResponse[]> {
    return this.http.get<TopicResponse[]>(`${this.baseUrl}/api/topics`);
  }

  findById(topicId: number): Observable<TopicResponse> {
    return this.http.get<TopicResponse>(`${this.baseUrl}/api/topics/${topicId}`);
  }

  findTopicsByTitle(keyword: string, page = 0, size = 20): Observable<TopicPageResponse> {
    const params = new HttpParams()
        .set('keyword', keyword)
        .set('page', page.toString())
        .set('size', size.toString());

    return this.http.get<TopicPageResponse>(`${this.baseUrl}/api/topics/search`, { params });
  }
}
