<?php
class AppController extends Controller {
	
	
	var $components = array('Auth'=>array('userModel'=>'Player', 'loginRedirect'=>'/pages/home' ) ,'Session');
	var $helpers = array('Session','Form' => array('className' => 'BootstrapForm'));
	
	
	function beforeFilter(){
		$this->Auth->allow('display', 'view');
	}
	
	
	
}
?>