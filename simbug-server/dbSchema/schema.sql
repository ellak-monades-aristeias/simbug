/* SQL Definition */

CREATE DATABASE simbug
CHARACTER SET utf8_general_ci;

create table simbug.game
(
	id INT NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	type varchar(50),
    PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table simbug.game_session
(
	id INT NOT NULL AUTO_INCREMENT,
	game_id INTEGER,
	game_session_uuid varchar(100),
	definition_data LONGTEXT,
	definition_file varchar(100),
	players varchar(500),
	current_round INTEGER,
	PRIMARY KEY (id),
    CONSTRAINT game_session_game_id FOREIGN KEY (game_id) 
    	REFERENCES simbug.game (id) ON DELETE SET NULL,
    INDEX i_game_session_game_id (game_id)	
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table simbug.game_session
add game_session_uuid varchar(100) not null;

alter table simbug.game_session_player
add player_uuid varchar(100) not null;

drop column player_uuid;

alter table simbug.game_session_round_player
add player_uuid varchar(100) not null;

drop column player_uuid;

create table simbug.game_session_player
(
	id INT NOT NULL AUTO_INCREMENT,
	game_session_id INTEGER,
	player_uuid varchar(100) not null,
	PRIMARY KEY (id),
    CONSTRAINT game_session_game_session_id FOREIGN KEY (game_session_id) 
    	REFERENCES simbug.game_session (id) ON DELETE SET NULL,
    INDEX i_game_session_player_game_session_id (game_session_id)	
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table simbug.game_session_round
(
	id INT NOT NULL AUTO_INCREMENT,
	game_session_id INTEGER,
	round_num INTEGER,
	variable_name varchar(50),
	variable_value varchar(100),
	variable_type varchar(100),
	PRIMARY KEY (id),
    CONSTRAINT game_session_round_game_session_id FOREIGN KEY (game_session_id) 
    	REFERENCES simbug.game_session (id) ON DELETE SET NULL,
    INDEX i_game_session_round_game_session_id (game_session_id)	
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table simbug.game_session_round_player
(
	id INT NOT NULL AUTO_INCREMENT,
	game_session_id INTEGER,
	player_uuid varchar(100) not null,
	round_num INTEGER,
	variable_name varchar(50),
	variable_value varchar(100),
	variable_type varchar(100),
	PRIMARY KEY (id),
    CONSTRAINT game_session_round_player_game_session_id FOREIGN KEY (game_session_id) 
    	REFERENCES simbug.game_session (id) ON DELETE SET NULL,
    INDEX i_game_session_round_player_game_session_id (game_session_id)	
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
