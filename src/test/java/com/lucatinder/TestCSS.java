package com.lucatinder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestCSS {
	
	@Autowired
	private MockMvc mockMvc;
	
	//¿Por qué pelotas da error esto?? No lo entiendo, hay que solucionarlo
	@Test
	public void devuelveArchivoCss() throws Exception {
		
		this.mockMvc
		.perform(get("/CSS/gotham-rounded/EstiloPaginaInicio.css"))
		.andDo(print())
		.andExpect(status().isOk());
	}

}
