
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
                    
                    <form method="POST" action="<?php  echo $this->webroot; ?>game_sessions/submitChoice/<?php echo $data['GameSession']['id']; ?>">
                    	<div class="form-group">
                            <label class="col-sm-2 control-label" for="id561df43ff3ca2">Give a number from 1-100</label>
                            <div class="col-sm-6">
                                <input type="text" maxlength="255" empty="" class="form-control" id="id561df43ff3ca2" name="data[Decision][numberChoice]">  
                            </div>
                        </div>
                        <div class="form-group">
			                <div class="col-sm-offset-2 col-sm-2">
			                    <input type="submit" value="Take Decision" class="btn btn-primary btn-lg">
			                </div>
			            </div>
                        
                    </form>

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