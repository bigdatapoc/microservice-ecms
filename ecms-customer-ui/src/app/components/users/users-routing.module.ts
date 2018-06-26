import { NgModule } from '@angular/core';
import { Routes,
     RouterModule } from '@angular/router';

import { UsersComponent } from './users.component';
import { AddUsersComponent } from './add.users.component';

const routes: Routes = [
  {
    path: '',
    component: UsersComponent,
    data: {
      title: 'Users'
    }
  },
  {
    path: 'add',
    component: AddUsersComponent,
    data: {
      title: 'Add User'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule {}
