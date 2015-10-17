<?php

App::import('HttpSocket');
App::import('Vendor','Services_JSON', array('file'=>'services_json.php'));

class SimBugClient extends Object {
	
	private $HttpSocket = null; 
	//private $rest_server_url = "http://infostorm.aua.gr:8080/simbug-server";
	private $rest_server_url = "http://quijote.aua.gr:8080/simbug-server";
	private $json ;
	
	
	
	function __construct() {
		$this->HttpSocket = new HttpSocket();
		$this->json = new Services_JSON(SERVICES_JSON_LOOSE_TYPE);
	}
	
	function __destruct() {
		$this->HttpSocket->disconnect();
	}
	
	function advanceTurn($gs) {
//pr($this->rest_server_url.'/advanceTurn/'.$gs['GameSession']['uuid']);die;		
		$results = $this->HttpSocket->post($this->rest_server_url.'/advanceTurn/'.$gs['GameSession']['uuid']);
		return $results;
	}
	
	/**
	 * 
	 * @param unknown $gs
	 * @param unknown $player
	 */
	function getPlayerChoices($gs,$player,$turn) {
pr(($this->rest_server_url.'/getPlayerChoices/'.$gs['GameSession']['uuid'].'/'.$player['id']));		
		$results = $this->HttpSocket->post($this->rest_server_url.'/getPlayerChoices/'.$gs['GameSession']['uuid'].'/'.$player['id'].'/'.$turn);
		//$results = $this->HttpSocket->post($this->rest_server_url.'/getPlayerChoices/'.$gs['GameSession']['uuid'].'/'.$player['id']);
pr($results);		
		
		//$json = new Services_JSON(SERVICES_JSON_LOOSE_TYPE);
//pr($json->decode($results))	;	
		return $this->json->decode($results);
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
		
//pr($this->rest_server_url.'/initGameSession/'.$gs['GameSession']['uuid']);pr($data);die;

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