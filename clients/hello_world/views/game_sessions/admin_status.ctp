
<div class="panel panel-default">
	<div class="panel-heading">
		<h3>
			<span class="glyphicon glyphicon-list-alt"></span> <?php  echo $data['GameSession']['name']; ?> <small>ADMIN Game Session Status</small>
			
			
			<!--  actions -->
			<div class="btn-group pull-right">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        Actions <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <?php
                       
                        echo "\t\t<li>";
                        echo $this->Html->link( sprintf(__d('cake', '<span class="glyphicon glyphicon-road"></span> Advance Round',true)), array('action' => 'advanceRound', $data['GameSession']['id']), array('escape' => false));
                        echo " </li>\n";
                        
                       
                        ?>
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
                    
                    <p>Decisions</p>

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

                    <p>Statistics</p>

                </div>
            </div>
        </div>
        
          <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">3. Statistics</a>
                </h4>
            </div>

            <div id="collapseTwo" class="panel-collapse collapse in">
                <div class="panel-body">

                    <p>Statistics</p>

                </div>
            </div>
        </div>

    </div> <!--  finished accrdion -->

		
		


	</div>
	
</div>