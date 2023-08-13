package com.flower.vikash.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.vikash.dto.FlowerDTO;
import com.flower.vikash.dto.ResponseDto;
import com.flower.vikash.enums.ResponseStatus;
import com.flower.vikash.exception.FlowerException;
import com.flower.vikash.model.Flower;
import com.flower.vikash.repository.FlowerRepository;

@Service
public class FlowerService {

	@Autowired
	FlowerRepository flowerRepo;
	
	public ResponseDto addFlower(FlowerDTO flowerDto) throws FlowerException{
		if(flowerRepo.findByName(flowerDto.getName())!=null) {
			throw new FlowerException(flowerDto.getName()+" is already present in database");
		}
		Flower flower = new Flower(flowerDto.getName(),flowerDto.getPrice(),flowerDto.getDescription(),flowerDto.getImageUrl(),flowerDto.getQuantity()); 
		Flower addedFlower = flowerRepo.save(flower);
		
		ResponseDto response = new ResponseDto();
		response.setMessage("Flower added successfully");
		response.setStatus(ResponseStatus.success.toString());
		return response;
	}

	public List<Flower> getAllFlower() throws FlowerException{
		List<Flower> listOfFlower =  flowerRepo.findAll();
		if(listOfFlower.size()==0) {
			throw new FlowerException("There is no flower added");
		}
		return listOfFlower;
	}

	public ResponseDto updateFlowerDetails(FlowerDTO updateFlower,Integer flowerId) throws FlowerException {
		// TODO Auto-generated method stub
		Optional<Flower> opt = flowerRepo.findById(flowerId);
		if(opt.get()==null) {
			throw new FlowerException(updateFlower.getName()+" doesnt exist");
		}
		Flower updatedFLower  = opt.get();
		updatedFLower.setName(updateFlower.getName());
		updatedFLower.setPrice(updateFlower.getPrice());
		updatedFLower.setDescription(updateFlower.getDescription());
		updatedFLower.setImageUrl(updateFlower.getImageUrl());
		updatedFLower.setQuantity(updateFlower.getQuantity());
		
		flowerRepo.save(updatedFLower);
		
		ResponseDto response = new ResponseDto();
		response.setMessage("Flower Updated SuccessFully");
		response.setStatus(ResponseStatus.success.toString());
		return response;
	}
	
	public Flower getFlowerByFlowerId(Integer flowerId) {
		Optional<Flower> flower = flowerRepo.findById(flowerId);
		if(flower.isEmpty()) throw new FlowerException("No flower of id "+flowerId);
		return flower.get();
	}

}
