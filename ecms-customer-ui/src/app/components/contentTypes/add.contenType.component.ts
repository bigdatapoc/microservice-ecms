import { Component, OnInit } from '@angular/core';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';

@Component({
  templateUrl: 'add.contentType.component.html',
  styles: ['.delete-attr { margin-top:30px; }']
})
export class AddContentTypeComponent implements OnInit {

    attrs = [{
      name: '',
      type: ''
    }]

    ngOnInit(): void {
    
    }


    addMore() {
      this.attrs.push({
        name: '',
        type: ''
      });
    }

    deleteAttr(i) {
      this.attrs.splice(i, 1);
    }

}
