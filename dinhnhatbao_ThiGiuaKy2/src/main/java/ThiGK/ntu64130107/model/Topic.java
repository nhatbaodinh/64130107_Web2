package ThiGK.ntu64130107.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
  private String id;
  private String topicName;
  private String topicDescription;
  private String supervisorId;
  private String topicType;
}