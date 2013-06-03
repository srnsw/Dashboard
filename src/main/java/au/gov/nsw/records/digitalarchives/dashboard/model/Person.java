package au.gov.nsw.records.digitalarchives.dashboard.model;

import com.google.gson.annotations.Expose;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPeopleByEmailEquals", "findPeopleByApprovedNot" })
public class Person {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Expose
    @NotNull
    @NotEmpty
    private String email;

    private String password;

    @Expose
    private boolean approved = false;

    @Expose
    @NotNull
    @NotEmpty
    private String name;

    @Expose
    @NotNull
    @NotEmpty
    private String lastName;
    
    @Expose
    private String telephone;

    @Expose
    private String jobtitle;

    @Expose
    @NotNull
    private String agencyText;

    @Expose
    @NotEmpty
    private String address;

    @Expose
    private int agencyNumber;

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.ROLE_AGENCY;
}
