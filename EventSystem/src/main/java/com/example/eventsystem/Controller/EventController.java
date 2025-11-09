package com.example.eventsystem.Controller;

import com.example.eventsystem.Api.ApiResponse;
import com.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Event> getEvents(){
        return events;
    }

    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody Event event){
        event.setStartDate(LocalDateTime.now());
        events.add(event);
        return new ApiResponse("Event has been added");
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@PathVariable int index, @RequestBody Event event){
       event.setStartDate(LocalDateTime.now());
       events.set(index, event);
       return new ApiResponse("event has been updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEvent(@PathVariable int index){
        events.remove(index);
        return new ApiResponse("event has been deleted");
    }

    @PutMapping("/update-capacity/{id}/{capacity}")
    public ApiResponse updateCapacity(@PathVariable String id, @PathVariable int capacity){
        for(Event e: events){
            if(e.getId().equalsIgnoreCase(id)){
                e.setCapacity(capacity);
                return new ApiResponse("event "+ e.getId() + " capacity has been changed to " + capacity);
            }
        }
        return new ApiResponse("Could not find event with ID " + id);
    }

    @GetMapping("/search-by-id/{id}")
    public Event searchById(@PathVariable String id){
        for (Event e : events){
            if(e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }


}
