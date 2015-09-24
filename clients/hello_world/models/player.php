<?php

class Player extends AppModel {
	
	var $name = 'Player';
	var $hasAndBelongsToMany = array('GameSession');

}



?>