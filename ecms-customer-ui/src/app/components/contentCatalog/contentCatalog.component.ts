import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';

@Component({
  templateUrl: 'contentCatalog.component.html',
  styles: [`
        ul.filters { padding: 0; }  
        ul.filters li { display: inline; padding: 0 5px; }
        ul.filters li { border-right: 1px solid #888; }
        ul.filters li:last-child { border-right: none; }
        ul.filters li.active { color: #20a8d8; }
        ul.filters li a { cursor: pointer; }
  `]
})
export class ContentCatalogComponent {

  showAdvanceSearch = false;
  displayType = 'all';
  catalog = [{
    id: 1,
    title: 'doc 1',
    imgUrl: '../../../assets/img/pdf-icon.jfif'
  },
  {
    id: 2,
    title: 'Document',
    imgUrl: '../../../assets/img/word-icon.png'
  },
  {
    id: 3,
    title: 'Sheet_1',
    imgUrl: '../../../assets/img/excel-icon.png'
  },
  {
    id: 4,
    title: 'files',
    imgUrl: '../../../assets/img/zip-icon.jfif'
  },
  {
    id: 5,
    title: 'Videos',
    imgUrl: '../../../assets/img/video-file-icon.jfif'
  },
  {
    id: 6,
    title: 'Audio',
    imgUrl: '../../../assets/img/audio-file-icon.jfif'
  },
  {
    id: 7,
    title: 'PPT Doc',
    imgUrl: '../../../assets/img/ppt-file-icon.jfif'
  },
  {
    id: 8,
    title: 'Files',
    imgUrl: '../../../assets/img/other-files.jfif'
  }];

}
