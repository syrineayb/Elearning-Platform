import {TopicResponse} from "../topic/topic-response";
import {LessonResponse} from "../lesson/lesson-response";

export interface CourseResponse {
  id: number;
  title: string;
  description: string;
  createdAt: Date;
  imageUrl?: string;
  duration?: string;
  publisherName: string;
  topic: TopicResponse;
  lessons: LessonResponse[];
}
