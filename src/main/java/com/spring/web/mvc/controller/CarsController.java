package com.spring.web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.mvc.controller.model.ApplicationResponse;
import com.spring.web.mvc.controller.model.Car;
import com.spring.web.mvc.service.CarService;
import com.spring.web.mvc.service.IVendorService;

@Controller
public class CarsController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private IVendorService vendorService;
	
	
	@GetMapping("/editCar")
	public String editCar(@RequestParam("cid") int cid,Model model){
		//String email=request.getParameter("email");
		Car car=carService.findCarByCid(cid);
		//Here we are mapping car object with carForm
		model.addAttribute("carForm", car);
		return "editCar"; //welcome.jsp
	}
	
	@GetMapping("/jdeleteCar")
	@ResponseBody
	public ApplicationResponse jdeleteCar(@RequestParam("cid") int cid,Model model){
		//String email=request.getParameter("email");
		String status=carService.deleteCarByCid(cid);
		//Show remaining data now
		///List<Car> cars=carService.getCars();
		//model.addAttribute("cars",cars);
		//return "redirect:/show-data"; //welcome.jsp
		ApplicationResponse applicationResponse=new ApplicationResponse();
		applicationResponse.setMessage("Hey record is deleted with cid = "+cid);
		applicationResponse.setStatus(status);
		return applicationResponse;
	}
	
	@GetMapping("/deleteCar")
	public String deleteCar(@RequestParam("cid") int cid,Model model){
		//String email=request.getParameter("email");
		carService.deleteCarByCid(cid);
		//Show remaining data now
		///List<Car> cars=carService.getCars();
		//model.addAttribute("cars",cars);
		return "redirect:/show-data"; //welcome.jsp
	}
	
	@GetMapping({"/show-data","/"})//alias
	public String showData(Model model){
		List<Car> cars=carService.getCars();
		model.addAttribute("cars",cars);
		return "showCars"; //welcome.jsp
	}
	
	@GetMapping("/add-car")
	public String showCarPage(){
		return "addCar"; // /WEB-INF/jsps/addCar.jsp
	}
	
	@PostMapping("/edit-car")
	@ResponseBody public ApplicationResponse editCar(@ModelAttribute Car car){
		System.out.println("____updating car data");
		System.out.println("controller - car: " + car);
		//We will code to update car into the database
		String status=carService.updateCar(car);
		ApplicationResponse applicationResponse=new ApplicationResponse();
		applicationResponse.setMessage("Hey record is updated successfully with cid = "+car.getCid());
		applicationResponse.setStatus(status);
		return applicationResponse;
	}
	
	@PostMapping("/add-car")
	public String addCar(@ModelAttribute Car car,Model model){
		/*String name=request.getParameter("name");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String mobile=request.getParameter("mobile");
		String city=request.getParameter("city");
		String photo=request.getParameter("photo");*/
		//Car car=new Car(name, email, gender, city, mobile,photo);
		model.addAttribute("car",car);
		return "reviewCar"; //welcome.jsp
	}
	
	
	@PostMapping("/save-review-car")
	public String saveReviewCar(@ModelAttribute Car car,Model model){
		//String name=request.getParameter("name");
		//String email=request.getParameter("email");
		//String gender=request.getParameter("gender");
		///String mobile=request.getParameter("mobile");
		//String city=request.getParameter("city");
		//String photo=request.getParameter("photo");
		//Car car=new Car(name, email, gender, city, mobile,photo);
		//Car car = new Car(name, email, gender, city, mobile,photo);
		///Here we have to write code to save data into database
		carService.save(car);
		//request.setAttribute("car",car);
		List<Car> cars=carService.getCars();
		model.addAttribute("cars",cars);
		return "showCars"; //welcome.jsp
	}
	
	@ModelAttribute("vendors") // all the return vendors are added inside request scope with this key = "vendors"
	public List<String> loadVendors(){
		List<String> pvendors=vendorService.findVendors();
		return pvendors;
	}
}
