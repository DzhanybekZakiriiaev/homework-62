package com.example.lesson62.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionsPerUser {
    private User whoIsSigning;
    private User whoIsSubscribedTo;
    private LocalDateTime eventDate;
}
