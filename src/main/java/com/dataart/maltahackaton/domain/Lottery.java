package com.dataart.maltahackaton.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.dataart.maltahackaton.utils.DateUtils.DATE_TIME_FORMAT;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Lottery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String contractAddress;

    private String fundName;

    private String fundDescription;

    @Enumerated(EnumType.STRING)
    private LotteryStatus status;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime startDate;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime endDate;

}