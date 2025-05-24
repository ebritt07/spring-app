package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Route {
    String aircraft;
    String origin;
    String destination;
    @ManyToOne
    Schedule schedule;
    @Id
    private Long id;
}