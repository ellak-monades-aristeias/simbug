<?php

App::import('HttpSocket');


class SimBugClient extends Object {
	
	private $HttpSocket = null; 
	private $rest_server_url = "http://quijote.aua.gr:8080/simbug-server";
	
	
	
	function __construct() {
		$this->HttpSocket = new HttpSocket();
	}
	
	function __destruct() {
		$this->HttpSocket->disconnect();
	}
	
	/**
	 * 
	 * @param array $gs The GameSession record 
	 * @return boolean 1=success, 0=failure
	 */
	function initGameSession($gs) {
		
		$sep='';$players_string='[';
		foreach($gs['Player'] as $pl) {
			$players_string.=$sep."{'uuid':'";
			$players_string.=$pl['id'];
			$players_string.="'}";
			$sep=',';
		}
		$players_string.=']';
		
		//form data
		$data=array();
		$data['listOfPlayers'] = $players_string;
		$data['definitionString']=$gs['Game']['definition'];
		
		$results = $this->HttpSocket->post($this->rest_server_url.'/initGameSession/'.$gs['GameSession']['uuid'],$data);
		
		$results_json = json_decode($results, TRUE);

		return $results_json;

		

	}
	
}