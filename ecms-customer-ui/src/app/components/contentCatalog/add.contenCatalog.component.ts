import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips';
import { FileUploader, FileItem, ParsedResponseHeaders} from "ng2-file-upload";
import { DataService } from '../../services/data.service';
import  { Subscription } from "rxjs/Rx";
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';

const URL = environment.apiPath + '/ingestion';

@Component({
  templateUrl: 'add.contentCatalog.component.html',
  styleUrls: ['contentCatalog.component.css']
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
  public uploadType = 'local';

  public formData = {
    key: null,
    title: null,
    description: null,
    tags: null
  };

  public ftpFormData = {
    host: null,
    port: null,
    user: null,
    password: null,
    path: null
  };

  private showProgress: Boolean = false;
  private showSpinner: Boolean = false;
  public isFileUploaded: Boolean;
  private uploadFileData;
  public uploader: FileUploader;
  private sub: Subscription;
  private ftpSub: Subscription;

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
      this.uploadFileData = data.data;
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

  uploadFileFromFTP() {
    this.showProgressBar();
    this.uploader.progress = 100;
	this.ftpFormData['remoteFile'] = this.ftpFormData.path; 
	delete this.ftpFormData.path;
    this.ftpSub = this.dataService.saveFtpContent(this.ftpFormData).subscribe(resp => {
      this.uploadFileData = resp.data;
      this.dataService.updateFileUploadStatus(true);
    });
  }

  public saveForm(isValid) {
    if (isValid && this.uploadFileData != undefined) {
      this.showSpinner = true;
	  
      let formData = Object.assign({}, this.uploadFileData, this.formData);
	 
      this.formData = formData;

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
