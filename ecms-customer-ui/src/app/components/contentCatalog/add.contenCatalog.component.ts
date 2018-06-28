import { Component } from '@angular/core';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';

@Component({
  templateUrl: 'add.contentCatalog.component.html',
  styles: [`
        ul.steps { margin: 0; padding: 0; }
        ul.steps li{ display: inline; }
        ul.steps li.done a{ color: #20a8d8; }
        ul.steps li a { cursor: pointer; }
  `]
})
export class AddContentCatalogComponent {

  stepNum = 1;
  selected = {
    config: '',
    contentType: ''
  };
   

}
