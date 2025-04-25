package group6.fit_ntu_cms.controllers;

import group6.fit_ntu_cms.models.EventModel;
import group6.fit_ntu_cms.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EventController {
  @Autowired
  EventRepository eventRepository;

  @GetMapping("/events")
  public String getAllEvents(Model model) {
    List<EventModel> events = eventRepository.findAll();
    model.addAttribute("events", events);
    return "events";
  }
}
