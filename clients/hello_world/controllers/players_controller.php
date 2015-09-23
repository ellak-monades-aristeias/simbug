<?php
class PlayersController extends AppController {

	var $name = 'Players';
	var $scaffold;
	
	function beforeFilter() {
		
		$this->Auth->allow('register');
	}
	
	
	
	//Auth login
	function login() {}
	
	//Auth logout
	function logout() {
	        $this->redirect($this->Auth->logout());
	    }
	}
	
	
?>