import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { CollapseModule } from 'ngx-bootstrap/collapse';

import { ContentTypesComponent } from './contentTypes.component';
import { AddContentTypeComponent } from './add.contenType.component';
import { ContentTypesRoutingModule } from './contentTypes-routing.module';

@NgModule({
  imports: [
    FormsModule,
    ContentTypesRoutingModule,
    BsDropdownModule,
    CommonModule,
    ButtonsModule.forRoot(),
    CollapseModule.forRoot()
  ],
  declarations: [ ContentTypesComponent, AddContentTypeComponent ]
})
export class ContentTypesModule { }
