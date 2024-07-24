package orange.odc.sprintboot.models;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;

	@CreationTimestamp
	@Column(updatable = true)
	private LocalDateTime date_create;

	@UpdateTimestamp
	private LocalDateTime date_update;

	@ManyToOne
	private User user;
	
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Note(Long id, String title, String description, LocalDateTime date_create, LocalDateTime date_update) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.date_create = date_create;
		this.date_update = date_update;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate_create() {
		return date_create;
	}

	public void setDate_create(LocalDateTime date_create) {
		this.date_create = date_create;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getDate_update() {
		return date_update;
	}

	public void setDate_update(LocalDateTime date_update) {
		this.date_update = date_update;
	}
}
