/**
 * 
 */
package gr.aua.simbug.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import gr.aua.simbug.utils.SimbugUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author michael
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class GameSessionControllerTest
{

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Configuration
	@EnableWebMvc
	public static class TestConfiguration
	{
		@Bean
		public GameSessionController gameSessionController()
		{
			return new GameSessionController();
		}
	}

	/**
	 * Test method for {@link gr.aua.simbug.controller.GameSessionController#initGameSession(java.lang.String)}.
	 */
	@Test
	public void testInitGameSession() throws Exception
	{
	    Integer UuidOfGameSession = 1;
/*	    mockMvc.perform(get("/initGameSession/{UuidOfGameSession}",UuidOfGameSession)
	    		.accept(MediaType.APPLICATION_JSON))
		        .andDo(print()) // print the request/response in the console
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.TEXT_PLAIN))
		        .andExpect(content().string(UuidOfGameSession.toString()));	    
*/	    
		String definitionFile = "source/test/java/gr/aua/simbug/tests/hello_world.def.xml";
		String definitionString = SimbugUtils.fileToString(definitionFile);
		String listOfPlayers = "[{\"uuid\":\"1\"}, {\"uuid\":\"2\"}, {\"uuid\":\"3\"}]";
		
		MockHttpServletRequestBuilder initGameSession = post("/initGameSession/{UuidOfGameSession}",UuidOfGameSession)
				.param("definitionString", definitionString)
				.param("listOfPlayers", listOfPlayers);
	    mockMvc.perform(initGameSession)
        .andDo(print()) // print the request/response in the console
        .andExpect(status().isOk())
        .andExpect(content().string(UuidOfGameSession.toString()));	    

/*	    mockMvc.perform(
	    		post("/initGameSession/{UuidOfGameSession}",UuidOfGameSession)
	    			.contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"grumpy\",\"cuteness\":11}"))
		        .andDo(print()) // print the request/response in the console
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.TEXT_PLAIN))
		        .andExpect(content().string(UuidOfGameSession.toString()));	    

*/	}

	/**
	 * Test method for {@link gr.aua.simbug.controller.GameSessionController#advanceTurn(java.lang.String)}.
	 */
	@Test
	public void testAdvanceTurn()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link gr.aua.simbug.controller.GameSessionController#submitChoices(java.lang.String)}.
	 */
	@Test
	public void testSubmitChoices()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link gr.aua.simbug.controller.GameSessionController#getWorldState(java.lang.String)}.
	 */
	@Test
	public void testGetWorldState()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link gr.aua.simbug.controller.GameSessionController#getPlayerState(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetPlayerState()
	{
		fail("Not yet implemented");
	}

}
