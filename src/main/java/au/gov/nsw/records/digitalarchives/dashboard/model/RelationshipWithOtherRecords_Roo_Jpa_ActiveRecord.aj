// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package au.gov.nsw.records.digitalarchives.dashboard.model;

import au.gov.nsw.records.digitalarchives.dashboard.model.RelationshipWithOtherRecords;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RelationshipWithOtherRecords_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager RelationshipWithOtherRecords.entityManager;
    
    public static final EntityManager RelationshipWithOtherRecords.entityManager() {
        EntityManager em = new RelationshipWithOtherRecords().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long RelationshipWithOtherRecords.countRelationshipWithOtherRecordses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM RelationshipWithOtherRecords o", Long.class).getSingleResult();
    }
    
    public static List<RelationshipWithOtherRecords> RelationshipWithOtherRecords.findAllRelationshipWithOtherRecordses() {
        return entityManager().createQuery("SELECT o FROM RelationshipWithOtherRecords o", RelationshipWithOtherRecords.class).getResultList();
    }
    
    public static RelationshipWithOtherRecords RelationshipWithOtherRecords.findRelationshipWithOtherRecords(Long id) {
        if (id == null) return null;
        return entityManager().find(RelationshipWithOtherRecords.class, id);
    }
    
    public static List<RelationshipWithOtherRecords> RelationshipWithOtherRecords.findRelationshipWithOtherRecordsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM RelationshipWithOtherRecords o", RelationshipWithOtherRecords.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void RelationshipWithOtherRecords.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void RelationshipWithOtherRecords.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            RelationshipWithOtherRecords attached = RelationshipWithOtherRecords.findRelationshipWithOtherRecords(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void RelationshipWithOtherRecords.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void RelationshipWithOtherRecords.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public RelationshipWithOtherRecords RelationshipWithOtherRecords.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        RelationshipWithOtherRecords merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}