package ntu.fit.dinhnhatbao_thicuoiky_blog.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String password;
  private String fullName;

  @Enumerated(EnumType.STRING)
  private Role role;

  private boolean enabled = true;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<Post> posts;
}
