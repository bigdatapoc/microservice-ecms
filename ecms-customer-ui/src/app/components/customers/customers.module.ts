import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { CollapseModule } from 'ngx-bootstrap/collapse';

import { CustomersComponent } from './customers.component';
import { AddCustomersComponent } from './add.customers.component';
import { CustomersRoutingModule } from './customers-routing.module';

@NgModule({
  imports: [
    FormsModule,
    CustomersRoutingModule,
    BsDropdownModule,
    CommonModule,
    ButtonsModule.forRoot(),
    CollapseModule.forRoot()
  ],
  declarations: [ CustomersComponent, AddCustomersComponent ]
})
export class CustomersModule { }
