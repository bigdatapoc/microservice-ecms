import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ContentTypesComponent } from './contentTypes.component';
import { AddContentTypeComponent } from './add.contenType.component';

const routes: Routes = [
  {
    path: '',
    component: ContentTypesComponent,
    data: {
      title: 'Content Types'
    }
  },
  {
    path: 'add',
    component: AddContentTypeComponent,
    data: {
      title: 'Add Content Type'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContentTypesRoutingModule {}
