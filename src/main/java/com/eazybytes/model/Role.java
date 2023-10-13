package com.eazybytes.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role extends BaseEntity{

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int roleId;

   @Column(name = "role_name")
    private String roleName;
    
    public Role() {
		// TODO Auto-generated constructor stub
	}

	public int getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String role_name) {
		this.roleName = role_name;
	}
}