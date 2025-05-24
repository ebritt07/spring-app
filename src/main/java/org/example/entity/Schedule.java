package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Schedule {
    Set<String> months;
    Set<String> weeklySchedule;
    @Id
    @GeneratedValue
    private UUID id;
}
