package com.flower.vikash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flower.vikash.dto.FlowerDTO;
import com.flower.vikash.dto.ResponseDto;
import com.flower.vikash.model.Flower;
import com.flower.vikash.services.FlowerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/flower")
public class FlowerController {

	@Autowired
	FlowerService flowerService;
	
	@PostMapping("/addFlower")
	public ResponseEntity<ResponseDto> addFlowerHandler(@RequestBody FlowerDTO flowerDto){
		ResponseDto response =  flowerService.addFlower(flowerDto);
//		ResponseDto response = new ResponseDto("Ok", "Test");
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Flower>> getAllFlowerHandler(){
		List<Flower> listOfFlower = flowerService.getAllFlower();
		return new ResponseEntity<>(listOfFlower,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{flowerId}")
	public ResponseEntity<ResponseDto> updateFlower(@PathVariable("flowerId") Integer flowerId, @RequestBody FlowerDTO updateFlower){
		ResponseDto response = flowerService.updateFlowerDetails(updateFlower,flowerId);
		
		return new ResponseEntity<ResponseDto>(response,HttpStatus.ACCEPTED);
	}
}
