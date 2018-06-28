import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { CollapseModule } from 'ngx-bootstrap/collapse';

import { ContentCatalogComponent } from './contentCatalog.component';
import { AddContentCatalogComponent } from './add.contenCatalog.component';
import { ContentCatalogRoutingModule } from './contentCatalog-routing.module';

@NgModule({
  imports: [
    FormsModule,
    ContentCatalogRoutingModule,
    BsDropdownModule,
    CommonModule,
    ButtonsModule.forRoot(),
    CollapseModule.forRoot()
  ],
  declarations: [ ContentCatalogComponent, AddContentCatalogComponent ]
})
export class ContentCatalogModule { }
