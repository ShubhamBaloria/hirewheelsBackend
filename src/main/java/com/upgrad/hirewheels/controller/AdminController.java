package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.AdminService;
import com.upgrad.hirewheels.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hirewheels/v1")
public class AdminController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AdminService adminService ;

    @PostMapping(value = "/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addVehicle(@RequestBody VehicleDTO vehicleDTO) throws Exception {
        Vehicle vehicle =modelMapper.map(vehicleDTO, Vehicle.class);
        Vehicle savedVehicle = adminService.registerVehicle(vehicle);

        VehicleDTO vehicleDTO1 =modelMapper.map(savedVehicle, VehicleDTO.class);
        return new ResponseEntity(vehicleDTO1, HttpStatus.ACCEPTED);
    }
    @PutMapping(value = "/vehicles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity UpdateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable(name = "id")int id){
    Vehicle vehicle = adminService.changeAvailability(id,vehicleDTO.getAvailabilityStatus());
VehicleDTO response =modelMapper.map(vehicle, VehicleDTO.class );
return  new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
}
