<?php

class GameSession extends AppModel {
	
	var $name = 'GameSession';
	var $belongsTo = 'Game';
	var $hasAndBelongsToMany = array('Player');
	
	var $displayField = 'name';
	
	/**
	 * 
	 * @param unknown $data
	 * @param unknown $filterKey
	 */
	function create($data = array(), $filterKey = false) {
		$d = parent::create($data,$filterKey);
		$d['GameSession']['uuid']= String::uuid();
		return $d;
	}
	

	
	
}



?>