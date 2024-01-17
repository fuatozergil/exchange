package com.fuat.exchange.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Data
@AllArgsConstructor
public class Analytic {
    @Id
    public String id;
    public LocalDateTime timestamp;
    public String username;
    public String type;
}
