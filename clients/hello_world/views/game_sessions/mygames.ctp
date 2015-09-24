<div class="panel panel-default">
	<div class="panel-heading">
		<h3>
			<span class="glyphicon glyphicon-list-alt"></span> My Game Sessions
		</h3>
	</div>

	<div style="overflow-x: auto">


		<table class="table table-bordered table-hover table-condensed">
			<thead>
				<tr class="active">
					<th><a href="/_sites/simbug/clients/hello_world/game_sessions/index/page:1/sort:game_id/direction:asc">Game Type</a></th>
					<th><a href="/_sites/simbug/clients/hello_world/game_sessions/index/page:1/sort:round/direction:asc">Current Round</a></th>
					<th><a href="/_sites/simbug/clients/hello_world/game_sessions/index/page:1/sort:name/direction:asc">Session Name</a></th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<? foreach($data as $d): ?>
				<tr>
					<td><? echo $d['Game']['name']; ?></td>
					<td><? echo $d['round']; ?></td>
					<td><? echo $d['name']; ?></td>
					<td class="noactions"><nobr>
							<a href="<? echo $this->webroot; ?>game_sessions/player_status/<? echo $d['id']; ?>" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open"><span></a> 
					</td>
				</tr>
				<? endforeach; ?>
			</tbody>
		</table>
	</div>
	<div class="panel-footer">
		<p>Page 1 of 1, showing 1 records out of 1 total, starting on
			record 1, ending on 1</p>
		<ul class="pagination  pagination-sm">

			<li class="prev disabled"><a
				href="/_sites/simbug/clients/hello_world/game_sessions/index/page:1"><span
					class="glyphicon glyphicon-backward"></span>&nbsp;</a></li>
			<li class="active"><a
				href="/_sites/simbug/clients/hello_world/game_sessions/index/page:1">1</a></li>
			<li class="next disabled"><a
				href="/_sites/simbug/clients/hello_world/game_sessions/index/page:1"><span
					class="glyphicon glyphicon-forward"></span>&nbsp;</a></li>
		</ul>


	</div>
</div>