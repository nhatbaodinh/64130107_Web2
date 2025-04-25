package group6.fit_ntu_cms.services;

import group6.fit_ntu_cms.models.EventModel;
import group6.fit_ntu_cms.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
  @Autowired
  private EventRepository eventRepository;

  public List<EventModel> getAllEvents() {
    return eventRepository.findAll();
  }
}
