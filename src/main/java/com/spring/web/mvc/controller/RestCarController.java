package com.spring.web.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.mvc.controller.model.ApplicationResponse;
import com.spring.web.mvc.controller.model.Car;
import com.spring.web.mvc.service.CarService;

@RestController
public class RestCarController {

	@Autowired
	private CarService carService;
	
	/**
	 * 
	 * @return
	 * @RequestBody - is reading json data from body of incoming request and converting into Java Object
	 * using jackson mapper framework!
	 */

	@PostMapping("/cars")
	public ApplicationResponse uploadCar(@RequestBody Car car){
		System.out.println(car);
		ApplicationResponse applicationResponse=new  ApplicationResponse();
		applicationResponse.setMessage("Hey! your data is received here.............");
		applicationResponse.setStatus("succes");
		applicationResponse.setPath("coming soon!");
		return applicationResponse;
	}

	/**
	 * Below method will return json response 
	 * of all the cars available into our database
	 * @param model
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/cars")//alias
	public List<Car> showData(){
		List<Car> cars=new ArrayList<Car>();
		cars=carService.getCars();
		return cars;
	}

	/**
	 * Below method will return json response 
	 * of all the cars available into our database
	 * @param model
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path="/cars",params ={"search"})//alias
	public List<Car> searchCars(@RequestParam(value="search",required=false) String search){
		List<Car> cars=new ArrayList<Car>();
			cars=carService.getCars();
		return cars;
	}

	/**
	 * Below method will return json response 
	 * of all the cars available into our database
	 * @param model
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path="/cars",params ={"sort"})//alias
	public List<Car> sortCars(@RequestParam(value="sort",required=false) String sort){
		List<Car> cars=new ArrayList<Car>();
			cars=carService.getCars();
		return cars;
	}

	/**
	 * Below method will return json response 
	 * of all the cars available into our database
	 * @param model
	 * @return
	 */
	//	companies?page=23
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path="/cars",params ={"page"})//alias
	public List<Car> findCarsByPage(@RequestParam(value="page",required=false) int page){
		List<Car> cars=new ArrayList<Car>();
			cars=carService.getCars();
			return cars;
	}


	@PutMapping("/cars")
	public ApplicationResponse iuploadCar(@RequestBody Car car){
		System.out.println(car);
		//write code to update
		ApplicationResponse applicationResponse=new  ApplicationResponse();
		applicationResponse.setMessage("Hey! your data is received here.............");
		applicationResponse.setStatus("succes");
		applicationResponse.setPath("coming soon!");
		return applicationResponse;
	}


	/**
	 * Below method will return json response 
	 * of all the cars available into our database
	 * @param model
	 * @return
	 */
	//below is an example of sending data as part of URI
	//http://localhost:8080/cars/90292
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/cars/{cid}")//alias
	public ApplicationResponse deleteCarByCid(@PathVariable("cid") int pcid){
		carService.deleteCarByCid(pcid);
		ApplicationResponse applicationResponse=new  ApplicationResponse();
		applicationResponse.setMessage("Hey! your data is delete here.............");
		applicationResponse.setStatus("succes");
		applicationResponse.setPath("coming soon!");
		return applicationResponse;
	}
}
