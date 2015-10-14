<?php
class GameSessionsController  extends AppController {

	var $name = 'GameSessions';
	var $scaffold;
	


	function advanceRound($id) {
		$data = $this->GameSession->find('first',array('recursive'=>1,'conditions'=>array('GameSession.id'=>$id)));
		
		
		$this->set('data',$data);
	}
	
	
	
	function admin_status($id) {
		$data = $this->GameSession->find('first',array('recursive'=>1,'conditions'=>array('GameSession.id'=>$id)));
		
		
		$this->set('data',$data);
	}
	
	function player_status($id) {
		$data = $this->GameSession->find('first',array('recursive'=>1,'conditions'=>array('GameSession.id'=>$id)));
		
		
		$this->set('data',$data);
	}
	
	
	function mygames() {
		$player_id = $this->Session->read('Auth.Player.id');
		if($player_id) {
			$data = $this->GameSession->Player->find('first',array('recursive'=>2,'conditions'=>array('Player.id'=>$player_id)));
			$this->set('data',$data['GameSession']);
		}
		else {
			$this->redirect('/pages/home');
		}
		
	}
	
	
	function initgamesession($id) {
		App::import('Lib','SimBugClient',array('file'=>'simbugclient.php'));
		$sc = new SimBugClient();
		$result=$sc->initGameSession($this->GameSession->find('first',array('conditions'=>array('GameSession.id'=>$id))));
		$this->set('results',$result);
	}

	
}
	
	
?>