package gr.aua.simbug.tests;

import org.junit.Before;
import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class RhinoEngine {

	@Before
	public void setUp() throws Exception {
	}

	//Crafted from https://www.informit.com/guides/content.aspx?g=java&seqNum=562
	//One has to download Rhino engine from 
	//https://s3.amazonaws.com/github-cloud/releases/1739767/9da69d38-a7d5-11e4-8d73-d047133b2c20.zip?response-content-disposition=attachment%3B%20filename%3Drhino1_7R5.zip&response-content-type=application/octet-stream&AWSAccessKeyId=AKIAISTNZFOVBIJMK3TQ&Expires=1440721801&Signature=USzXxf0XaG6L%2FvqIfPi7HxoDqGo%3D
	//and add it to the classpath
	@Test
	public void testSimpleJsScript() {

		String script = "a=1; b=1; c=a+b;c;";

		Context cx = Context.enter();

		try
		{
			// Initialize the standard objects (Object, Function, etc.). This must be done before scripts can be
			// executed. The null parameter tells initStandardObjects 
			// to create and return a scope object that we use
			// in later calls.
			Scriptable scope = cx.initStandardObjects();

			Object obj = cx.evaluateString( scope, script, "TestScript", 1, null );
			System.out.println( "Object: " + obj );


		} 
		catch( Exception e )
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

}
