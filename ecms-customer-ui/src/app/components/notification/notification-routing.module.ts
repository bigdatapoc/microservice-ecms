import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NotificationTemplatesComponent } from './notificationTemplates.component';
import { AddNotificationTemplateComponent } from './add.notificationTemplate.component';
import { NotificationEventsComponent } from './notificationEvents.component';
import { AddNotificationEventComponent } from './add.notificationEvent.component';
import { NotificationRulesComponent } from './notificationRules.component';
import { AddNotificationRuleComponent } from './add.notificationRule.component';

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
      },
      {
        path: 'events',
        component: NotificationEventsComponent,
        data: {
          title: 'Events'
        }
      },
      {
        path: 'addEvent',
        component: AddNotificationEventComponent,
        data: {
          title: 'Add Event'
        }
      },
      {
        path: 'rules',
        component: NotificationRulesComponent,
        data: {
          title: 'Rules'
        }
      },
      {
        path: 'addRule',
        component: AddNotificationRuleComponent,
        data: {
          title: 'Add Rule'
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
