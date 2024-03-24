export interface NavItem {
  routeLink: string;
  icon: string;
  label: string;
}

export interface NavbarData {
  instructor: NavItem[];
  candidate: NavItem[];
}

export const navbarData: NavbarData = {
  instructor: [
    {
      routeLink: 'dashboard',
      icon: 'fal , fa-home',
      label: 'Dashboard'
    },
    {
      routeLink: 'courses',
      icon: 'fal , fa-book',
      label: 'Courses'
    },
    {
      routeLink: 'create-course',
      icon: 'fal , fa-plus-circle',
      label: 'Create Course'
    },
    {
      routeLink: 'assignments',
      icon: 'fal , fa-tasks',
      label: 'Assignments'
    },
    {
      routeLink: 'grades',
      icon: 'fal , fa-clipboard-check',
      label: 'Grades'
    },
    {
      routeLink: 'messages',
      icon: 'fal , fa-envelope',
      label: 'Messages'
    },
    {
      routeLink: 'profile',
      icon: 'fal , fa-user',
      label: 'Profile'
    },
    // Add more navigation items as needed
  ],
  candidate: [
    {
      routeLink: 'dashboard',
      icon: 'fal , fa-home',
      label: 'Dashboard'
    },
    {
      routeLink: 'courses',
      icon: 'fal , fa-book',
      label: 'Courses'
    },
    {
      routeLink: 'assignments',
      icon: 'fal , fa-tasks',
      label: 'Assignments'
    },
    {
      routeLink: 'grades',
      icon: 'fal , fa-clipboard-check',
      label: 'Grades'
    },
    {
      routeLink: 'messages',
      icon: 'fal , fa-envelope',
      label: 'Messages'
    },
    {
      routeLink: 'profile',
      icon: 'fal , fa-user',
      label: 'Profile'
    },
  ]
};
