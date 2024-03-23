import { Component, OnInit } from '@angular/core';
import {Topic} from "../../../../models/topic";
import {TopicService} from "../../../app-common/services/topic/topic.service";

@Component({
  selector: 'app-list-topic',
  templateUrl: './list-topic.component.html',
  styleUrls: ['./list-topic.component.css']
})
export class ListTopicComponent implements OnInit {
  topics!: Topic[];

  constructor(private topicService: TopicService) {}

  ngOnInit(): void {
    this.getAllTopics();
  }

  getAllTopics(): void {
    this.topicService.getAllTopics()
      .subscribe(topics => this.topics = topics);
  }

  deleteTopic(id: number): void {
    this.topicService.deleteTopic(id)
      .subscribe(() => {
        this.topics = this.topics.filter(topic => topic.id !== id);
      });
  }
}
