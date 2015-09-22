<?php
class AppController extends Controller {
	
	
	var $components = array('Auth'=>array('userModel'=>'Player' ) ,'Session');
	
	
	//$this->Auth->fields = array('username' => 'email', 'password' => 'passwd');
	
	
	function beforeFilter(){
		$this->Auth->allow('display', 'view');
	}
	
	
	
}
?>