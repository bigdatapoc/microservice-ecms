import { Component, OnInit, OnDestroy, ElementRef, AfterViewInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import  { Subscription } from "rxjs/Rx";
import { ActivatedRoute } from '@angular/router';

declare const videojs: any;

@Component({
  templateUrl: 'view.contentCatalog.component.html',
  styleUrls: ['contentCatalog.component.css']
})
export class ViewContentCatalogComponent implements OnInit, OnDestroy, AfterViewInit{

  public content = null;
  private transcodeSub: Subscription;
  public conversionType = 'HLS';
  public transcodeStatus = 'not-started';
  public publishStatus = 'not-started';
  private sub: Subscription;
  private contentSub: Subscription;
  public id: number;
  private _elementRef: ElementRef;
  private player: any; 
  public resolutionArr = [{
    label: '240',
    res: 240,
    postFix: '352x240'
  },
  {
    label: '1080',
    res: 1080,
    postFix: '1920x1080'
  },
  {
    label: 'default',
    res: 'deafult',
    postFix: ''
  }];

  constructor(private dataService: DataService,
                private route: ActivatedRoute,
                private elementRef: ElementRef) { 
      this.player = false;
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.getSelectedContent(this.id);
    });  
  }


  ngAfterViewInit() {
    
    if (this.content != null && this.content['fileStatus'] == 'PUBLISHED') {
      this.initPlayer();
    }

  }


  initPlayer() {

    this.player = videojs(document.getElementById('video_player'), {
      plugins: {
        videoJsResolutionSwitcher: {
          default: 'low',
          dynamicLabel: true
        }
      }
    });

    let videoArr = [];
    let pubUrlArr = this.content.publishURL.split('.');
    let pubExt = pubUrlArr.pop();
    let pubUrl = pubUrlArr.join('.');

    this.resolutionArr.forEach((val) => {
      let videObj = {
        src: pubUrl + val.postFix + '.' + pubExt,
        type: 'application/x-mpegURL',
        res: val.res,
        label: val.label
      }

      videoArr.push(videObj);
    });

    this.player.updateSrc(videoArr);
  }

  getSelectedContent(id) {
    
    this.content = {
		  "id": 8,
		  "title": "TEST",
		  "description": "TEST",
		  "tags": null,
		  "fileKey": "9264aa53-bba1-4fea-8119-d7747300f3df_HCL_TEST.mp4",
		  "fileSize": 383631,
		  "fileContentType": "video/mp4",
		  "ingestionFileLocation": "ott-ingestion-bucket/video/9264aa53-bba1-4fea-8119-d7747300f3df_HCL_TEST.mp4",
		  "ingestionURL": "https://s3.ap-south-1.amazonaws.com/ott-ingestion-bucket/video/9264aa53-bba1-4fea-8119-d7747300f3df_HCL_TEST.mp4",
		  "processFileLocation": "ott-ingestion-bucket/video/9264aa53-bba1-4fea-8119-d7747300f3df_HCL_TEST.mp4",
		  "processURL": null,
		  "publishFileLocation": null,
		  "publishURL": "https://d3njk02ga9lv3t.cloudfront.net/video/d7b38a21-5069-4f07-af9a-26866e53419f_HCL_TEST/HLS/d7b38a21-5069-4f07-af9a-26866e53419f_HCL_TEST.m3u8",
		  "fileStatus": "PUBLISHED"
    }
    
    if (this.content != null && this.content['fileStatus'] == 'PUBLISHED' && this.publishStatus == 'success') {
      this.initPlayer();
    }

    // this.contentSub = this.dataService.getContentById(id).subscribe(resp => {
    //   this.content = resp['data'];
    // });
  }

  transcode() {

    this.transcodeStatus = 'in-progress';
    let transcodeObj = {
      metaDataId: this.content['id'],
      fileLocation: this.content['ingestionFileLocation'],
      fileConversionType: this.conversionType
    }

    this.transcodeSub = this.dataService.transcode(transcodeObj).subscribe(resp => {
      
      if (resp.status == 'SUCCESS') {
        this.getSelectedContent(this.id);
        this.transcodeStatus = 'success';
      } else {
        this.transcodeStatus = 'error';
      }
      
    });
  }

  publish() {

    this.publishStatus = 'in-progress';
    
    let publishObj = {
      metaDataId: this.content['id'],
      fileLocation: this.content['processFileLocation'],
      processFormat: this.content['fileContentType']
    }

    this.transcodeSub = this.dataService.publishContent(publishObj).subscribe(resp => {
      
      if (resp.status == 'SUCCESS') {
        this.publishStatus = 'success';
        this.getSelectedContent(this.id);
      } else {
        this.publishStatus = 'error';
      }
      
    });
  }


  ngOnDestroy() {
    this.sub.unsubscribe();
    //this.contentSub.unsubscribe();

    if (this.transcodeStatus != 'not-started' || this.publishStatus != 'not-started') {
      this.transcodeSub.unsubscribe();
    }

    if (this.content != null && this.content['fileStatus'] == 'PUBLISHED') {
      this.player.dispose();
    }

  }

}
