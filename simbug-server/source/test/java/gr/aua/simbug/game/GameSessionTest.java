package gr.aua.simbug.game;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import gr.aua.simbug.controller.GameSessionController;
import gr.aua.simbug.utils.SimbugUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class GameSessionTest {


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

	@Test
	public void creatListOfPlayers() throws Exception
	{
		String listOfPlayers = "[{\"uuid\":\"1\"}]";
		GameSession gs = new GameSession();
		//gs.createListOfPlayers(listOfPlayers);
	}

}
