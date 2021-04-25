
import { HttpErrorResponse, HttpEvent, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FileHandlerService } from 'src/app/service/file-handler.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  public filenames: string[] = [];
  public fileStatus = {
    status: "",
    requestType: "",
    percent: 0
  };
  
  constructor(private fileHandlerService: FileHandlerService) {
    
  }
  
  ngOnInit(): void {
    
  }
  
  public onUploadFiles(files: File[]): void {
    
    const formData: FormData = new FormData();
    files.forEach(f => formData.append("files", f, f.name));
    this.fileHandlerService.upload(formData).subscribe(
      (response) => {
        console.log(response);
        this.reportProgress(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert(error.message);
      }
    );
  }
  
  private reportProgress(httpEvent: HttpEvent<string[] | Blob>): void {
    switch (httpEvent.type) {
      case HttpEventType.UploadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, "Uploading...");
        break;
      case HttpEventType.DownloadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, "Downloading...");
        break;
      case HttpEventType.ResponseHeader:
        console.log("header returned...", httpEvent);
        break;
      case HttpEventType.Response:
        if (httpEvent.body instanceof Array) {
          httpEvent.body.forEach(filename => this.filenames.unshift(filename));
        }
        else {
          saveAs(new File([httpEvent.body!], httpEvent.headers.get('File-Name')!, { type: httpEvent.headers.get('Content-Type') + ";charset=utf-8"} ));
        }
        break;
        default:
          console.log(httpEvent);
    }
  }
  
  private updateStatus(loaded: number, total: number, requestType: string) {
    this.fileStatus.status = "progress";
    this.fileStatus.requestType = requestType;
    this.fileStatus.percent = Math.round(100 * loaded / total);
  }
  
  public onDownloadFiles(filename: string): void {
    
    this.fileHandlerService.download(filename).subscribe(
      (response) => {
        console.log(response);
        this.reportProgress(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert(error.message);
      }
    );
  }
  
  
  
}
