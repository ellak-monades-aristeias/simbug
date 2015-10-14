<?php

class Player extends AppModel {
	
	var $name = 'Player';
	var $hasAndBelongsToMany = array('GameSession');
	
	var $virtualFields = array('showName' => "CONCAT(Player.username, ' (', Player.id, ')')");
	
	var $displayField = 'showName';

}



?>