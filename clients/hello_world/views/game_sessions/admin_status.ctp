<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<link rel="stylesheet" href="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
<script src="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>



 <script src="<?php  echo $this->webroot; ?>js/xmltree.js"></script>
<link href="<?php  echo $this->webroot; ?>js/xmltree.css" rel="stylesheet">







<script>
$(document).ready(function() {
	new XMLTree({
		xml: $("#xml").html(),
		container: '#tree',
		startCollapsed: true
	});


	var data = {
			  // A labels array that can contain any sort of values
			  labels: <?php  echo $round_string; ?>,
			  // Our series array that contains series objects or in this case series data arrays
			  series: <?php  echo $series_string; ?>
			};

	new Chartist.Line('#choices_hist_chart', data);

	
});
</script>

 
				  


<div class="panel panel-default">
	<div class="panel-heading">
		<h3>
			<span class="glyphicon glyphicon-list-alt"></span> <?php  echo $data['GameSession']['name']; ?> 
			<small><?php  echo $data['GameSession']['uuid']; ?></small> 
			<small>Round <?php  echo $data['GameSession']['round']; ?></small>
			
			
			<!--  actions -->
			<div class="btn-group pull-right">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        Actions <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                         <li><a href="<?php  echo $this->webroot; ?>game_sessions/admin_status/<?php echo $data['GameSession']['id']; ?>" ><span class="glyphicon glyphicon-refresh"></span>Refresh Status</a></li>
                         <li><a href="<?php  echo $this->webroot; ?>game_sessions/initgamesession/<?php echo $data['GameSession']['id']; ?>" ><span class="glyphicon glyphicon-play-circle"></span>Initialize on REST Server</a></li>
                         <li><a href="<?php  echo $this->webroot; ?>game_sessions/advanceRound/<?php echo $data['GameSession']['id']; ?>" ><span class="glyphicon glyphicon-road"></span>Advance Round</a></li>
                         <li><a href="<?php  echo $this->webroot; ?>game_sessions/edit/<?php echo $data['GameSession']['id']; ?>" ><span class="glyphicon glyphicon-edit"></span>Edit</a></li>
                    </ul>
                </div>			
		</h3>
	</div>

	<div style="overflow-x: auto">
		
		
    <div id="accordion" class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">1. Current Round Decisions</a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse">
                <div class="panel-body">
                    <table class="table table-bordered table-condensed table-striped">
                        <thead>
                            <tr>
                                		<th>User</th>
										<th>Current Round Decisions</th>
		
                        </thead>
                        <tbody>
                   <?php  foreach($data['Player'] as $p): ?>
                   <tr><td><?php  echo $p['showName']; ?></td><td><?php  echo print_r($p['Decisions'], TRUE); ?></td></tr>
                   <?php  endforeach; ?>
                   </tbody>
				    </table>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">2. History of Decisions</a>
                </h4>
               
            </div>

            <div id="collapseTwo" class="panel-collapse collapse in">
                <div class="panel-body">
				  
				  <div id="choices_hist_chart" style="width:600px;height:300px"></div>
				  
				  <!-- //TODO, Line displaying data -->
				  
				   
				
				  
                </div>
                <div> <p><?php  pr($choices_hist);?> </p></div>
                
                
            </div>
        </div>
        
          <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">3. Statistics</a>
                </h4>
            </div>

            <div id="collapseThree" class="panel-collapse collapse in">
                <div class="panel-body">

                    <div> <p><?php  pr($state_hist);?> </p></div>

                </div>
            </div>
        </div>

    </div> <!--  finished accrdion -->

	
		<div class="panel-footer"><h3> Players </h3></div>   
		<div style="overflow-x: auto">
                    <table class="table table-bordered table-condensed table-striped">
                        <thead>
                            <tr>
                                		<th>Id</th>
										<th>Username</th>
		
                        </thead>
                        <tbody>
                        <?php  foreach($data['Player'] as $p): ?>
                          <tr>
							<td><?php  echo $p['id']; ?></td>
							<td><?php  echo $p['username']; ?></td>
			
						 </tr>
					    <?php  endforeach; ?>
		
                        </tbody>
                    </table>
         </div>
		     	
		
		<div class="panel-footer"><h3> Definition File </h3></div>   
		
		<div style="overflow-x: auto">
			<div id="tree"></div>
		</div>
		
		<div id="xml" style="display: none;"><?php echo $data['Game']['definition']; ?></div>

	</div>
	
	
</div>