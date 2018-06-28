import { Component, OnInit } from '@angular/core';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';

@Component({
  templateUrl: 'profile.component.html'
})
export class ProfileComponent implements OnInit {

    formParts = { 
            generalIsCollapsed : true,
            accountIsCollapsed: false,
            portalIsCollapsed: false
        };

    ngOnInit(): void {
    
    }

}
