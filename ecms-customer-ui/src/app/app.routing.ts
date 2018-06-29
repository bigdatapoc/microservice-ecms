import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';
import { LoginComponent } from './components/login/login.component';


export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'dashboard',
        loadChildren: './components/dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'contentTypes',
        loadChildren: './components/contentTypes/contentTypes.module#ContentTypesModule'
      },
      {
        path: 'contentConfig',
        loadChildren: './components/contentConfig/contentConfig.module#ContentConfigModule'
      },
      {
        path: 'contentCatalog',
        loadChildren: './components/contentCatalog/contentCatalog.module#ContentCatalogModule'
      },
      {
        path: 'users',
        loadChildren: './components/users/users.module#UsersModule'
      },
      {
        path: 'profile',
        loadChildren: './components/profile/profile.module#ProfileModule'
      },
      {
        path: 'storageConfig',
        loadChildren: './components/storageConfig/storageConfig.module#StorageConfigModule'
      },
      {
        path: 'notification',
        loadChildren: './components/notification/notification.module#NotificationModule'
      }
    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
