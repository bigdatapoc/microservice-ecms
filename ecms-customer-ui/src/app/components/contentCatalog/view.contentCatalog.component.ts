import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: 'view.contentCatalog.component.html',
  styleUrls: ['contentCatalog.component.css']
})
export class ViewContentCatalogComponent implements OnInit{

  public content = {};

  constructor(private dataService: DataService) { 

  }

  ngOnInit() {
    this.getSelectedContent();
  }

  getSelectedContent() {
    this.content = this.dataService.getLocalStorage('selectedContent');
  }

}
