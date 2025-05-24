package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Set;

@Data
@Builder
public class ScheduleDTO {
    Set<Month> months;
    Set<DayOfWeek> weeklySchedule;
}
