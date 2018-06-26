import { Component, OnInit } from '@angular/core';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';

@Component({
  templateUrl: 'add.users.component.html'
})
export class AddUsersComponent implements OnInit {

    formParts = { 
            generalIsCollapsed : true,
            accountIsCollapsed: false 
        };

    ngOnInit(): void {
    
    }

}
