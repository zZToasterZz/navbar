package srdt.co.in.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import srdt.co.in.utility.Generation;


@MappedSuperclass
public class SharedField {

	@Column(length = 50)
	@NotNull
	public String CreatedBy;
	@Column(length = 50)
	@NotNull
	public String ModifiedBy;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	public Date CreatedDate = Generation.getCurrentDate();
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	public Date LastUpdatedDate = Generation.getCurrentDate();;
	@Column(length = 1)
	@NotNull
	public String IsActive = "Y";
	
	public SharedField() {
		
	}
	public SharedField(@NotNull String createdBy) {
		
		CreatedBy = createdBy;
		ModifiedBy = createdBy;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getModifiedBy() {
		return ModifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
	public Date getLastUpdatedDate() {
		return LastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
	}
	public String getIsActive() {
		return IsActive;
	}
	public void setIsActive(String isActive) {
		IsActive = isActive;
	}	
}
