package co.com.calculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/calculator")
public class CalculatorController {

	@GetMapping("/newSession")
	public ResponseEntity<String> newSession(){
		return new ResponseEntity<String>("New Session Dummy", HttpStatus.OK);
	}
	
	@GetMapping("/addValue/{value}")
	public ResponseEntity<String> addValue(@PathVariable double value){
		return new ResponseEntity<String>("Added value dummy", HttpStatus.OK);
	}
	
	@GetMapping("/executeOperation/{operation}")
	public ResponseEntity<String> addValue(@PathVariable String operation){
		return new ResponseEntity<String>("Executed operation dummy", HttpStatus.OK);
	}
}
