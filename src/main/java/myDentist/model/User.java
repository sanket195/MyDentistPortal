package myDentist.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	public static String getRoleUser() {
		return ROLE_USER;
	}

	@Id
    @GeneratedValue
    private Integer userId;

    private String username;

    private String userType;
    
    private Integer userContact;
    
    private String userEmail;
    
    private Date dateOfBirth;
    
    private String password;
    
    private String userAddress;
    
    @ElementCollection
    @CollectionTable(name = "authorities",
        joinColumns = @JoinColumn(name = "userid"))
    @Column(name = "role")
    private Set<String> roles;
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getRoleAdmin() {
		return ROLE_ADMIN;
	}

	@Column(nullable = false)
    private boolean enabled;
    
    public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

    
    public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Integer getUserContact() {
		return userContact;
	}

	public void setUserContact(Integer userContact) {
		this.userContact = userContact;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	        for( String role : roles )
	            authorities.add( new SimpleGrantedAuthority( role ) );
	        return authorities;
	}

	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public static String getRoleDoctor() {
		return ROLE_ADMIN;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
    
	 public boolean isSysadmin()
	    {
	        return roles.contains( "ROLE_ADMIN" );
	    }

   
}