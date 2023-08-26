package be.robbevw.italent_statistics_app.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Data
public class Project implements Serializable {

    public Long id;
    public String uuid;
    public String name;
    public LocalDateTime dateCreated;
    public Set<TimeLine> timeLines = new TreeSet<>();
}
