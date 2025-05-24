package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Route {
    String aircraft;
    String origin;
    String destination;
    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    Schedule schedule;
    @Id
    @GeneratedValue
    private UUID id;
}