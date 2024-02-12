package com.revature.MyWorkoutScheduleApp.controller;

import com.revature.MyWorkoutScheduleApp.ExceptionHandling.BadRequestException;
import com.revature.MyWorkoutScheduleApp.ExceptionHandling.NotFoundException;
import com.revature.MyWorkoutScheduleApp.model.workOutSchedule;
import com.revature.MyWorkoutScheduleApp.service.workOutScheduleService;
import com.sun.jdi.request.DuplicateRequestException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class workOutScheduleController {

    private workOutScheduleService wos;


    @Autowired
    public workOutScheduleController(workOutScheduleService wos) {
        this.wos = wos;
    }

    // creating a new schedule
    @PostMapping
    public workOutSchedule createWorkOutSchedule(@RequestBody workOutSchedule schedule){

        workOutSchedule savedSchedule = wos.createNewSchedule(schedule);

        try {
            return savedSchedule;
        } catch (DuplicateRequestException e) {
            // Handle the constraint violation (e.g., duplicate day) and throw appropriate exception
            throw new DuplicateRequestException("Day is already scheduled");
        }

    }


    // retrieving a schedule by id
    @GetMapping("{id}")
    public ResponseEntity<workOutSchedule> getWorkOutScheduleById(@PathVariable int id){

        workOutSchedule returnedSchedule;

        try{
            returnedSchedule = wos.findScheduleById(id);
        }catch(NotFoundException b){
            throw new NotFoundException("Not found");
        }
        return new ResponseEntity<>(returnedSchedule, HttpStatus.OK);
    }


    // retrieving all schedules
    @GetMapping
    public List<workOutSchedule> getAllSchedules(){

        List<workOutSchedule> allSchedules = wos.findAllSchedules();

        return allSchedules;
    }


    // updating existed schedule
    @PutMapping("{id}")
    public ResponseEntity<workOutSchedule> updateWorkOutSchedule( @PathVariable int id, @RequestBody workOutSchedule schedule){

        workOutSchedule updatedSchedule;

        try{
            updatedSchedule = wos.updateScheduleById(id, schedule);
        } catch (NotFoundException b){
            throw new NotFoundException("Not Found");
        }

        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }


    // deleting a row by its id
    @DeleteMapping("{id}")
    public ResponseEntity<workOutSchedule> deleteScheduleById(@PathVariable int id){

        try{
            wos.deleteScheduleById(id);
        }catch (NotFoundException a){
            throw new NotFoundException("Not found");
        }

       return new ResponseEntity<>(HttpStatus.OK);
    }


}
