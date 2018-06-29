import { NgModule } from '@angular/core';
import { Routes,
     RouterModule } from '@angular/router';

import { StorageConfigComponent } from './storageConfig.component';

const routes: Routes = [
  {
    path: '',
    component: StorageConfigComponent,
    data: {
      title: 'Storage Configuration'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StorageConfigRoutingModule {}
