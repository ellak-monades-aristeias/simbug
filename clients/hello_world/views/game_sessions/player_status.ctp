
<div class="panel panel-default">
	<div class="panel-heading">
		<h3>
			<span class="glyphicon glyphicon-list-alt"></span> <?php  echo $data['GameSession']['name']; ?> <small>Game Session Status</small>
		</h3>
	</div>

	<div style="overflow-x: auto">
		
		
    <div id="accordion" class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">1. Make Decisions</a>
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
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">2. See Game Status</a>
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