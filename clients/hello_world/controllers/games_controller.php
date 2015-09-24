<?php 

class GamesController extends AppController {
	
	
	var $name = 'Games';
	var $scaffold;
	
	

	function test() {
		$d = $this->Game->find('all');
		$this->Session->setFlash('<pre>'.print_r($d,true).'</pre>');
		$this->redirect('/games');
	
	}


}
?>