package au.gov.nsw.records.digitalarchives.dashboard.model;

import com.google.gson.annotations.Expose;
import java.util.Date;
import javax.persistence.EntityManager;
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
@RooJpaActiveRecord(finders = { "findEventHistorysByProject", "findEventHistorysByUser" })
public class EventHistory {

    @ManyToOne
    private Person user;

    @ManyToOne
    private Project project;

    private String event;

    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creationDate = new Date();

	public static TypedQuery<EventHistory> findEventHistorysByProject(Project project) {
        if (project == null) throw new IllegalArgumentException("The project argument is required");
        EntityManager em = EventHistory.entityManager();
        TypedQuery<EventHistory> q = em.createQuery("SELECT o FROM EventHistory AS o WHERE o.project = :project order by creationDate desc", EventHistory.class);
        q.setParameter("project", project);
        return q;
    }
}
