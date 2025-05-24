package org.example.mapper;

import org.example.dto.ScheduleDTO;
import org.example.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(target = "id", ignore = true)
    Schedule toSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO toScheduleDTO(Schedule schedule);
}
