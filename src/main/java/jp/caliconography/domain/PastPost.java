package jp.caliconography.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Indexed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "past_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Indexed
public class PastPost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid2")
	private String id;

	@Column(nullable = false)
	private String postId;

	@Column(nullable = false)
	private java.sql.Date forDate;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private java.sql.Timestamp createdAt;

	@Column(nullable = false)
	private java.sql.Timestamp updatedAt;
}