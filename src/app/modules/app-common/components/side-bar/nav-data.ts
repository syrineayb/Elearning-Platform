export interface NavItem {
  routeLink: string;
  icon: string;
  label: string;
}

export interface NavbarData {
  [key: string]: NavItem[]; // Index signature to allow any string key with value of NavItem[]
  instructor: NavItem[];
  candidate: NavItem[];
  admin: NavItem[];
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
      routeLink: '#',
      icon: 'fal , fa-home',
      label: 'Personal'
    },  {
      routeLink: '#',
      icon: 'fal , fa-home',
      label: 'Dashboard'
    },
    {
      routeLink: 'topics',
      icon: 'fal , fa-book',
      label: 'Topics'
    },
    {
      routeLink: 'profile',
      icon: 'fal , fa-user',
      label: 'Profile'
    },
    {
      routeLink: 'courses',
      icon: 'fal , fa-book',
      label: 'My Courses'
    },

  ],
  admin: [
    {
      routeLink: 'home',
      icon: 'fal , fa-home',
      label: 'Admin'
    },
    {
      routeLink: 'home',
      icon: 'fal , fa-home',
      label: 'Home'
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
