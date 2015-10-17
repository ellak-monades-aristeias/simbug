		//1. calculate average of all players (world level)
		sum=0;
		for (i = 0; i < INFO['num_players']; i++) {
			player_id = INFO['players'][i][INFO['cur_turn']];
		    sum = sum + PLAYER_CHOICE_VARIABLES['numberChoice'][player_id];
		}	
		avg_all = sum/INFO['num_players'];
		RESULTS['WorldStateVariables']['averageAllPlayers'] = avg_all;
		
		//2. calculate distance of each player
		distance = new Array(INFO['num_players']);
		for (i = 0; i < INFO['num_players']; i++) {
			player_id = INFO['players'][i][INFO['cur_turn']];
			distance[i] = Math.abs(avg_all - PLAYER_CHOICE_VARIABLES['numberChoice'][player_id]);
			RESULTS['PlayerStateVariables']['roundDistanceFromAverage'][player_id] = distance[i];
		}	 
		
		//3. compute rank of each player
		rank = new Array(INFO['num_players']);
		for (i = 0; i < INFO['num_players']; i++) {rank[i]=i;}
		
		rank.sort(function (a, b) { return distance[a] < distance[b] ? 1 : distance[a] > distance[b] ? -1 : 0; });
		for (i = 0; i < INFO['num_players']; i++) {
			RESULTS['PlayerStateVariables']['roundRank'][player_id] = rank[i];
		}
		
		if(INFO['cur_turn']>0) {
			RESULTS['PlayerStateVariables']['overallScore'][player_id] = rank[i] + PLAYER_STATE_VARIABLES['overallScore'][player_id][INFO['cur_turn']-1];
		}
		else {RESULTS['PlayerStateVariables']['overallScore'][player_id] = rank[i];}