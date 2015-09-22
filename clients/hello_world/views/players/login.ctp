<h1>Player Login</h1>
<?php
    echo $this->Session->flash('auth');
    echo $this->Form->create('Player', array('div' => 'form-group'));
    echo $this->Form->input('username', array('div' => 'form-group'));
    echo $this->Form->input('password', array('div' => 'form-group'));
    echo $this->Form->end('Login');
?>