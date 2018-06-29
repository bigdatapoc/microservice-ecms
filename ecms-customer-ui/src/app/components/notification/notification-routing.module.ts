import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NotificationTemplatesComponent } from './notificationTemplates.component';
import { AddNotificationTemplateComponent } from './add.notificationTemplate.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Notification'
    },  
    children: [
      {
        path: 'templates',
        component: NotificationTemplatesComponent,
        data: {
          title: 'Templates'
        }
      },
      {
        path: 'addTemplate',
        component: AddNotificationTemplateComponent,
        data: {
          title: 'Add Template'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NotificationRoutingModule {}
