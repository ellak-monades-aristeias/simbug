<?php
class GameSessionsController  extends AppController {

	var $name = 'GameSessions';
	var $scaffold;
	

	function submitChoice($gs_id=null) {
		
		if($this->data) {
//pr($this->data);die;			
			App::import('Lib','SimBugClient',array('file'=>'simbugclient.php'));
			$sc = new SimBugClient();
			$result=$sc->submitChoices($this->GameSession->find('first',array('recursive'=>-1,'conditions'=>array('GameSession.id'=>$gs_id))),
									   $this->Session->read('Auth.Player'),
									   $this->data['Decision']					
									);
			$this->Session->setFlash('An initializaion message was sent to the SIMBUG-SERVER. The response was:<pre>'.print_r($result,TRUE).'</pre>');
			
			$this->redirect('/game_sessions/player_status/'.$gs_id);
		}
		
		
		
		
	}

	function advanceRound($id) {
		$data = $this->GameSession->find('first',array('recursive'=>1,'conditions'=>array('GameSession.id'=>$id)));
		
		
		$this->set('data',$data);
	}
	
	
	
	function admin_status($id) {
		App::import('Lib','SimBugClient',array('file'=>'simbugclient.php'));
		$sc = new SimBugClient();
		
		
		$data = $this->GameSession->find('first',array('recursive'=>1,'conditions'=>array('GameSession.id'=>$id)));
		
		//find player decision
		foreach($data['Player'] as $k=>$p) {
			$cc = $sc->getPlayerChoices($data,$this->Session->read('Auth.Player'));
			$data['Player'][$k]['Decisions'] = $cc;
			//insert value to $data['Player'][$k]['Decisions'][xxx]
		}
		
		$this->set('data',$data);
	}
	
	function player_status($id) {
		$data = $this->GameSession->find('first',array('recursive'=>1,'conditions'=>array('GameSession.id'=>$id)));
		
		//load decisions, /getPlayerState/UUID_of_GAME_SESSION/UUID_of_PLAYER
		
		
		
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
		
		//if($result['status']=='ok') {
		//	$this->GameSession->id=$id;$this->GameSession->saveField('round',0);
		//}
		
		$this->Session->setFlash('An initializaion message was sent to the SIMBUG-SERVER. The response was:<pre>'.print_r($result,TRUE).'</pre>');
		
		$this->redirect('/game_sessions/admin_status/'.$id);

	}

	
}
	
	
?>