import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ContentCatalogComponent } from './contentCatalog.component';
import { AddContentCatalogComponent } from './add.contenCatalog.component';

const routes: Routes = [
  {
    path: '',
    component: ContentCatalogComponent,
    data: {
      title: 'Content Catalog'
    }
  },
  {
    path: 'add',
    component: AddContentCatalogComponent,
    data: {
      title: 'Add Content'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContentCatalogRoutingModule {}
