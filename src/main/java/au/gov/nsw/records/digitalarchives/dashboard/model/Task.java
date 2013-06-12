package au.gov.nsw.records.digitalarchives.dashboard.model;

import com.google.gson.annotations.Expose;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findTasksByStatus", "findTasksByAssignedTo" })
public class Task {

    @Id
    @Expose
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Expose
    private String description;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Person assignedTo;

    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created;

    @ManyToOne
    private Person createdBy;

    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date due;

    @Enumerated(EnumType.STRING)
    private TaskStatusType status;

    public static TypedQuery<au.gov.nsw.records.digitalarchives.dashboard.model.Task> findTasksByStatusNot(TaskStatusType status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Task.entityManager();
        TypedQuery<Task> q = em.createQuery("SELECT o FROM Task AS o WHERE o.status <> :status", Task.class);
        q.setParameter("status", status);
        return q;
    }
    
    public static TypedQuery<Task> findTasksByAssignedToOrCreatedBy(Person person) {
      if (person == null) throw new IllegalArgumentException("The assignedTo argument is required");
      EntityManager em = Task.entityManager();
      TypedQuery<Task> q = em.createQuery("SELECT o FROM Task AS o WHERE o.assignedTo = :person or o.createdBy = :person", Task.class);
      q.setParameter("person", person);
      return q;
  }
}
