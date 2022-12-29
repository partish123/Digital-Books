package com.digitalbooks.controllers;

import com.digitalbooks.models.Book;
import com.digitalbooks.models.CreateBookRequest;
import com.digitalbooks.models.Reader;
import com.digitalbooks.models.UpdateBookRequest;
import com.digitalbooks.payload.response.MessageResponse;
import com.digitalbooks.services.UserService;
import com.digitalbooks.utility.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@PostMapping("/author/{authorId}/books")
	//	@PreAuthorize("hasRole('AUTHOR')")
	public ResponseEntity<?> createBook(@RequestBody CreateBookRequest book, @PathVariable String authorId) throws UserException {
		try {
			Object createBook = userService.createBook(book, authorId);
			if (createBook != null)
				return ResponseEntity.status(200).body(createBook);
			else
				throw new UserException("Book is not added--->");
		} catch (Exception e) {
			throw new UserException("Book is not added---> error", e);
		}

	}

	@PostMapping(value="/author/{authorId}/books/{bookId}")
	public ResponseEntity<?> updateBook(@RequestBody UpdateBookRequest book, @PathVariable String authorId, @PathVariable String bookId) throws UserException{

		try {
			Object updateBook= userService.updateBook(book,authorId,bookId);
			if (updateBook != null)
				return ResponseEntity.status(200).body(updateBook);
			else
				throw new UserException("Book not updated--->");

		}catch(Exception e) {
			throw new UserException("Book is not updated..got error--->",e);
		}
	}

	@GetMapping("/search")
	public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false ,defaultValue = "") String title, @RequestParam(required = false,defaultValue = "")
	String author, @RequestParam(defaultValue ="") String price, @RequestParam(required = false,defaultValue = "") String publisher,@RequestParam(name="category" ,defaultValue="") String category) throws UserException{
		List<Book> books=null;
		try {
			books= userService.searchBook(category,title,author,price,publisher);
			return ResponseEntity.status(200).body(books);

		}catch(Exception e) {
			throw new UserException("Search ERROR()--->",e);
		}
	}


	@PostMapping("/{bookId}/subscribe")
	public  ResponseEntity<String> subscribeBook(@RequestBody Reader reader, @PathVariable String bookId) throws UserException{
		try {
			boolean sub= userService.subscribeBook(bookId,reader);
			if(sub)
				return ResponseEntity.status(200).body("SUCCESSFULLY SUBSCRIBED");
			else
				throw new UserException("NOT SUBSCRIBED");

		}catch(Exception e) {
			throw new UserException("NOT SUBSCRIBED",e);
		}
	}


	@GetMapping("/readers/{emailId}/books")
	public ResponseEntity<?> getAllSubscribeBooksByReader(@PathVariable String emailId) throws UserException{
		if(emailId!=null&&!emailId.equalsIgnoreCase("")) {
			try {
				Object subscribBooksByReader=userService.getAllSubscribeBooksByReader(emailId);
				if(subscribBooksByReader!=null) {
					return ResponseEntity.status(200).body(subscribBooksByReader);
				}
				else{
					return ResponseEntity.status(200).body("No books are subscribed by reader");
				}
			}catch(Exception e) {
				throw new UserException("SOMETHING WENT WRONG PLEASE TRY AFTER SOME TIME");
			}
		}
		else {
			throw new UserException("DATA MISSING");
		}
	}


	@GetMapping("/readers/{emailId}/books/{subscriptionId}")
	public ResponseEntity<Book> getSubscribeBookByReaderEmailId(@PathVariable String emailId,@PathVariable String subscriptionId) throws UserException{
		if(emailId!=null&&!emailId.equalsIgnoreCase("")) {
			try {
				Book subscribBookByReader=userService.getSubscribeBookByReaderEmailId(emailId,subscriptionId);
				return ResponseEntity.status(200).body(subscribBookByReader);
			}catch(Exception e) {
				throw new UserException("SOMETHING WENT WRONG PLEASE TRY AFTER SOME TIME",e);
			}
		}
		else {
			throw new UserException("DATA_MISSING");
		}
	}


	@GetMapping("/readers/{emailId}/books/{subscriptionId}/read")
	public ResponseEntity<String> getSubscribeBookContentByReaderEmailId(@PathVariable String emailId,@PathVariable String subscriptionId) throws UserException{
		if(emailId!=null&&!emailId.equalsIgnoreCase("")) {
			try {
				String bookContent= userService.getSubscribeBookByReader(emailId,subscriptionId);
				return ResponseEntity.status(200).body(bookContent);
			}catch(Exception e) {
				throw new UserException("SOMETHING_WENT_WRONG_PLESE_TRY_AFTER_SOME_TIME");
			}
		}
		else {
			throw new UserException("DATA_MISSING");
		}
	}


	@PostMapping("/readers/{readerId}/books/{subscriptionId}/cancel-subscription")
	public ResponseEntity<String>cancleSubscriptionWithIn24Hours(@PathVariable String readerId,@PathVariable String subscriptionId ) throws UserException{
		if(readerId!=null &&!readerId.equalsIgnoreCase("") && subscriptionId!=null && !subscriptionId.equalsIgnoreCase("")) {
			boolean cancle=userService.cancleSubscriptionWithIn24Hours(readerId,subscriptionId);
			if(cancle) {
				return ResponseEntity.status(200).body("SUBSCRIPTION_CANCELED_SUCCESSFULLY");
			}
			else {
				throw new UserException("SOMETHING_WENT_WRONG_PLEASE_TRY_AFTER_SOME_TIME");
			}
		}
		else {
			return ResponseEntity.status(400).body("DATA_MISSING");
		}
	}

	@PostMapping("/author/{authorId}/books/{bookId}/block={block}")
	public ResponseEntity<?> blockOrUnBlockBookByAuthor(@PathVariable(value="authorId") String authorId,@PathVariable(value="bookId") String bookId,@PathVariable(value = "block") String block) throws UserException{
		if(block!=null&&!block.equalsIgnoreCase("")&&authorId!=null&&bookId!=null) {
			try {
				boolean update=userService.blockOrUnBlockBookByAuthor(authorId,bookId,block);
				if(update) {
					if(block.equalsIgnoreCase("Yes"))
						return ResponseEntity.status(200).body("blocked");
					else
						return ResponseEntity.status(200).body("unblocked");
				}
				else {
					throw new UserException("SOMETHING WENT WRONG PLEASE_TRY_AFTER_SOME_TIME");
				}
			}catch(Exception e) {
				throw new UserException("SOMETHING_WENT_WRONG_PLEASE_TRY_AFTER_SOME_TIME",e);
			}
		}
		else {
			return ResponseEntity.status(400).body("Data Missing!.. authorId:"+authorId+"bookId:"+bookId+"block:"+block);
		}
	}




}
