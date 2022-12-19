import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-updatebook',
  templateUrl: './updatebook.component.html',
  styleUrls: ['./updatebook.component.css']
})
export class UpdatebookComponent implements OnInit {

  isUpdated = false;


  updatebook : any ={
    title : '',
    category : '',
    image : '',
    price : '',
    publisher : '',
    active : '',
    content : '',
    
  };

  bookID: string | null | undefined;

  constructor(private route:ActivatedRoute,private userService: UserService, private snak:MatSnackBar,private tokenStorage: TokenStorageService) { }

  

  flag= false;

  ngOnInit(): void {
    this.bookID = this.route.snapshot.paramMap.get('bookID');
  }

  doUpdateForm(){
  console.log("try to save form");
  console.log("DATA ",this.updatebook);

  if(this.updatebook.title=='' || this.updatebook.price==''|| this.updatebook.category=='' || this.updatebook.publisher=='' || this.updatebook.active=='' || this.updatebook.content=='')
  {
    this.snak.open("Fields can not be empty !!","OK");
    return;
  }

  this.flag=true;

  this.userService.updateBook(this.updatebook,this.bookID).subscribe(
    response=>{
      console.log(response);   
      this.flag=false; 
      this.isUpdated= true;
      this.snak.open("Updated book Successfully","OK")  
    },
    error=>{
      console.log(error); 
      this.flag=false;    
      this.snak.open("ERROR!! ","OK")   
    }
  )  
  
  }

}
