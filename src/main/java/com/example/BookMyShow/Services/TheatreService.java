package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.TheatreConvertor;
import com.example.BookMyShow.EntryDtos.TheatreEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.Theatre;
import com.example.BookMyShow.Models.TheatreSeat;
import com.example.BookMyShow.Repositories.TheatreRepository;
import com.example.BookMyShow.Repositories.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    TheatreSeatRepository theatreSeatRepository;
    @Autowired
    TheatreRepository theatreRepository;
    public String addTheatre(TheatreEntryDto theatreEntryDto){

        //Converting theatreEntryDto to theatre by using static convertor method
        Theatre theatre= TheatreConvertor.convertTheatreDtoToTheatreEntity(theatreEntryDto);

        //Creating the seats for the theatre. using the create theatreSeat function.
        List<TheatreSeat> theatreSeatList=createTheatreSeat(theatreEntryDto,theatre);
        theatre.setTheatreSeatList(theatreSeatList);//Setting the created seats to the theatre before saving it in the database

        //Both theatre and theatreSeats are saved in the database in their respective tables
        //Because of the cascade effect child entity theatreSeats will automatically saved
        theatreRepository.save(theatre);

        return "Theatre added successfully";
    }
    public List<TheatreSeat> createTheatreSeat(TheatreEntryDto theatreEntryDto,Theatre theatre){

        //Using no.Of Classic seats and No.OF premium seats theatre seats are being created
        List<TheatreSeat> theatreSeatList=new ArrayList<>();

        for(int i=1;i<= theatreEntryDto.getNoOfClassicSeats();i++)
        {
            TheatreSeat theatreSeat= TheatreSeat.builder().seatType(SeatType.CLASSIC).seatNo(i+"C").theatre(theatre).build();
            theatreSeatList.add(theatreSeat);

        }
        for(int i=1;i<= theatreEntryDto.getNoOfPremiumSeats();i++){
            TheatreSeat theatreSeat= TheatreSeat.builder().seatType(SeatType.PLATINUM).seatNo(i+"P").theatre(theatre).build();
            theatreSeatList.add(theatreSeat);
        }
        return theatreSeatList;
    }
}
