
<script>
//definitions
WORLD_STATE_VARIABLES = [];WORLD_STATE_VARIABLES["Phase"]=[];
WORLD_STATE_VARIABLES["Phase"][0]=1;

INFO=[];INFO["players"]=[];
INFO["num_players"]=5;INFO["cur_turn"]=0;

PLAYER_CHOICE_VARIABLES=[];
PLAYER_CHOICE_VARIABLES["LandtoRentIn"]=[];
PLAYER_CHOICE_VARIABLES["LandtoRentInWTP"]=[];
PLAYER_CHOICE_VARIABLES["LandtoRentOut"]=[];
PLAYER_CHOICE_VARIABLES["LandtoRentOutWTA"]=[];

PLAYER_STATE_VARIABLES=[];
PLAYER_STATE_VARIABLES["LandOwned"]=[];
PLAYER_STATE_VARIABLES["Liquidity"]=[];

//initial values
for(var i=1;i<11;i++){
INFO["players"][i]=[];INFO["players"][i][0]=i;
var v1=Math.random();var v2=Math.random();
PLAYER_CHOICE_VARIABLES["LandtoRentIn"][i]=(v1>0.5?v2*20:0);
PLAYER_CHOICE_VARIABLES["LandtoRentInWTP"][i]=(v1>0.5?v2*2000:0);
PLAYER_CHOICE_VARIABLES["LandtoRentOut"][i]=(v1>0.5?0:v2*20);
PLAYER_CHOICE_VARIABLES["LandtoRentOutWTA"][i]=(v1>0.5?0:v2*150);

PLAYER_STATE_VARIABLES["LandOwned"][i]=[];PLAYER_STATE_VARIABLES["Liquidity"][i]=[];
PLAYER_STATE_VARIABLES["LandOwned"][i][0]=100;
PLAYER_STATE_VARIABLES["Liquidity"][i][0]=10000;
}


//code

/**
 * Returns a random integer between min (inclusive) and max (inclusive)
 * Using Math.round() will give you a non-uniform distribution!
 */
function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}


var turn=INFO['cur_turn'];
if(WORLD_STATE_VARIABLES['Phase'][turn]==1) {
	//run land market
	var lm_iterations=INFO['num_players']*3;
    
    
 
    	for(var i=1;i<=lm_iterations;i++) {
        		//construct buyers-sellers arrays
				var buyers=[];var sellers=[];
				for(var j=0;j<INFO['num_players'];j++) {
					if(PLAYER_CHOICE_VARIABLES["LandtoRentIn"][j]>0){buyers.push(INFO["players"][j][turn]);}
					if(PLAYER_CHOICE_VARIABLES["LandtoRentOut"][j]>0){sellers.push(INFO["players"][j][turn]);}
				}
				if(buyers.length==0||sellers.length==0){break;}
        
				b=buyers[getRandomInt(0,buyers.length)];
                s=sellers[getRandomInt(0,sellers.length)];
                
                if(PLAYER_CHOICE_VARIABLES["LandtoRentInWTP"][b]>
                	PLAYER_CHOICE_VARIABLES["LandtoRentOutWTA"][s]) {
                    
                    var land=(PLAYER_CHOICE_VARIABLES["LandtoRentIn"][b]<PLAYER_CHOICE_VARIABLES["LandtoRentOut"][s]?PLAYER_CHOICE_VARIABLES["LandtoRentIn"][b]:PLAYER_CHOICE_VARIABLES["LandtoRentOut"][s]);
                    var price=PLAYER_CHOICE_VARIABLES["LandtoRentOutWTA"][s]+
                    	(PLAYER_CHOICE_VARIABLES["LandtoRentInWTP"][b]-PLAYER_CHOICE_VARIABLES["LandtoRentOutWTA"][s])*Math.random();
                    
                    //update variables
                    PLAYER_CHOICE_VARIABLES["LandtoRentIn"][b]=-land;
                    PLAYER_CHOICE_VARIABLES["LandtoRentOut"][s]=-land;
                    
                    RESULTS['PlayerStateVariables']['Liquidity'][s] =+ price*land;
                    RESULTS['PlayerStateVariables']['Liquidity'][b] =- price*land;
                    
                    RESULTS['PlayerStateVariables']['LandToCultivate'][b] = PLAYER_STATE_VARIABLES["LandOwned"][b][turn]+land;
                    RESULTS['PlayerStateVariables']['LandToCultivate'][s] = PLAYER_STATE_VARIABLES["LandOwned"][s][turn]-land;
                }
                	
    	}
        
    
    
}
else {
	//run production realization
}

</script>
</p>
