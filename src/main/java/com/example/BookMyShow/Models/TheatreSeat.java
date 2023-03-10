package com.example.BookMyShow.Models;

import com.example.BookMyShow.Enums.SeatType;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "theatre_seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheatreSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    private Theatre theatre;
}
