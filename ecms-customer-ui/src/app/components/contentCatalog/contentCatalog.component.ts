import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Subscription } from "rxjs/Rx";
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';
import { DataService } from '../../services/data.service';

@Component({
  templateUrl: 'contentCatalog.component.html',
  styleUrls: ['contentCatalog.component.css']
})
export class ContentCatalogComponent implements OnDestroy, OnInit{

  public dtOptions: DataTables.Settings = {};
  public dtTrigger: Subject<any> = new Subject();
  public catalog = [];
  private sub: Subscription;
  
  constructor(private dataService: DataService,
                 private router: Router) { 

  }

  ngOnInit() {
    this.dtOptions = {
      pagingType: 'simple_numbers',
      pageLength: 10
    };

    this.getCatalogList();
    
  }

  getCatalogList() {

    this.sub = this.dataService.getAllContent().subscribe(resp => {
      if (resp['status'] && resp['status'] == 'SUCCESS') {
        this.catalog = resp['data'];
        this.dtTrigger.next();
      }
    });

  }

  viewContent(id) {
    let content = this.catalog.find(obj => obj.id == id);
    this.dataService.setLocalStorage('selectedContent', content);
    this.router.navigate(['/contentCatalog/view']);
  }

  deleteContent(id) {
    this.catalog = this.catalog.filter(obj => obj.id != id);
  }

  ngOnDestroy() {
    this.dtTrigger.unsubscribe();
    this.sub.unsubscribe();
  }

}
