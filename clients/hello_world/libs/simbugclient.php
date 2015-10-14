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
	 * @param unknown $gs
	 * @param unknown $player
	 */
	function getPlayerChoices($gs,$player) {
		$results = $this->HttpSocket->post($this->rest_server_url.'/getPlayerChoices/'.$gs['GameSession']['uuid'].'/'.$player['id']);
		
		App::import('Vendor','Services_JSON', array('file'=>'services_json.php'));
		$json = new Services_JSON();
		
		return $json->decode($results);
	}
	
	
	/**
	 * 
	 * @param array $gs The GameSession record 
	 * @return asnwer from server as JSON php array
	 */
	function advanceTurn($gs) {
		$results = $this->HttpSocket->post($this->rest_server_url.'/advanceTurn/'.$gs['GameSession']['uuid']);
		
		return $results;
	}
	
	
	/**
	 * 
	 * @param array $gs The GameSession record 
	 * @return asnwer from server as JSON php array
	 */
	function initGameSession($gs) {
		
		$sep='';$players_string='[';
		foreach($gs['Player'] as $pl) {
			$players_string.=$sep.'{"uuid":"';
			$players_string.=$pl['id'];
			$players_string.='"}';
			$sep=',';
		}
		$players_string.=']';
//pr($players_string);die;		
		//form data
		$data=array();
		$data['listOfPlayers'] = $players_string;
		$data['definitionString']=$gs['Game']['definition'];
		
		$results = $this->HttpSocket->post($this->rest_server_url.'/initGameSession/'.$gs['GameSession']['uuid'],$data);
//pr($results);die;		
//		$results_json = json_decode($results, TRUE);

		return $results;

		

	}
	
	
	function submitChoices($gs,$player,$choice) {
//pr($gs);pr($player);pr($choice);die;
		
		//construct chouces
		$sep='';$choices_string='{';
		foreach($choice as $k=>$v) {
			$choices_string.=$sep.'"'.$k.'":';
			$choices_string.='"'.$v.'"';
			$sep=',';
		}
		$choices_string.='}';
		
		//JsonString={"uuid": "56030487-0cf0-4f60-b10c-4d8a8fe9baf7", "choiceVariables": {"numberChoice": "10"}}
		$data=array();
		$data['jsonString'] = '{"uuid":"'.$player['id'].'","choiceVariables":'.$choices_string.'}';
//pr($data['JsonString']);die;		
		$results = $this->HttpSocket->post($this->rest_server_url.'/submitChoices/'.$gs['GameSession']['uuid'],$data);
//pr($data);pr($results);die;		
		return $results;
	}
	
}