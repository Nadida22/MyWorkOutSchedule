package com.revature.MyWorkoutScheduleApp.repo;

import com.revature.MyWorkoutScheduleApp.model.workOutSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface workOutScheduleRepo extends JpaRepository<workOutSchedule, Integer> {


}
