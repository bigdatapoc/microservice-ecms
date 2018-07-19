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
    title: 'Video 1',
    imgUrl: '../../../assets/img/video-file-icon.jfif'
  },
  {
    id: 2,
    title: 'Video 2',
    imgUrl: '../../../assets/img/video-file-icon.jfif'
  },
  {
    id: 3,
    title: 'Video 3',
    imgUrl: '../../../assets/img/video-file-icon.jfif'
  },
  {
    id: 4,
    title: 'Video 4',
    imgUrl: '../../../assets/img/video-file-icon.jfif'
  },
  {
    id: 5,
    title: 'Video 5',
    imgUrl: '../../../assets/img/video-file-icon.jfif'
  },
  {
    id: 6,
    title: 'Video 6',
    imgUrl: '../../../assets/img/video-file-icon.jfif'
  },
  {
    id: 7,
    title: 'Video 7',
    imgUrl: '../../../assets/img/video-file-icon.jfif'
  },
  {
    id: 8,
    title: 'Video 8',
    imgUrl: '../../../assets/img/video-file-icon.jfif'
  }];

}
