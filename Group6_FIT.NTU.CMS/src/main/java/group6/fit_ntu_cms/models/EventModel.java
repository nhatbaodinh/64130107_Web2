package group6.fit_ntu_cms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "events")
public class EventModel {
  @Id
  private String eventId;
  private String eventName;
  private String eventDate;
  private String eventLocation;
  private String eventDescription;
  private String eventImage;
  private String eventTime;
}