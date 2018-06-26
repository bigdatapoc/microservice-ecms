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
        path: 'customers',
        loadChildren: './components/customers/customers.module#CustomersModule'
      },
      {
        path: 'users',
        loadChildren: './components/users/users.module#UsersModule'
      },
      {
        path: 'contentTypes',
        loadChildren: './components/contentTypes/contentTypes.module#ContentTypesModule'
      }
    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
