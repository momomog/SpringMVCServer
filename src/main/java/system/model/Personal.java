package system.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter
    @Setter
    private String commentary;

    @Getter
    @Setter
    private int name;

    @Getter
    @Setter
    private int technology;

    @Getter
    @Setter
    private int skill;

    @Getter
    @Setter
    private int used;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name", insertable = false, updatable = false)
    @Getter
    @Setter
    private User userObject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology", insertable = false, updatable = false)
    @Getter
    @Setter
    private Technology technologyObject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill", insertable = false, updatable = false)
    @Getter
    @Setter
    private Skill skillObject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "used", insertable = false, updatable = false)
    @Getter
    @Setter
    private LastUsed lastUsedObject;

    public Personal() {
    }
}
