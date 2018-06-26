import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { getStyle, hexToRgba } from '@coreui/coreui/dist/js/coreui-utilities';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';

@Component({
  templateUrl: 'users.component.html'
})
export class UsersComponent implements OnInit {

  ngOnInit(): void {
   
  }

  radioModel: string = 'Month';
}
