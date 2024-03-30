// navbar.component.ts

import { Component, OnInit } from '@angular/core';
import { TopicService } from "../../services/topic/topic.service";
import { TopicPageResponse } from "../../models/topic/topic-page-response";
import { TopicResponse } from "../../models/topic/topic-response";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  topicsPage: TopicPageResponse = {};
  searchedTopics: TopicResponse[] | undefined = []; // Initialize as an empty array
  searchKeyword: string = ''; // Variable to store the search keyword
  isDropdownOpen: boolean = false;

  constructor(private topicService: TopicService) { }

  ngOnInit(): void {
    this.loadAllTopics(); // Load all topics automatically on component initialization
  }

  // Method to search topics containing the search keyword
  searchTopics(): void {
    if (this.searchKeyword.trim() !== '') {
      this.topicService.findTopicsByTitle(this.searchKeyword).subscribe({
        next: (response) => {
          if (response.content) {
            this.searchedTopics = response.content; // Populate searched topics array
          } else {
            this.searchedTopics = []; // If content is undefined, initialize as an empty array
          }
          console.log(response);
        },
        error: (error) => {
          console.error('Error searching topics:', error);
          // Handle error searching topics
        }
      });
    } else {
      this.searchedTopics = []; // Clear searched topics array if search keyword is empty
    }
  }


  // Method to load all topics
  loadAllTopics(): void {
    this.topicService.findAllTopics().subscribe({
      next: (response) => {
        this.topicsPage = response;
        console.log(response);
      },
      error: (error) => {
        console.error('Error loading topics:', error);
        // Handle error loading topics
      }
    });
  }

  // Method to handle click event on "LEARN" button
  loadAllTopicsOnClick(): void {
    this.loadAllTopics();
  }

  showDropdown() {
    this.isDropdownOpen = true;
  }

  hideDropdown() {
    this.isDropdownOpen = false;
  }
}
