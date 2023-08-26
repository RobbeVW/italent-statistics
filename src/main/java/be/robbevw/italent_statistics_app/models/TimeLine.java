package be.robbevw.italent_statistics_app.models;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TimeLine implements Comparable<TimeLine>, Serializable {

    public Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Override
    public int compareTo(TimeLine otherTimeline) {
        return otherTimeline.getStartTime().compareTo(this.getStartTime());
    }
}
