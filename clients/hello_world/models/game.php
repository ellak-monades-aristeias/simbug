<?php

class Game extends AppModel {
	
	var $name = 'Game';
	var $hasMany = array('GameSession');
	
	var $displayField = 'name';
	
	
	
	
}



?>