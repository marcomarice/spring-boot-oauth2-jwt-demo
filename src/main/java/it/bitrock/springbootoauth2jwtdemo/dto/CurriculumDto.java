package it.bitrock.springbootoauth2jwtdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CurriculumDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String file;
    private LocalDateTime uploadDate;
    private String status;
}
