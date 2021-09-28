package com.sterp.multitenant.mastertenant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Navinder Singh
 */
@Entity
@Table(name = "tbl_tenant_master")
public class MasterTenant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1353873947617857906L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tenant_client_id")
	private Integer tenantClientId;

	@Size(max = 50)
	@Column(name = "db_name", nullable = false)
	private String dbName;

	@Size(max = 100)
	@Column(name = "url", nullable = false)
	private String url;

	@OneToOne(targetEntity = Clientage.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "client_id")
	private Clientage clientage;

	@Size(max = 50)
	@Column(name = "user_name", nullable = false)
	private String userName;
	@Size(max = 100)
	@Column(name = "password", nullable = false)
	private String password;
	@Size(max = 100)
	@Column(name = "driver_class", nullable = false)
	private String driverClass;
	@Size(max = 10)
	@Column(name = "status", nullable = false)
	private String status;

	public MasterTenant() {
	}

	public MasterTenant(@Size(max = 50) String dbName, @Size(max = 100) String url, @Size(max = 50) String userName,
			@Size(max = 100) String password, @Size(max = 100) String driverClass, @Size(max = 10) String status) {
		this.dbName = dbName;
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.driverClass = driverClass;
		this.status = status;
	}

	
	public Integer getTenantClientId() {
		return tenantClientId;
	}

	public MasterTenant setTenantClientId(Integer tenantClientId) {
		this.tenantClientId = tenantClientId;
		return this;
	}

	public String getDbName() {
		return dbName;
	}

	public MasterTenant setDbName(String dbName) {
		this.dbName = dbName;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public MasterTenant setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public MasterTenant setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public MasterTenant setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public MasterTenant setDriverClass(String driverClass) {
		this.driverClass = driverClass;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public MasterTenant setStatus(String status) {
		this.status = status;
		return this;
	}

	public Clientage getClientage() {
		return clientage;
	}

	public void setClientage(Clientage clientage) {
		this.clientage = clientage;
	}

}
