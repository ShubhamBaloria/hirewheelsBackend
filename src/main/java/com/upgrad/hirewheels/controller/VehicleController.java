package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/hirewheels/v1")
public class VehicleController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    VehicleService vehicleService;

    @GetMapping(value = "/vehicles")
    public ResponseEntity getVehicle(){
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = vehicleService.getAllVehicles();

        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for(Vehicle vehicle : vehicleList){
            vehicleDTOList.add(modelMapper.map(vehicle, VehicleDTO.class));
        }
        return new ResponseEntity(vehicleDTOList, HttpStatus.OK);

    }
}
