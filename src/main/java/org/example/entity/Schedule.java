package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Schedule {
    @Id
    Long id;
    Set<String> months;
    Set<String> weeklySchedule;
}
