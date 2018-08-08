import { Component, OnInit, OnDestroy } from '@angular/core';
import { DataService } from '../../services/data.service';
import  { Subscription } from "rxjs/Rx";
import { ActivatedRoute } from '@angular/router';

@Component({
  templateUrl: 'view.contentCatalog.component.html',
  styleUrls: ['contentCatalog.component.css']
})
export class ViewContentCatalogComponent implements OnInit, OnDestroy{

  public content = {};
  private transcodeSub: Subscription;
  public conversionType = 'HLS';
  public transcodeStatus = 'not-started';
  private sub: Subscription;
  private contentSub: Subscription;
  public id: number;

  constructor(private dataService: DataService,
                private route: ActivatedRoute) { 

  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.getSelectedContent(this.id);
    });  
  }

  getSelectedContent(id) {
    this.contentSub = this.dataService.getContentById(id).subscribe(resp => {
      this.content = resp['data'];
    });
  }

  transcode() {

    this.transcodeStatus = 'in-progress';
    let transcodeObj = {
      metaDataId: this.content['id'],
      fileLocation: this.content['ingestionFileLocation'],
      fileConversionType: this.conversionType
    }

    this.transcodeSub = this.dataService.transcode(transcodeObj).subscribe(resp => {
      
      if (resp.status == 'Success') {
        this.getSelectedContent(this.id);
        this.transcodeStatus = 'success';
      } else {
        this.transcodeStatus = 'error';
      }
      
    });
  }


  ngOnDestroy() {
    this.sub.unsubscribe();
    this.contentSub.unsubscribe();

    if (this.transcodeStatus != 'not-started') {
      this.transcodeSub.unsubscribe();
    }
  }

}
