import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ContentConfigComponent } from './contentConfig.component';
import { AddContentConfigComponent } from './add.contentConfig.component';

const routes: Routes = [
  {
    path: '',
    component: ContentConfigComponent,
    data: {
      title: 'Content Configuration'
    }
  },
  {
    path: 'add',
    component: AddContentConfigComponent,
    data: {
      title: 'Add Content Configuration'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContentConfigRoutingModule {}
