export const navItems = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer'
  }/*,
  {
    name: 'Content Types',
    url: '/contentTypes',
    icon: 'icon-list'
  },
  {
    name: 'Content Config.',
    url: '/contentConfig',
    icon: 'icon-docs'
  }*/,
  {
    name: 'Content Catalog',
    url: '/contentCatalog/list',
    icon: 'icon-layers',
    children: [
      {
        name: 'List Content',
        url: '/contentCatalog/list',
        icon: 'icon-list'
      },
      {
        name: 'Add Content',
        url: '/contentCatalog/add',
        icon: 'icon-plus'
      }
    ]
  }/*,
  {
    name: 'Users',
    url: '/users',
    icon: 'icon-people'
  },
  {
    name: 'Storage Config.',
    url: '/storageConfig',
    icon: 'icon-social-dropbox'
  },
  {
    name: 'Notification',
    url: '/notification',
    icon: 'icon-speech',
    children: [
      {
        name: 'Templates',
        url: '/notification/templates',
        icon: 'icon-doc'
      },
      {
        name: 'Events',
        url: '/notification/events',
        icon: 'icon-list'
      },
      {
        name: 'Rules',
        url: '/notification/rules',
        icon: 'icon-shuffle'
      }
    ]
  }*/
];
