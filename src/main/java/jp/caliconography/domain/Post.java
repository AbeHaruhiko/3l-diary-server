package jp.caliconography.domain;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.FormParam;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;

//	@FormParam("body")
	@Column(nullable = false)
	private String body;

//	@FormParam("username")
	@Column(nullable = false)
	private String username;

//	@FormParam("createdAt")
//	@Column(nullable = false)
	private Date createdAt;

//	@FormParam("updatedAt")
//	@Column(nullable = false)
	private Date updatedAt;
}