<?php
App::import('Lib','SimBugClient',array('file'=>'simbugclient.php'));

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
		
		//App::import('Lib','SimBugClient',array('file'=>'simbugclient.php'));
		$sc = new SimBugClient();
		$result=$sc->advanceTurn($data);
		
		if($result['status']=='ok') {
			$this->GameSession->id = $id;
			$this->GameSession->saveField('round',$data['GameSession']['round']+1);
		}
		
		$this->Session->setFlash('An initializaion message was sent to the SIMBUG-SERVER. The response was:<pre>'.print_r($result,TRUE).'</pre>');
		
		$this->redirect('/game_sessions/admin_status/'.$id);
		
		
		
	}
	
	/**
	 * //TODO
	 */
	function getAllPlayerDecisionsAsTsv() {
		
	}
	
	/**
	 * 
	 * @param unknown $id
	 */
	function admin_status($id) {
		
		$sc = new SimBugClient();
		
		$data = $this->GameSession->find('first',array('recursive'=>1,'conditions'=>array('GameSession.id'=>$id)));
//pr($data);die;		
		//find current player decision
		foreach($data['Player'] as $k=>$p) {
			$cc = $sc->getPlayerChoices($data,$p,$data['GameSession']['round']);
			if($cc['status']=='ok') {
				$data['Player'][$k]['Decisions'] = $cc['result'];
			}
			
			//get choices history
			$c = $sc->getPlayerChoicesAll($data,$p,$data['GameSession']['round']);
			$choices_hist[$p['id']] =  $c;
			
			//get state history
			$s = $sc->getPlayerStateAll($data,$p,$data['GameSession']['round']);
			$state_hist[$p['id']] =  $s;

		}
//die;		
		$this->set('data',$data);
		$this->set('choices_hist',$choices_hist);
		$this->set('state_hist',$state_hist);
	}
	
	function player_status($id) {
		$sc = new SimBugClient();
		
		$data = $this->GameSession->find('first',array('recursive'=>1,'conditions'=>array('GameSession.id'=>$id)));
		
		//get state history
		$s = $sc->getPlayerStateAll($data,$this->Session->read('Auth.Player'),$data['GameSession']['round']);
		$state_hist =  $s;
		
		
		$this->set('data',$data);
		$this->set('state_hist',$state_hist);
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
		//App::import('Lib','SimBugClient',array('file'=>'simbugclient.php'));
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