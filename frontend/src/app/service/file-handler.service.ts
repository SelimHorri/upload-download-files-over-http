
import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FileHandlerService {
  
  private apiUrl: string = environment.apiUrl;
  
  constructor(private http: HttpClient) {
    
  }
  
  public upload(formData: FormData): Observable<HttpEvent<string[]>> {
    return this.http.post<string[]>(this.apiUrl + "/files/upload", formData, {
      reportProgress: true,
      observe: "events"
    });
  }
  
  public download(filename: string): Observable<HttpEvent<Blob>> {
    return this.http.get(this.apiUrl + "/files/download" + "/" + filename, {
      reportProgress: true,
      observe: "events",
      responseType: "blob"
    });
  }
  
  
  
}





