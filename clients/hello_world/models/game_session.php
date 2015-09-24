<?php

class GameSession extends AppModel {
	
	var $name = 'GameSession';
	var $belongsTo = 'Game';
	var $hasAndBelongsToMany = array('Player');
	
	var $displayField = 'name';
	
	
	
	
}



?>