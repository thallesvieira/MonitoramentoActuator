package com.agenda.agenda.resource.persistence.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.dto.request.UserDTORequest;
import com.agenda.agenda.domain.dto.response.AddressDTOResponse;
import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.domain.dto.response.UserDTOResponse;
import com.agenda.agenda.domain.util.Longs;
import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import com.agenda.agenda.resource.persistence.entity.base.IBaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = 1404061395446273795L;
	
	private String name;

	private String username;

	private String cpf;

	@JsonIgnore
	private String password;

	private String email;

	private Boolean active;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@OneToMany(cascade= CascadeType.ALL, mappedBy="user", fetch = FetchType.EAGER)
	private  List<Phone> phones;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id")
	private Profile profile;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_company",
			joinColumns = @JoinColumn(name = "company_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<Company> companies;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}
	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

	@Override
	public BaseEntity toEntity(BaseDTORequest dto) {
		UserDTORequest request = (UserDTORequest) dto;

		this.setId(request.getId());
		this.setName(request.getName());
		this.setUsername(request.getUsername());
		this.setPassword(request.getPassword());
		this.setCpf(request.getCpf());
		this.setEmail(request.getEmail());
		this.setActive(true);
		this.setCreatedDate(Longs.isNullOrZero(request.getId()) ? LocalDateTime.now() : null);
		this.setUpdateDate(!Longs.isNullOrZero(request.getId()) ? LocalDateTime.now() : null);
		this.setPhones(new Phone().toListEntity(request.getPhones()));
		this.setAddress(Objects.nonNull(request.getAddress()) ? (Address) new Address().toEntity(request.getAddress()) : null);
//		user.companies

		return this;
	}

	@Override
	public BaseDTOResponse toResponse() {
		UserDTOResponse response = new UserDTOResponse();

		response.setId(this.getId());
		response.setName(this.getName());
		response.setUsername(this.getUsername());
		response.setCpf(this.getCpf());
		response.setEmail(this.getEmail());
		response.setActive(this.getActive());
		response.setCreatedDate(this.getCreatedDate());
		response.setUpdateDate(this.getUpdateDate());
		response.setAddress((AddressDTOResponse) nonNullToResponse(this.getAddress()));
		response.setPhones(new Phone().toListResponse(this.getPhones()));
		//response.setCompanies(this.getCompanies());

		return response;
	}
}
