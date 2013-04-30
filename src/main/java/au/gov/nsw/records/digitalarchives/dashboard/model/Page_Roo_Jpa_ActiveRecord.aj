// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package au.gov.nsw.records.digitalarchives.dashboard.model;

import au.gov.nsw.records.digitalarchives.dashboard.model.Page;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Page_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Page.entityManager;
    
    public static final EntityManager Page.entityManager() {
        EntityManager em = new Page().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Page.countPages() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Page o", Long.class).getSingleResult();
    }
    
    public static List<Page> Page.findAllPages() {
        return entityManager().createQuery("SELECT o FROM Page o", Page.class).getResultList();
    }
    
    public static Page Page.findPage(Long id) {
        if (id == null) return null;
        return entityManager().find(Page.class, id);
    }
    
    public static List<Page> Page.findPageEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Page o", Page.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Page.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Page.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Page attached = Page.findPage(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Page.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Page.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Page Page.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Page merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}