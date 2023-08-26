package be.robbevw.italent_statistics_app.events;

import be.robbevw.italent_statistics_app.models.Project;
import be.robbevw.italent_statistics_app.models.ProjectStatistics;
import be.robbevw.italent_statistics_app.models.TimeLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
public class ProjectTimingChangedEvent {

    @KafkaListener(topics = "Projects", groupId = "projects_time_changed", containerFactory = "kafkaListenerContainerFactory")
    public void listenTimingChanged(Project project) {
        log.info("Incoming project timing changed event: " + project.toString());

        final ProjectStatistics projectStatistics = new ProjectStatistics(getAverageTimePerDay(project), getTotalProjectTime(project));

        log.info("Project statistics calculated: " + projectStatistics);
    }

    private long getAverageTimePerDay(Project project) {
        return getTotalProjectTime(project) / Duration.between(project.getDateCreated(), LocalDateTime.now()).toDays();
    }

    private static long getTotalProjectTime(Project project) {
        return project.timeLines.stream()
                .mapToLong(timeLine -> Duration.between(timeLine.getStartTime(), getEndTime(timeLine)).getSeconds())
                .sum();
    }

    private static LocalDateTime getEndTime(TimeLine timeLine) {
        return timeLine.getEndTime() == null ? LocalDateTime.now() : timeLine.getEndTime();
    }
}
