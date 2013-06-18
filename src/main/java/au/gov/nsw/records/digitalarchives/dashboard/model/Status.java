package au.gov.nsw.records.digitalarchives.dashboard.model;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@RooJpaActiveRecord(finders = { "findStatusesByProject" })
public class Status {

    @Enumerated(EnumType.STRING)
    private StatusType projectStatusType;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastUpdateDate;

    private String comment;

    @ManyToOne
    private Project project;

	public static TypedQuery<Status> findStatusesByProject(Project project) {
        if (project == null) throw new IllegalArgumentException("The project argument is required");
        EntityManager em = Status.entityManager();
        TypedQuery<Status> q = em.createQuery("SELECT o FROM Status AS o WHERE o.project = :project order by lastUpdateDate desc", Status.class);
        q.setParameter("project", project);
        return q;
    }
}
