// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package au.gov.nsw.records.digitalarchives.dashboard.model;

import au.gov.nsw.records.digitalarchives.dashboard.model.Record;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Record_Roo_Jpa_Entity {
    
    declare @type: Record: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Record.id;
    
    @Version
    @Column(name = "version")
    private Integer Record.version;
    
    public Long Record.getId() {
        return this.id;
    }
    
    public void Record.setId(Long id) {
        this.id = id;
    }
    
    public Integer Record.getVersion() {
        return this.version;
    }
    
    public void Record.setVersion(Integer version) {
        this.version = version;
    }
    
}
