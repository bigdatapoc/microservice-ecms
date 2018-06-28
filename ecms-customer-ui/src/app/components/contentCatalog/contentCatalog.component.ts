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
    title: 'Movies',
    imgUrl: '../../../assets/img/video-icon.png'
  },
  {
    id: 2,
    title: 'Videos',
    imgUrl: '../../../assets/img/video-icon.png'
  },
  {
    id: 3,
    title: 'Audio',
    imgUrl: '../../../assets/img/audio-icon.png'
  },
  {
    id: 4,
    title: 'Test',
    imgUrl: '../../../assets/img/video-icon.png'
  },
  {
    id: 5,
    title: 'Videos',
    imgUrl: '../../../assets/img/video-icon.png'
  },
  {
    id: 6,
    title: 'Audio',
    imgUrl: '../../../assets/img/audio-icon.png'
  },
  {
    id: 7,
    title: 'Videos',
    imgUrl: '../../../assets/img/video-icon.png'
  },
  {
    id: 8,
    title: 'Audio',
    imgUrl: '../../../assets/img/audio-icon.png'
  },
  {
    id: 9,
    title: 'Audio',
    imgUrl: '../../../assets/img/audio-icon.png'
  },
  {
    id: 10,
    title: 'Test',
    imgUrl: '../../../assets/img/video-icon.png'
  },
  {
    id: 11,
    title: 'Videos',
    imgUrl: '../../../assets/img/video-icon.png'
  },
  {
    id: 12,
    title: 'Audio',
    imgUrl: '../../../assets/img/audio-icon.png'
  },
  {
    id: 13,
    title: 'Videos',
    imgUrl: '../../../assets/img/video-icon.png'
  },
  {
    id: 8,
    title: 'Audio',
    imgUrl: '../../../assets/img/audio-icon.png'
  }];

}
