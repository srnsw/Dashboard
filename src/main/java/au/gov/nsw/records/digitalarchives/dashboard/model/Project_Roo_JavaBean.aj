// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package au.gov.nsw.records.digitalarchives.dashboard.model;

import au.gov.nsw.records.digitalarchives.dashboard.model.AccessRequirement;
import au.gov.nsw.records.digitalarchives.dashboard.model.DatabaseStorage;
import au.gov.nsw.records.digitalarchives.dashboard.model.Documentation;
import au.gov.nsw.records.digitalarchives.dashboard.model.EventHistory;
import au.gov.nsw.records.digitalarchives.dashboard.model.FileStorage;
import au.gov.nsw.records.digitalarchives.dashboard.model.MigrationStrategyAssessment;
import au.gov.nsw.records.digitalarchives.dashboard.model.Page;
import au.gov.nsw.records.digitalarchives.dashboard.model.Person;
import au.gov.nsw.records.digitalarchives.dashboard.model.Project;
import au.gov.nsw.records.digitalarchives.dashboard.model.ProjectType;
import au.gov.nsw.records.digitalarchives.dashboard.model.Record;
import au.gov.nsw.records.digitalarchives.dashboard.model.RelationshipWithOtherRecords;
import au.gov.nsw.records.digitalarchives.dashboard.model.Requirement;
import au.gov.nsw.records.digitalarchives.dashboard.model.Status;
import au.gov.nsw.records.digitalarchives.dashboard.model.SystemAnalysis;
import au.gov.nsw.records.digitalarchives.dashboard.model.Task;
import au.gov.nsw.records.digitalarchives.dashboard.model.Upload;
import java.util.Date;
import java.util.List;

privileged aspect Project_Roo_JavaBean {
    
    public Long Project.getId() {
        return this.id;
    }
    
    public void Project.setId(Long id) {
        this.id = id;
    }
    
    public String Project.getName() {
        return this.name;
    }
    
    public void Project.setName(String name) {
        this.name = name;
    }
    
    public ProjectType Project.getProjectType() {
        return this.projectType;
    }
    
    public void Project.setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }
    
    public String Project.getAgencyName() {
        return this.agencyName;
    }
    
    public void Project.setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    
    public String Project.getAgencyNumber() {
        return this.agencyNumber;
    }
    
    public void Project.setAgencyNumber(String agencyNumber) {
        this.agencyNumber = agencyNumber;
    }
    
    public String Project.getAgencyContact() {
        return this.agencyContact;
    }
    
    public void Project.setAgencyContact(String agencyContact) {
        this.agencyContact = agencyContact;
    }
    
    public String Project.getSrnswContact() {
        return this.srnswContact;
    }
    
    public void Project.setSrnswContact(String srnswContact) {
        this.srnswContact = srnswContact;
    }
    
    public String Project.getSrnswFileReference() {
        return this.srnswFileReference;
    }
    
    public void Project.setSrnswFileReference(String srnswFileReference) {
        this.srnswFileReference = srnswFileReference;
    }
    
    public List<Status> Project.getStatus() {
        return this.status;
    }
    
    public void Project.setStatus(List<Status> status) {
        this.status = status;
    }
    
    public String Project.getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    
    public void Project.setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    public String Project.getOverview() {
        return this.overview;
    }
    
    public void Project.setOverview(String overview) {
        this.overview = overview;
    }
    
    public Date Project.getCreationDate() {
        return this.creationDate;
    }
    
    public void Project.setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public Date Project.getLastUpdateDate() {
        return this.lastUpdateDate;
    }
    
    public void Project.setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    public Date Project.getCloseDate() {
        return this.closeDate;
    }
    
    public void Project.setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
    
    public Page Project.getProjectPlan() {
        return this.projectPlan;
    }
    
    public void Project.setProjectPlan(Page projectPlan) {
        this.projectPlan = projectPlan;
    }
    
    public Page Project.getMigrationPlan() {
        return this.migrationPlan;
    }
    
    public void Project.setMigrationPlan(Page migrationPlan) {
        this.migrationPlan = migrationPlan;
    }
    
    public List<Person> Project.getStakeholders() {
        return this.stakeholders;
    }
    
    public void Project.setStakeholders(List<Person> stakeholders) {
        this.stakeholders = stakeholders;
    }
    
    public List<Upload> Project.getUpload() {
        return this.upload;
    }
    
    public void Project.setUpload(List<Upload> upload) {
        this.upload = upload;
    }
    
    public List<Task> Project.getTask() {
        return this.task;
    }
    
    public void Project.setTask(List<Task> task) {
        this.task = task;
    }
    
    public Requirement Project.getRequirement() {
        return this.requirement;
    }
    
    public void Project.setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }
    
    public String Project.getDescription() {
        return this.description;
    }
    
    public void Project.setDescription(String description) {
        this.description = description;
    }
    
    public List<Documentation> Project.getDocumentation() {
        return this.documentation;
    }
    
    public void Project.setDocumentation(List<Documentation> documentation) {
        this.documentation = documentation;
    }
    
    public Record Project.getRecords() {
        return this.records;
    }
    
    public void Project.setRecords(Record records) {
        this.records = records;
    }
    
    public List<RelationshipWithOtherRecords> Project.getRelationship() {
        return this.relationship;
    }
    
    public void Project.setRelationship(List<RelationshipWithOtherRecords> relationship) {
        this.relationship = relationship;
    }
    
    public List<AccessRequirement> Project.getAccessRequirement() {
        return this.accessRequirement;
    }
    
    public void Project.setAccessRequirement(List<AccessRequirement> accessRequirement) {
        this.accessRequirement = accessRequirement;
    }
    
    public List<SystemAnalysis> Project.getSystemAnalysis() {
        return this.systemAnalysis;
    }
    
    public void Project.setSystemAnalysis(List<SystemAnalysis> systemAnalysis) {
        this.systemAnalysis = systemAnalysis;
    }
    
    public List<FileStorage> Project.getFileStorage() {
        return this.fileStorage;
    }
    
    public void Project.setFileStorage(List<FileStorage> fileStorage) {
        this.fileStorage = fileStorage;
    }
    
    public List<DatabaseStorage> Project.getDatabaseStorage() {
        return this.databaseStorage;
    }
    
    public void Project.setDatabaseStorage(List<DatabaseStorage> databaseStorage) {
        this.databaseStorage = databaseStorage;
    }
    
    public List<MigrationStrategyAssessment> Project.getMigrationStrategyAssessment() {
        return this.migrationStrategyAssessment;
    }
    
    public void Project.setMigrationStrategyAssessment(List<MigrationStrategyAssessment> migrationStrategyAssessment) {
        this.migrationStrategyAssessment = migrationStrategyAssessment;
    }
    
    public List<EventHistory> Project.getEventHistory() {
        return this.eventHistory;
    }
    
    public void Project.setEventHistory(List<EventHistory> eventHistory) {
        this.eventHistory = eventHistory;
    }
    
}
