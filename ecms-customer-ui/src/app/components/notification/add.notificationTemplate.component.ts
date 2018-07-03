import { Component, OnInit } from '@angular/core';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';

@Component({
  templateUrl: 'add.notificationTemplate.component.html'
})
export class AddNotificationTemplateComponent {

  public options: Object = {
    placeholderText: 'Enter template content here.',
    height: 200
  }

}
