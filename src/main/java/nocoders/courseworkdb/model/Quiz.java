package nocoders.courseworkdb.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name of Quiz can''t be empty!")
    private String name;
    @NotBlank(message = "Description can''t be empty!")
    @Length(max = 4000, message = "Too long description!")
    private String text;
    private String tag;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "users_id")
//    private User author;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<>();


    public Quiz() {
    }

    public Quiz(String name, String text, String tag) {
        this.name = name;
        this.text = text;
        this.tag = tag;
    }

}


