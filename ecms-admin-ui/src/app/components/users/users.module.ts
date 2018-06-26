import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { CollapseModule } from 'ngx-bootstrap/collapse';

import { UsersComponent } from './users.component';
import { AddUsersComponent } from './add.users.component';
import { UsersRoutingModule } from './users-routing.module';

@NgModule({
  imports: [
    FormsModule,
    UsersRoutingModule,
    CommonModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    CollapseModule.forRoot()
  ],
  declarations: [ UsersComponent, AddUsersComponent ]
})
export class UsersModule { }
