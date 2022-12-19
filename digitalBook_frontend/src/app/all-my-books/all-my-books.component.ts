import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-all-my-books',
  templateUrl: './all-my-books.component.html',
  styleUrls: ['./all-my-books.component.css']
})
export class AllMyBooksComponent implements OnInit {

  isPresent = false;
  bookList:any[] = [];

  book: any = {
    bookTitle: '',
    createdOn: '',
    publisher: '',
    price: '',
    active:'',
    id:''

  };


  constructor(private token:TokenStorageService,private userService: UserService,private snak: MatSnackBar,private router: Router) { }


  ngOnInit(): void {
    this.userService.getAuthorBooks(this.book).subscribe(
      response => {
        this.isPresent = true;
        this.bookList = response;
        this.snak.open("Books found", "OK");
      },
      error => {
        console.log(error);
        this.snak.open("No Books found!! ", "OK");
      }
    )
  }

  doUpdate(){
    this.router.navigate(['/author/books/update',this.book.id]);
    console.log("Updating book");
  }

  doBlock(){
    this.userService.getAuthorBooks(this.book).subscribe(
      response => {
        this.isPresent = true;
        this.bookList = response;
        this.snak.open("Book is blocked", "OK");
      },
      error => {
        console.log(error);
        this.snak.open("Unable to block Book!! ", "OK");
      }
    )
  }

  

}
