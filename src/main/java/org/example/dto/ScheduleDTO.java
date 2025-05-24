package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Set;

@Data
@Builder
public class ScheduleDTO {
    @JsonProperty
    Set<Month> months;
    @JsonProperty
    Set<DayOfWeek> weeklySchedule;
}
