import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';
import { FileUploader, FileItem, ParsedResponseHeaders} from "ng2-file-upload";
import { DataService } from '../../services/data.service';
import  { Subscription } from "rxjs/Rx";
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';

const URL = environment.apiPath + '/api/';

@Component({
  templateUrl: 'add.contentCatalog.component.html',
  styleUrls: ['add.contentCatalog.component.css']
})
export class AddContentCatalogComponent implements OnInit{

  @ViewChild('fileInput') fileInput: ElementRef;

  constructor(private dataService: DataService,
              private router: Router) {
                  this.dataService.updateFileUploadStatus(false);   //reset file upload flag
    
        }

  public stepNum = 1;
  public selected = {
    config: '',
    contentType: ''
  };

  public formData = {
    key: null,
    title: null,
    description: null,
    tags: null
  };

  private showProgress: Boolean = false;
  private showSpinner: Boolean = false;
  public isFileUploaded: Boolean;
  private uploadFileKey: String;
  public uploader: FileUploader;
  private sub: Subscription;

  ngOnInit() {

    this.uploader = new FileUploader({ 
          url: URL, 
          autoUpload: true 
    });
    
    this.uploader.onErrorItem = (item, response, status, headers) => this.onErrorItem(item, response, status, headers);
    
    this.uploader.onSuccessItem = (item, response, status, headers) => this.onSuccessItem(item, response, status, headers);

    this.dataService.uploadObservable.subscribe((val) => {
      console.log('change detected', val);
      this.isFileUploaded = val;
    });

  }

  private onSuccessItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): any {
      let data = JSON.parse(response); //success server response
      this.uploadFileKey = data.response;
      this.dataService.updateFileUploadStatus(true);
  }

  private onErrorItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): any {
      let error = JSON.parse(response); //error server response
  }

  public triggerClick() {
    this.fileInput.nativeElement.click();
  }

  public showProgressBar() {
    this.showProgress = true;
  }

  public saveForm(isValid) {
    if (isValid) {
      this.showSpinner = true;
      this.formData['key'] = this.uploadFileKey;

      if (this.formData.tags.trim() != '') {
        this.formData.tags = this.formData.tags.split(',');
      }

      this.sub = this.dataService.saveContent(this.formData).subscribe(resp => {
        this.showSpinner = false;
        this.router.navigate(['/contentCatalog']);
      });
    }    
  }

  public cancel() {
    
    this.formData = {
      key: null,
      title: null,
      description: null,
      tags: []
    };

    this.showProgress = false;
  }

}
