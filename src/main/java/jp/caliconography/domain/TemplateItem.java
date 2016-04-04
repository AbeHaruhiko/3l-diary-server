package jp.caliconography.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	private String templateId;

	@Column(nullable = false)
	private String body;

	@Column(nullable = false)
	private String username;

//	@Column(nullable = false)
	private Date createdAt;

//	@Column(nullable = false)
	private Date updatedAt;
}
