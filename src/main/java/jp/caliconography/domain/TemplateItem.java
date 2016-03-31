package jp.caliconography.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Resolution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "template_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid2")
	private String id;

	@Column(nullable = false)
	private String body;

	@Column(nullable = false)
	private String username;

	@DateBridge(resolution = Resolution.SECOND)
	@Column(nullable = false)
	private Date createdAt;

	@Column(nullable = false)
	private Date updatedAt;
}
