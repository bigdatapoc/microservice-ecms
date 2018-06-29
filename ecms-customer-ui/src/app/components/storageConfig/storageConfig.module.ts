import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { CollapseModule } from 'ngx-bootstrap/collapse';

import { StorageConfigComponent } from './storageConfig.component';
import { StorageConfigRoutingModule } from './storageConfig-routing.module';

@NgModule({
  imports: [
    FormsModule,
    StorageConfigRoutingModule,
    BsDropdownModule,
    CommonModule,
    ButtonsModule.forRoot(),
    CollapseModule.forRoot()
  ],
  declarations: [ StorageConfigComponent ]
})
export class StorageConfigModule { }
