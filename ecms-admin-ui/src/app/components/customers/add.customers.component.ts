import { Component, OnInit } from '@angular/core';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';

@Component({
  templateUrl: 'add.customers.component.html'
})
export class AddCustomersComponent implements OnInit {

    formParts = { 
            generalIsCollapsed : true,
            accountIsCollapsed: false,
            portalIsCollapsed: false
        };

    ngOnInit(): void {
    
    }

}