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

CREATE
    UNIQUE index
    	IF NOT EXISTS
	    ix_auth_username
	        ON authorities (
	        username
	        ,authority
	    )
;