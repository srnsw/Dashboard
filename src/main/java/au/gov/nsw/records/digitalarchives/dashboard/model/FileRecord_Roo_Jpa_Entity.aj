// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package au.gov.nsw.records.digitalarchives.dashboard.model;

import au.gov.nsw.records.digitalarchives.dashboard.model.FileRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

privileged aspect FileRecord_Roo_Jpa_Entity {
    
    declare @type: FileRecord: @Entity;
    
    @Version
    @Column(name = "version")
    private Integer FileRecord.version;
    
    public Integer FileRecord.getVersion() {
        return this.version;
    }
    
    public void FileRecord.setVersion(Integer version) {
        this.version = version;
    }
    
}