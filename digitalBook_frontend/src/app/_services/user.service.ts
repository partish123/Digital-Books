import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

const API_URL:string = 'http://localhost:8081/api/user/';



@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient, private token: TokenStorageService) { }

  authorID: any = this.token.getUser().id;

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }


  searchBook(book:any): Observable<any>{
     return this.http.get(`${API_URL}search?title=${book.bookTitle}&category=${book.category}&author=${book.authorId}&price=${book.price}&publisher=${book.publisher}`);
  }

  createBook(createBook:any): Observable<any>{
     return this.http.post(`${API_URL}author/${this.token.getUser().id}/books`,createBook,{ responseType: 'text' });
  }

  updateBook(updatebook:any,bookID:any): Observable<any>{
    return this.http.post(`${API_URL}author/${this.token.getUser().id}/books/${bookID}`,updatebook,{ responseType: 'text' });
 }

  getAuthorBooks(book:any): Observable<any>{
    return this.http.get(`${API_URL}search?author=${this.authorID}`);
  }







}
