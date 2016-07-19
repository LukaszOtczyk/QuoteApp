package QuoteApp.QuoteApp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("quoteapp-servlet.xml")
public class QuoteAppControllerTest {

	@Autowired
	WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void getLastQute() throws Exception {
		this.mockMvc.perform(get("/get-last-quote"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.author").exists())
		.andExpect(jsonPath("$.content").exists());
	}
	
	@Test
	public void getRandomQute() throws Exception {
		this.mockMvc.perform(get("/get-random-quote"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.author").exists())
		.andExpect(jsonPath("$.content").exists());
	}
	
	@Test
	@Transactional
	public void addQuoteAndGetLast() throws Exception {
		final String author = "Arthur Luther";
		final String content = "Some quote";
		
		mockMvc.perform(post("/quote").param("author", author)
				.param("content", content))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().is3xxRedirection());
		
		this.mockMvc.perform(get("/get-last-quote"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.author").value(author))
		.andExpect(jsonPath("$.content").value(content));
	}
}
