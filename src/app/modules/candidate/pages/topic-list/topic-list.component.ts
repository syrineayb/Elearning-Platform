import {Component, OnInit} from '@angular/core';
import {TopicService} from "../../../../services/topic/topic.service";
import {TopicResponse} from "../../../../models/topic/topic-response";
import {TopicPageResponse} from "../../../../models/topic/topic-page-response";

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css']
})
export class TopicListComponent implements OnInit {
  topTopics : TopicPageResponse = {};
  constructor(private topicService: TopicService) { }

  loadTopics(): void {
    this.topicService.findAllTopics().subscribe({
      next: (response) => {
        this.topTopics = response;
      },
      error: (error) => {
        console.error('Error loading topics:', error);
        // Handle error loading topics
      }
    });
  }

  ngOnInit(): void {
    this.loadTopics();
  }
}
