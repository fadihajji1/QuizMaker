package com.example.quizplatformf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizForm {
    private Long id;
    private String title;
    private String description;
    private Integer durationHours;
    private Integer durationMinutes;

    public Duration getDuration() {
        int hours = durationHours != null ? durationHours : 0;
        int minutes = durationMinutes != null ? durationMinutes : 0;

        return Duration.ofHours(hours)
                .plusMinutes(minutes);
    }

    public Duration toDuration() {
        int h = (durationHours == null) ? 0 : durationHours.intValue();
        int m = (durationMinutes == null) ? 0 : durationMinutes.intValue();
        // normalize minutes (e.g. 90 -> 1h30m) if you want:
        int totalMinutes = h * 60 + m;
        return Duration.ofMinutes(totalMinutes);
    }
}
