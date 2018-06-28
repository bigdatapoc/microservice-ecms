import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { CollapseModule } from 'ngx-bootstrap/collapse';

import { ProfileComponent } from './profile.component';
import { ProfileRoutingModule } from './profile-routing.module';

@NgModule({
  imports: [
    FormsModule,
    ProfileRoutingModule,
    BsDropdownModule,
    CommonModule,
    ButtonsModule.forRoot(),
    CollapseModule.forRoot()
  ],
  declarations: [ ProfileComponent ]
})
export class ProfileModule { }
