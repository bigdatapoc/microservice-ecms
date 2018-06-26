import { NgModule } from '@angular/core';
import { Routes,
     RouterModule } from '@angular/router';

import { CustomersComponent } from './customers.component';
import { AddCustomersComponent } from './add.customers.component';

const routes: Routes = [
  {
    path: '',
    component: CustomersComponent,
    data: {
      title: 'Customers'
    }
  },
  {
    path: 'add',
    component: AddCustomersComponent,
    data: {
      title: 'Add Customer'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomersRoutingModule {}
