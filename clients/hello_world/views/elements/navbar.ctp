<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">SIMBUG Client</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="<?php  echo $this->webroot; ?>pages/home">Home</a></li>
            <? if($this->Session->read('Auth.Player')): ?>
            	<li><a href="#">Logged in as <? echo $this->Session->read('Auth.Player.username'); ?></a></li>
            	<? if($this->Session->read('Auth.Player.isAdmin')==1): ?>
            		<li><a href="<?php  echo $this->webroot; ?>games">Games</a></li>
            		<li><a href="<?php  echo $this->webroot; ?>game_sessions/index">Game Sessions</a></li>
            		<li><a href="<?php  echo $this->webroot; ?>players">Players</a></li>
            	<? else: ?>
            		 <li><a href="<?php  echo $this->webroot; ?>game_sessions/mygames">My Games</a></li>
            	<? endif; ?>
            	<li><a href="<?php  echo $this->webroot; ?>players/logout">Logout</a></li>
            <? else: ?>
            <li><a href="<?php  echo $this->webroot; ?>players/login">Login</a></li>
            <? endif; ?>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
