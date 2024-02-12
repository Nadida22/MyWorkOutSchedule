package com.revature.MyWorkoutScheduleApp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "schedule")
public class workOutSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name="day_Schedule", unique = true)
    private String daySchedule;

    @Column(name = "exercise_Name")
    private String exerciseName;

    @Column(name = "muscle_Group")
    private String muscleGroup;

    @Column(name = "my_fav_workout")
    private boolean myFavWorkout;

    public workOutSchedule() {

    }


    public int getId() {
        return id;
    }

    public String getDaySchedule() {
        return daySchedule;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public boolean isMyFavWorkout() {
        return myFavWorkout;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDaySchedule(String daySchedule) {
        this.daySchedule = daySchedule;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public void setMyFavWorkout(boolean myFavWorkout) {
        this.myFavWorkout = myFavWorkout;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        workOutSchedule that = (workOutSchedule) o;
        return id == that.id && myFavWorkout == that.myFavWorkout && daySchedule.equals(that.daySchedule) && exerciseName.equals(that.exerciseName) && muscleGroup.equals(that.muscleGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, daySchedule, exerciseName, muscleGroup, myFavWorkout);
    }


    @Override
    public String toString() {
        return "workOutSchedule{" +
                "id=" + id +
                ", daySchedule='" + daySchedule + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", myFavWorkout=" + myFavWorkout +
                '}';
    }
}

