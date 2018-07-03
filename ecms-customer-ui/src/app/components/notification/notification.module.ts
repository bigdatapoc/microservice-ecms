import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { FroalaEditorModule, FroalaViewModule } from 'angular-froala-wysiwyg';


import { NotificationTemplatesComponent } from './notificationTemplates.component';
import { AddNotificationTemplateComponent } from './add.notificationTemplate.component';
import { NotificationEventsComponent } from './notificationEvents.component';
import { AddNotificationEventComponent } from './add.notificationEvent.component';
import { NotificationRulesComponent } from './notificationRules.component';
import { AddNotificationRuleComponent } from './add.notificationRule.component';

import { NotificationRoutingModule } from './notification-routing.module';

@NgModule({
  imports: [
    FormsModule,
    NotificationRoutingModule,
    BsDropdownModule,
    CommonModule,
    FroalaEditorModule.forRoot(), 
    FroalaViewModule.forRoot(),
    ButtonsModule.forRoot(),
    CollapseModule.forRoot()
  ],
  declarations: [ 
                  NotificationTemplatesComponent, 
                  AddNotificationTemplateComponent,
                  NotificationEventsComponent,
                  AddNotificationEventComponent,
                  NotificationRulesComponent,
                  AddNotificationRuleComponent
                ]
})
export class NotificationModule { }
