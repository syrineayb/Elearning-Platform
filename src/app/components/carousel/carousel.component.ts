import { Component } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent {
  carouselItems = [
    {
      imageUrl: '/assets/img/carousel-1.jpg',
      title: 'Best Online Courses',
      subtitle: 'The Best Online Learning Platform',
      description: 'Vero elitr justo clita lorem. Ipsum dolor at sed stet sit diam no. Kasd rebum ipsum et diam justo clita et kasd rebum sea sanctus eirmod elitr.'
    },
    {
      imageUrl: '/assets/img/carousel-2.jpg',
      title: 'Best Online Courses',
      subtitle: 'Get Educated Online From Your Home',
      description: 'Vero elitr justo clita lorem. Ipsum dolor at sed stet sit diam no. Kasd rebum ipsum et diam justo clita et kasd rebum sea sanctus eirmod elitr.'
    }
  ];
}
