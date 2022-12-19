import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-searchbook',
  templateUrl: './searchbook.component.html',
  styleUrls: ['./searchbook.component.css']
})
export class SearchbookComponent implements OnInit {

  isFound = false;
  booklist:any[] = [];

  book: any = {
    bookTitle: '',
    authorId: '',
    publisher: '',
    category: '',
    price: ''

  };

  flag = false;

  constructor(private userService: UserService, private snak: MatSnackBar) { }

  ngOnInit(): void {
  }

  doSubmitForm() {
    console.log("try to submit form");
    console.log("DATA ", this.book);

    if (this.book.bookTitle == '' || this.book.authorId == '' || this.book.category == '' || this.book.publisher == '' || this.book.bookTitle == null || this.book.authorId == null || this.book.category == null || this.book.publisher == null) {
      this.snak.open("Fields can not be empty or null !!", "OK");
      return;
    }

    this.flag = true;
    this.userService.searchBook(this.book).subscribe(
      response => {
        this.flag = false;
        this.isFound = true;
        this.booklist = response;
        this.snak.open("Search result found", "OK");
      },
      error => {
        console.log(error);
        this.flag = false;
        this.snak.open("ERROR!! ", "OK");
      }
    )

  }




}
