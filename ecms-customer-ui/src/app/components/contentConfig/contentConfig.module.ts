import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { DragulaModule } from 'ng2-dragula';

import { ContentConfigComponent } from './contentConfig.component';
import { AddContentConfigComponent } from './add.contentConfig.component';
import { ContentConfigRoutingModule } from './contentConfig-routing.module';

@NgModule({
  imports: [
    FormsModule,
    ContentConfigRoutingModule,
    BsDropdownModule,
    CommonModule,
    DragulaModule,
    ButtonsModule.forRoot(),
    CollapseModule.forRoot()
  ],
  declarations: [ ContentConfigComponent, AddContentConfigComponent ]
})
export class ContentConfigModule { }
