<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>SIMBUG Client</title>

    <!-- Bootstrap -->
    <link href="<?php  echo $this->webroot; ?>css/bootstrap.min.css" rel="stylesheet">
    
     <!-- Custom -->
    <link href="<?php  echo $this->webroot; ?>css/custom.css" rel="stylesheet">
    

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    

  </head>
  <body role="document">
	
    <!-- Fixed navbar -->
    <?php echo $this->element('navbar'); ?>
  
  	<?php $a=$session->flash();if($a): ?>
	 <div class="container" role="messages" >
	  	 <div class="row">
	  	 	 <div class="col-xs-1"></div>
	  	 	 <div class="col-xs-10 modal-content">
	  		<h2>Messages:</h2>
	  		<?php echo $a; ?>
	  		</div> 
	  		<div class="col-xs-1"></div>
	  	 </div>    
    </div>	
	<?php endif; ?>
  	
  	
  	 <div class="container" role="main" >
	  	 <div class="row">
	  	 	 <div class="col-xs-12">
	  		<?php echo $content_for_layout; ?> 
	  		</div>
	  	 </div>    
    </div>
    
     <div class="container" role="sql" >
	  	 <div class="row">
	  	 	 <div class="col-xs-12">
	  		<?php echo $this->element('sql_dump'); ?>
	  		</div>
	  	 </div>    
    </div>
    
    
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<?php  echo $this->webroot; ?>js/bootstrap.min.js"></script>
  </body>
</html>