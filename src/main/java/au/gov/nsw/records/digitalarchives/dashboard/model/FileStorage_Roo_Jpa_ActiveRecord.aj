// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package au.gov.nsw.records.digitalarchives.dashboard.model;

import au.gov.nsw.records.digitalarchives.dashboard.model.FileStorage;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect FileStorage_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager FileStorage.entityManager;
    
    public static final EntityManager FileStorage.entityManager() {
        EntityManager em = new FileStorage().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long FileStorage.countFileStorages() {
        return entityManager().createQuery("SELECT COUNT(o) FROM FileStorage o", Long.class).getSingleResult();
    }
    
    public static List<FileStorage> FileStorage.findAllFileStorages() {
        return entityManager().createQuery("SELECT o FROM FileStorage o", FileStorage.class).getResultList();
    }
    
    public static FileStorage FileStorage.findFileStorage(Long id) {
        if (id == null) return null;
        return entityManager().find(FileStorage.class, id);
    }
    
    public static List<FileStorage> FileStorage.findFileStorageEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM FileStorage o", FileStorage.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void FileStorage.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void FileStorage.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            FileStorage attached = FileStorage.findFileStorage(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void FileStorage.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void FileStorage.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public FileStorage FileStorage.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        FileStorage merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}