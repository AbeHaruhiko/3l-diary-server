CREATE
    TABLE
    	IF NOT EXISTS
        users (
            username varchar_ignorecase (50) NOT NULL PRIMARY KEY
            ,password varchar_ignorecase (50) NOT NULL
            ,enabled BOOLEAN NOT NULL
        )
;

CREATE
    TABLE
    	IF NOT EXISTS
        authorities (
            username varchar_ignorecase (50) NOT NULL
            ,authority varchar_ignorecase (50) NOT NULL
            ,CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
        )
;
-- JPAがdomainオブジェクトを見て勝手にやるのでいったん消してみる。
--drop table posts;
CREATE
    TABLE
    	IF NOT EXISTS
        posts (
        	id varchar_ignorecase (37) NOT NULL PRIMARY KEY
        	,body varchar_ignorecase (4000) NOT NULL
            ,username varchar_ignorecase (50) NOT NULL
            ,created_at timestamp NOT NULL
            ,updated_at timestamp NOT NULL
            ,CONSTRAINT fk_posts_users FOREIGN KEY (username) REFERENCES users (username)
        )
;

CREATE
    TABLE
    	IF NOT EXISTS
        templates (
        	id varchar_ignorecase (37) NOT NULL PRIMARY KEY
            ,username varchar_ignorecase (50) NOT NULL
            ,created_at timestamp NOT NULL
            ,updated_at timestamp NOT NULL
            ,CONSTRAINT fk_templates_users FOREIGN KEY (username) REFERENCES users (username)
        )
;

CREATE
    TABLE
    	IF NOT EXISTS
        template_items (
        	id varchar_ignorecase (37) NOT NULL PRIMARY KEY
        	, template_id varchar_ignorecase (37) NOT NULL
        	,body varchar_ignorecase (200) NOT NULL
            ,username varchar_ignorecase (50) NOT NULL
            ,created_at timestamp NOT NULL
            ,updated_at timestamp NOT NULL
            ,CONSTRAINT fk_template_items_users FOREIGN KEY (username) REFERENCES users (username)
            ,CONSTRAINT fk_template_items_templates FOREIGN KEY (template_id) REFERENCES templates (id)
        )
;


CREATE
    UNIQUE index
    	IF NOT EXISTS
	    ix_auth_username
	        ON authorities (
	        username
	        ,authority
	    )
;
