package gr.aua.simbug.tests;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class RhinoEngine
{

	@Before
	public void setUp() throws Exception
	{
	}

	// Crafted from https://www.informit.com/guides/content.aspx?g=java&seqNum=562
	// One has to download Rhino engine from
	// https://s3.amazonaws.com/github-cloud/releases/1739767/9da69d38-a7d5-11e4-8d73-d047133b2c20.zip?response-content-disposition=attachment%3B%20filename%3Drhino1_7R5.zip&response-content-type=application/octet-stream&AWSAccessKeyId=AKIAISTNZFOVBIJMK3TQ&Expires=1440721801&Signature=USzXxf0XaG6L%2FvqIfPi7HxoDqGo%3D
	// and add it to the classpath
	@Test
	public void testSimpleJsScript()
	{
		Integer alpha = 7;
		String script = "a=alpha; b=1; c=a+b;c; var arr= new Object(); arr['1'] = new Object(); arr['1']['2'] = new Object(); arr['1']['2']['3']=5;"
				+ "javaArray[0] + '-----'"
				+ ""
				+ ""
				+ ""
				+ ""
				+ "";
		script = "var INFO = new Object();";
		script += "INFO.num_players = 2;";
		script += "INFO.cur_turn = 3;";
		script += "INFO.players = new Object(); INFO";

		Context cx = Context.enter();

		String[] ar = { "Alpha", "Beta"};
		try
		{
			// Initialize the standard objects (Object, Function, etc.). This must be done before scripts can be
			// executed. The null parameter tells initStandardObjects
			// to create and return a scope object that we use
			// in later calls.
			Scriptable scope = cx.initStandardObjects();

			Object wrappedOut = Context.javaToJS(alpha, scope);
			ScriptableObject.putProperty(scope, "alpha", wrappedOut);
			Object o1 = Context.javaToJS(ar, scope);
			ScriptableObject.putProperty(scope, "javaArray", o1);
			
			Object obj = cx .evaluateString(scope, script, "TestScript", 1, null);
			System.out.println("Object: " + obj);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			// Exit the Context. This removes the association between the Context and the current thread and is an
			// essential cleanup action. There should be a call to exit for every call to enter.
			Context.exit();
		}
	}

	@Test
	public void testInfoScript()
	{
		String script = "var INFO = new Object();";
		script += "INFO.num_players = 2;";
		script += "INFO.cur_turn = 3;";
		script += "INFO.players = new Object();";
		script += "INFO.players[0] = '1';";
		script += "INFO.players[1] = '2';";
		script += "choice = new Object();";
		script += "choice['1'] = new Object();";
		script += "choice['1']['1'] = 3;";
		script ="var INFO = new Object();INFO.num_players = 3;INFO.cur_turn = 1;INFO.players = new Object();INFO.players[0] = 1;INFO.players[1] = 2;INFO.players[2] = 3;CONFIG = new Object();PLAYER_CHOICE_VARIABLES = new Object();PLAYER_CHOICE_VARIABLES[1] = new Object();PLAYER_CHOICE_VARIABLES[1]['1'] = new Object();PLAYER_CHOICE_VARIABLES[1]['1']['numberChoice'] = 23;PLAYER_CHOICE_VARIABLES[1]['2'] = new Object();PLAYER_CHOICE_VARIABLES[1]['2']['numberChoice'] = null;PLAYER_CHOICE_VARIABLES[1]['3'] = new Object();PLAYER_CHOICE_VARIABLES[1]['3']['numberChoice'] = null;";
		script += "PLAYER_STATE_VARIABLES = new Object();PLAYER_STATE_VARIABLES[1] = new Object();PLAYER_STATE_VARIABLES[1]['1'] = new Object();PLAYER_STATE_VARIABLES[1]['1']['roundDistanceFromAverage'] = null;PLAYER_STATE_VARIABLES[1]['1']['roundRank'] = null;PLAYER_STATE_VARIABLES[1]['1']['overallScore'] = null;PLAYER_STATE_VARIABLES[1]['2'] = new Object();PLAYER_STATE_VARIABLES[1]['2']['roundDistanceFromAverage'] = null;PLAYER_STATE_VARIABLES[1]['2']['roundRank'] = null;PLAYER_STATE_VARIABLES[1]['2']['overallScore'] = null;PLAYER_STATE_VARIABLES[1]['3'] = new Object();PLAYER_STATE_VARIABLES[1]['3']['roundDistanceFromAverage'] = null;PLAYER_STATE_VARIABLES[1]['3']['roundRank'] = null;PLAYER_STATE_VARIABLES[1]['3']['overallScore'] = null;";
		script += "WORLD_STATE_VARIABLES = new Object();WORLD_STATE_VARIABLES[1] = new Object();WORLD_STATE_VARIABLES[1]['averageAllPlayers'] = 22;";
		script += "var RESULTS = new Object(); RESULTS['world'] = new Object; RESULTS['player'] = new Object; RESULTS['world']['averageAllPlayers'] =22;";
		script += "RESULTS['player']['roundDistanceFromAverage'] = new Object(); RESULTS['player']['roundDistanceFromAverage']['1']=11; RESULTS['player']['roundDistanceFromAverage']['2']=11; RESULTS['player']['roundDistanceFromAverage']['3']=11;";
		//script += "var myJsonString = JSON.stringify(INFO);";
		script += "RESULTS;";
		
		script = "var INFO = new Object();INFO['num_players'] = 2;INFO['cur_turn'] = 1;INFO['players'] = new Object();INFO['players'][0] = '56030487-0cf0-4f60-b10c-4d8a8fe9baf7';INFO['players'][1] = '561dec18-8d0c-41f9-833b-1b848fe9baf7';";
		script += "	CONFIG = new Object();PLAYER_CHOICE_VARIABLES = new Object();PLAYER_CHOICE_VARIABLES['numberChoice'] = new Object();PLAYER_CHOICE_VARIABLES['numberChoice']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'] = new Object();";
		script += "PLAYER_CHOICE_VARIABLES['numberChoice']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'][0] = null;PLAYER_CHOICE_VARIABLES['numberChoice']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'][1] = null;";
		script += "PLAYER_CHOICE_VARIABLES['numberChoice']['561dec18-8d0c-41f9-833b-1b848fe9baf7'] = new Object();PLAYER_CHOICE_VARIABLES['numberChoice']['561dec18-8d0c-41f9-833b-1b848fe9baf7'][0] = null;";
		script += "PLAYER_CHOICE_VARIABLES['numberChoice']['561dec18-8d0c-41f9-833b-1b848fe9baf7'][1] = null;PLAYER_STATE_VARIABLES = new Object();PLAYER_STATE_VARIABLES['roundDistanceFromAverage'] = new Object();";
		script += "PLAYER_STATE_VARIABLES['roundDistanceFromAverage']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'] = new Object();PLAYER_STATE_VARIABLES['roundDistanceFromAverage']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'][0] = null;";
		script += "PLAYER_STATE_VARIABLES['roundDistanceFromAverage']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'][1] = null;PLAYER_STATE_VARIABLES['roundDistanceFromAverage']['561dec18-8d0c-41f9-833b-1b848fe9baf7'] = new Object();";
		script += "PLAYER_STATE_VARIABLES['roundDistanceFromAverage']['561dec18-8d0c-41f9-833b-1b848fe9baf7'][0] = null;PLAYER_STATE_VARIABLES['roundDistanceFromAverage']['561dec18-8d0c-41f9-833b-1b848fe9baf7'][1] = null;";
		script += "PLAYER_STATE_VARIABLES['roundRank'] = new Object();PLAYER_STATE_VARIABLES['roundRank']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'] = new Object();PLAYER_STATE_VARIABLES['roundRank']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'][0] = null;";
		script += "PLAYER_STATE_VARIABLES['roundRank']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'][1] = null;PLAYER_STATE_VARIABLES['roundRank']['561dec18-8d0c-41f9-833b-1b848fe9baf7'] = new Object();";
		script += "PLAYER_STATE_VARIABLES['roundRank']['561dec18-8d0c-41f9-833b-1b848fe9baf7'][0] = null;PLAYER_STATE_VARIABLES['roundRank']['561dec18-8d0c-41f9-833b-1b848fe9baf7'][1] = null;PLAYER_STATE_VARIABLES['overallScore'] = new Object();";
		script += "PLAYER_STATE_VARIABLES['overallScore']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'] = new Object();PLAYER_STATE_VARIABLES['overallScore']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'][0] = 4;";
		script += "PLAYER_STATE_VARIABLES['overallScore']['56030487-0cf0-4f60-b10c-4d8a8fe9baf7'][1] = 3;PLAYER_STATE_VARIABLES['overallScore']['561dec18-8d0c-41f9-833b-1b848fe9baf7'] = new Object();";
		script += "PLAYER_STATE_VARIABLES['overallScore']['561dec18-8d0c-41f9-833b-1b848fe9baf7'][0] = 2;PLAYER_STATE_VARIABLES['overallScore']['561dec18-8d0c-41f9-833b-1b848fe9baf7'][1] = 6;";
		script += "WORLD_STATE_VARIABLES = new Object();WORLD_STATE_VARIABLES['averageAllPlayers'] = new Object();WORLD_STATE_VARIABLES['averageAllPlayers'][0] = null;WORLD_STATE_VARIABLES['averageAllPlayers'][1] = null;RESULTS = new Object();";
		script += "RESULTS['WorldStateVariables'] = new Object();RESULTS['PlayerStateVariables'] = new Object();RESULTS['PlayerStateVariables']['roundDistanceFromAverage'] = new Object();";
		script += "RESULTS['PlayerStateVariables']['roundRank'] = new Object();RESULTS['PlayerStateVariables']['overallScore'] = new Object();";
	  	
		//1. calculate average of all players (world level)
		script += "sum=0;";
		script += "for (i = 0; i < INFO['num_players']; i++) {";
		script += "player_id = INFO['players'][i][INFO['cur_turn']];";
		script += "sum = sum + PLAYER_CHOICE_VARIABLES['numberChoice'][player_id];";
		script += "}";	
		script += "avg_all = sum/INFO['num_players'];";
		script += "RESULTS['WorldStateVariables']['averageAllPlayers'] = avg_all;";

//2. calculate distance of each player
		script += "distance = new Array(INFO['num_players']);";
		script += "for (i = 0; i < INFO['num_players']; i++) {";
		script += "	player_id = INFO['players'][i][INFO['cur_turn']];";
		script += "	distance[i] = Math.abs(avg_all - PLAYER_CHOICE_VARIABLES['numberChoice'][player_id]);";
		script += "	RESULTS['PlayerStateVariables']['roundDistanceFromAverage'][player_id] = distance[i];";
		script += "}";

		//3. compute rank of each player
	script += "rank = new Array(INFO['num_players']);";
	script += "for (i = 0; i < INFO['num_players']; i++) {rank[i]=i;}";

	script += "rank.sort(function (a, b) { return distance[a] < distance[b] ? 1 : distance[a] > distance[b] ? -1 : 0; });";
	script += "for (i = 0; i < INFO['num_players']; i++) {";
	script += "	player_id = INFO['players'][i];";
	script += "	RESULTS['PlayerStateVariables']['roundRank'][player_id] = rank[i];";

	script += "if(INFO['cur_turn']>0) {";
	//script += "	RESULTS['PlayerStateVariables']['overallScore'][player_id] = rank[i] + PLAYER_STATE_VARIABLES['overallScore'][player_id][INFO['cur_turn']-1];";
	script += "	RESULTS['PlayerStateVariables']['overallScore'][player_id] = rank[i]+ PLAYER_STATE_VARIABLES['overallScore'][player_id][0];";
	script += "}";
	script += "else {RESULTS['PlayerStateVariables']['overallScore'][player_id] = rank[i];}";
	script += "}";
		
	script += "RESULTS;";

		Object obj = runScript(script);
		System.out.println("Object: " + obj);
		handleResults((NativeObject)obj);
	}
	
	private Object runScript(String script) 
	{
		Context cx = Context.enter();
		try
		{
			Scriptable scope = cx.initStandardObjects();
			Object obj = cx .evaluateString(scope, script, "TestScript", 1, null);
			
			return obj;

           //Scriptable res = (Scriptable) scope.get("obj", scope);
			//System.out.println("INFO.num_players: " + res.get("num_players", res));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			Context.exit();
		}
		return null;
	}

	private void handleResults(NativeObject res) 
	{
		for (Entry<Object, Object> p : res.entrySet()) 
		{
			NativeObject r1 = (NativeObject)p.getValue();
            for (Entry<Object, Object> p1 : r1.entrySet()) 
            {
				if (p.getKey().equals("world"))
				{
					System.out.println(p.getKey() + ": " + p1.getKey() + ": " + p1.getValue());
				}
				if (p.getKey().equals("player"))
				{
					NativeObject r2 = (NativeObject)p1.getValue();
		            for (Entry<Object, Object> p2 : r2.entrySet()) 
		            {
						System.out.println(p.getKey() + ": " + p1.getKey() + ": " + p2.getKey() + ": " + p2.getValue());			            	
		            }	            	
	            }
			}

		}
	}

	@Test
	public void testSimpleJsScript2()
	    {
	        Context cx = Context.enter();
	        try {
	            // Set version to JavaScript1.2 so that we get object-literal style
	            // printing instead of "[object Object]"
	            cx.setLanguageVersion(Context.VERSION_1_2);

	            // Initialize the standard objects (Object, Function, etc.)
	            // This must be done before scripts can be executed.
	            Scriptable scope = cx.initStandardObjects();

	            // Now we can evaluate a script. Let's create a new object
	            // using the object literal notation.
	            String script = "res={world:{'A':'1', 'B':'2'}, players:{'1':{'C':'3'}, '2':{'D':'4'} } }";
	            Object result1 = cx.evaluateString(scope, script, "MySource", 1, null);
	            Scriptable world = (Scriptable) scope.get("res", scope);
	            System.out.println(world);
	            NativeObject result2 = (NativeObject)result1;
	            for (Entry<Object, Object> p : result2.entrySet()) 
	            {
                    System.out.println(p.getKey() + ": " + p.getValue());
	            	if (p.getKey().equals("world"))
	            	{
	            		NativeObject r1 = (NativeObject)p.getValue();
	            		for (Entry<Object, Object> p1 : r1.entrySet()) 
	    	            {
	            			System.out.println(p1.getKey() + ": " + p1.getValue());
	    	            }
	            		
	            	}
	            	if (p.getKey().equals("players"))
	            	{
	            		NativeObject r1 = (NativeObject)p.getValue();
	            		for (Entry<Object, Object> p1 : r1.entrySet()) 
	    	            {
	            			System.out.println(p1.getKey() + ": " + p1.getValue());
	            			NativeObject r2 = (NativeObject)p1.getValue();
		            		for (Entry<Object, Object> p2 : r2.entrySet()) 
		    	            {
		            			System.out.println(p2.getKey() + ": " + p2.getValue());
		    	            }
	    	            }
	            		
	            	}
                }
	            
	            
	            Object result = cx.evaluateString(scope, "obj = {a:1, b:['x','y']}", "MySource", 1, null);

	            Scriptable obj = (Scriptable) scope.get("obj", scope);

	            // Should print "obj == result" (Since the result of an assignment
	            // expression is the value that was assigned)
	            System.out.println("obj " + (obj == result ? "==" : "!=") + " result");

	            // Should print "obj.a == 1"
	            System.out.println("obj.a == " + obj.get("a", obj));

	            Scriptable b = (Scriptable) obj.get("b", obj);

	            // Should print "obj.b[0] == x"
	            System.out.println("obj.b[0] == " + b.get(0, b));

	            // Should print "obj.b[1] == y"
	            System.out.println("obj.b[1] == " + b.get(1, b));

	            // Should print {a:1, b:["x", "y"]}
	            //Function fn = (Function) ScriptableObject.getProperty(obj, "toString");
	            //System.out.println(fn.call(cx, scope, obj, new Object[0]));
	        } finally {
	            Context.exit();
	        }
	    }
	   
	public static void jsStaticFunction_test(NativeObject obj) 
	{
	    HashMap<String, String> mapParams = new HashMap<String, String>();

	    if(obj != null) 
	    {
	        Object[] propIds = NativeObject.getPropertyIds(obj);
	        for(Object propId: propIds) {
	            String key = propId.toString();
	            String value = NativeObject.getProperty(obj, key).toString();
	            mapParams.put(key, value);
	            System.out.println("key: " + key + " - value: " + value); 
	        }
	    }
	    //work with mapParams next..
	}
	
	@Test
	public void testRegex()
	{
		String poem = "In software, a stack overflow [apple] occurs"
                + " when too much memory [orange] is used on the call stack [banana]."
                + " The call stack [pear] contains a limited amount of memory,"
                + " often determined at the start of the program [apple].";

        String pattern = "\\[(?<=\\[)(\\w+)(?=])\\]";
        poem = poem.replaceAll(pattern, "<img src='$1.jpg' />");

        System.out.println(poem);
        System.out.println("---------------------------------");

         Pattern p = Pattern.compile("(\\[)(\\w*)(\\])");
        Matcher m = p.matcher(poem);
        //poem = m.replaceAll("<img src='$2.jpg' />");
        System.out.println(poem);

	}
	
	/*
	 * 
	iterators seem to be the key!
	
	if you want to iterate over all entries of a map, you could do the following
	
	JAVA
	
	//pass  the map and map.keySet().iterator() to the javascript
	Object wrappedParameterMap = Context.javaToJS(parameterMap, scope);
	ScriptableObject.putProperty(scope, "parameterMap", wrappedParameterMap);
	Object wrappedParameterNames = Context.javaToJS(parameterMap.keySet().iterator(), scope);
	ScriptableObject.putProperty(scope, "parameterNames", wrappedParameterNames);
	
	JAVASCRIPT
	
	while(parameterNames.hasNext()) {
	  key = parameterNames.next();
	  value = parameterMap.get(key);
	}

*/
	
}
