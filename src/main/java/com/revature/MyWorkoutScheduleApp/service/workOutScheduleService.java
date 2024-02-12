package com.revature.MyWorkoutScheduleApp.service;

import com.revature.MyWorkoutScheduleApp.ExceptionHandling.BadRequestException;
import com.revature.MyWorkoutScheduleApp.ExceptionHandling.NotFoundException;
import com.revature.MyWorkoutScheduleApp.model.workOutSchedule;
import com.revature.MyWorkoutScheduleApp.repo.workOutScheduleRepo;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class workOutScheduleService {

    private workOutScheduleRepo wos;

    @Autowired
    public workOutScheduleService(workOutScheduleRepo wos) {
        this.wos = wos;
    }



    public workOutSchedule createNewSchedule(workOutSchedule schedule){

        try {
            return wos.save(schedule);
        } catch (DuplicateRequestException e) {
            // Handle the constraint violation (e.g., duplicate day) and throw appropriate exception
            throw new DuplicateRequestException("Day is already scheduled");
        }
    }


    public List<workOutSchedule> findAllSchedules(){
        return wos.findAll();
    }


    public workOutSchedule findScheduleById(int id){

        Optional<workOutSchedule> schedule = wos.findById(id);

        if(schedule.isPresent()){
            return schedule.get();
        } else{
            throw new NotFoundException("Not Found");
        }
    }

    public workOutSchedule updateScheduleById(int id, workOutSchedule sc){

        Optional<workOutSchedule> checkSchedule = wos.findById(id);


        if(checkSchedule.isPresent()){
            workOutSchedule returnedSchedule = checkSchedule.get();
            returnedSchedule.setDaySchedule(sc.getDaySchedule());
            returnedSchedule.setExerciseName(sc.getExerciseName());
            returnedSchedule.setMuscleGroup(sc.getMuscleGroup());
            returnedSchedule.setMyFavWorkout(sc.isMyFavWorkout());

            return wos.save(returnedSchedule);

        } else{
            throw new NotFoundException("Not Found");
        }

    }


    public void deleteScheduleById(int id) throws BadRequestException{
        workOutSchedule deletedSchedule;
        Optional<workOutSchedule> checkSchedule = wos.findById(id);


        if(checkSchedule.isPresent()){
            wos.deleteById(id);
        } else{
            throw new BadRequestException("bad request");
        }


    }
}
