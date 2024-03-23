import { Component } from '@angular/core';
import {TopicService} from "../../../app-common/services/topic/topic.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-topic',
  templateUrl: './add-topic.component.html',
  styleUrls: ['./add-topic.component.css']
})
export class AddTopicComponent {
  topicTitle: string = '';

  constructor(private topicService: TopicService, private router: Router) {}

  addTopic(): void {
    if (this.topicTitle.trim()) {
      this.topicService.addTopic({ title: this.topicTitle })
        .subscribe(() => {
          this.router.navigate(['/listTopic']); // Navigate to the list topic component after adding
        });
    }
  }
}
