package jp.caliconography.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "templates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Template implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid2")
	private String id;

	@Column(nullable = false)
	private String name;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumns({
        @JoinColumn(name = "username", referencedColumnName = "username"),
        @JoinColumn(name = "templateId", referencedColumnName = "id")
    })
//	@JoinColumn(name = "username")
	private List<TemplateItem> templateItems;
	
	@Column(nullable = false)
	private String username;

//	@Column(nullable = false)
	private Date createdAt;

//	@Column(nullable = false)
	private Date updatedAt;
}