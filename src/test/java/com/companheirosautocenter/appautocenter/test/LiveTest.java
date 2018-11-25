package com.companheirosautocenter.appautocenter.test;

import static org.apache.commons.lang3.RandomStringUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.companheirosautocenter.appautocenter.domain.Veiculo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppautocenterApplicationTests.class }, webEnvironment = WebEnvironment.DEFINED_PORT)*/
public class LiveTest {

	private static final String API_ROOT = "http://localhost:8081/veiculos";

	private Veiculo createRandomVeiculo() {
		Veiculo veiculo = new Veiculo();

		veiculo.setMarca(randomAlphabetic(10));
		veiculo.setModelo(randomAlphabetic(10));
		veiculo.setAno(Integer.parseInt(randomNumeric(4)));
		veiculo.setPlaca(randomAlphabetic(10).toUpperCase() + "-" + Integer.parseInt(randomNumeric(4)));
		veiculo.setKilometragem(Long.parseLong(randomNumeric(10)));
		return veiculo;
	}

	private String createVeiculoAsUri(Veiculo veiculo) {
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(veiculo)
				.post(API_ROOT);
		return API_ROOT + "/" + response.jsonPath().get("id");
	}

	@Test
	public void whenGetAllVeiculos_thenOK() {
		Response response = RestAssured.get(API_ROOT);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenGetVeiculosByTitle_thenOK() {
		Veiculo veiculo = createRandomVeiculo();
		createVeiculoAsUri(veiculo);
		Response response = RestAssured.get(API_ROOT + "/marca/" + veiculo.getMarca());

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertTrue(response.as(List.class).size() > 0);
	}

	@Test
	public void whenGetCreatedVeiculoById_thenOK() {
		Veiculo veiculo = createRandomVeiculo();
		String location = createVeiculoAsUri(veiculo);
		Response response = RestAssured.get(location);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertEquals(veiculo.getMarca(), response.jsonPath().get("marca"));
	}

	@Test
	public void whenGetNotExistVeiculoById_thenNotFound() {
		Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}

	@Test
	public void whenCreateNewVeiculo_thenCreated() {
		Veiculo veiculo = createRandomVeiculo();
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(veiculo)
				.post(API_ROOT);

		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	}

	@Test
	public void whenInvalidVeiculo_thenError() {
		Veiculo veiculo = createRandomVeiculo();
		veiculo.setModelo(null);
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(veiculo)
				.post(API_ROOT);

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
	}

	@Test
	public void whenUpdateCreatedVeiculo_thenUpdated() {
		Veiculo veiculo = createRandomVeiculo();
		String location = createVeiculoAsUri(veiculo);
		veiculo.setId(Integer.parseInt(location.split("api/veiculos/")[1]));
		veiculo.setModelo("novoModelo");
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(veiculo)
				.put(location);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());

		response = RestAssured.get(location);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertEquals("newAuthor", response.jsonPath().get("author"));
	}

	@Test
	public void whenDeleteCreatedVeiculo_thenOk() {
		Veiculo veiculo = createRandomVeiculo();
		String location = createVeiculoAsUri(veiculo);
		Response response = RestAssured.delete(location);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode());

		response = RestAssured.get(location);
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}
}
