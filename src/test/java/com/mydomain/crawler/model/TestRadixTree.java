package com.mydomain.crawler.model;

import com.mydomain.crawler.CrawlerException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestRadixTree {
	private List<String> populate() {
		List<String> uris = new ArrayList<>();
		uris.add("https://www.clarin.com/");
		uris.add("https://www.clarin.com/suscripciones/landing.html?ob=1");
		uris.add("https://www.clarin.com");
		uris.add("https://www.clarin.com/ultimas-noticias/");
		uris.add("https://www.clarin.com/politica/");
		uris.add("https://www.clarin.com/economia/");
		uris.add("https://www.clarin.com/economia/divisas-acciones-bonos/");
		uris.add("https://www.clarin.com/mercado-inmobiliario/");
		uris.add("https://www.clarin.com/rural/");
		uris.add("https://www.clarin.com/sociedad/");
		uris.add("https://www.clarin.com/ciudades/");
		uris.add("https://www.clarin.com/policiales/");
		uris.add("https://www.clarin.com/opinion/");
		uris.add("https://www.clarin.com/cartas_al_pais/");
		uris.add("https://www.clarin.com/mundo/");
		uris.add("https://www.clarin.com/deportes/");
		uris.add("https://www.clarin.com/deportes/futbol/");
		uris.add("https://www.clarin.com/deportes/futbol-internacional/");
		uris.add("https://www.clarin.com/deportes/seleccion-nacional/");
		uris.add("https://www.clarin.com/deportes/estadisticas.html");
		uris.add("https://www.clarin.com/deportes/ascenso/");
		uris.add("https://www.clarin.com/deportes/tenis/");
		uris.add("https://www.clarin.com/deportes/rugby/");
		uris.add("https://www.clarin.com/tema/nba.html");
		uris.add("https://www.clarin.com/deportes/hockey/");
		uris.add("https://www.clarin.com/deportes/agenda-deportiva/");
		uris.add("https://www.clarin.com/espectaculos/");
		uris.add("https://www.clarin.com/espectaculos/tv/");
		uris.add("https://www.clarin.com/espectaculos/cine/");
		uris.add("https://www.clarin.com/tema/series-y-peliculas.html");
		uris.add("https://www.clarin.com/espectaculos/musica/");
		uris.add("https://www.clarin.com/espectaculos/teatro/");
		uris.add("https://www.clarin.com/espectaculos/teatro/cartelera.html");
		uris.add("https://www.clarin.com/espectaculos/cine/cartelera.html");
		uris.add("https://www.clarin.com/cultura/");
		uris.add("https://www.clarin.com/revista-enie/");
		uris.add("https://www.clarin.com/clima/");
		uris.add("https://www.clarin.com/horoscopo.html");
		uris.add("https://www.clarin.com/loterias-y-quinielas/");
		uris.add("https://www.clarin.com/radio-mitre-vivo/");
		uris.add("https://clasificados.clarin.com/");
		uris.add("https://www.argenprop.com/");
		uris.add("https://www.empleos.clarin.com/postulantes/");
		uris.add("https://www.clarin.com/humor/");
		uris.add("https://www.clarin.com/feriados/");
		uris.add("https://www.clarin.com/television-argentina/");
		uris.add("https://tapas.clarin.com/");
		uris.add("https://www.clarin.com/ediciones-anteriores/");
		uris.add("https://www.grandt.clarin.com/");
		uris.add("https://www.clarin.com/horoscopo-chino");
		uris.add("https://www.clarin.com/agencias/");
		uris.add("https://kiosco.clarin.com/");
		uris.add("https://www.clarin.com/claringrilla");
		uris.add("https://www.clarin.com/sudoku");
		uris.add("https://www.clarin.com/videos/");
		uris.add("https://www.clarin.com/fotogalerias/");
		uris.add("https://www.clarin.com/fama/");
		uris.add("https://www.clarin.com/internacional/");
		uris.add("https://www.clarin.com/autos/");
		uris.add("https://www.clarin.com/tecnologia/");
		uris.add("https://www.clarin.com/viajes/");
		uris.add("https://www.clarin.com/arq/");
		uris.add("https://recetas.clarin.com/");
		uris.add("https://www.clarin.com/gourmet/");
		uris.add("https://www.clarin.com/buena-vida/");
		uris.add("https://www.clarin.com/familias");
		uris.add("https://www.clarin.com/relaciones");
		uris.add("https://www.clarin.com/genero");
		uris.add("https://www.clarin.com/astrologia");
		uris.add("https://www.clarin.com/viva/");
		uris.add("https://www.clarin.com/new-york-times-international-weekly");
		uris.add("https://www.clarin.com/br/");
		uris.add("https://www.clarin.com/tema/especial-clarin.html");
		uris.add("https://www.clarin.com/videos-documentales-clarin");
		uris.add("https://www.clarin.com/newsletters/");
		uris.add("https://www.clarin.com/contactenos.html");
		uris.add("https://www.clarin.com/preguntas-frecuentes");
		uris.add("https://www.clarin.com/suscripciones/landing.html");
		uris.add("https://www.clarin.com/dialogos/");
		uris.add("https://www.clarin.com/temas/");
		uris.add("https://clasificados.clarin.com/inicio/index#!/");
		uris.add("https://365.clarin.com");
		uris.add("http://autogestion.365.com.ar");
		uris.add("https://www.clarin.com/clima");
		uris.add("https://www.clarin.com/politica/muerte-nestor-kirchner-intimidad-ultimas-horas-calafate_0_xeScEh8ca.html");
		uris.add("https://www.clarin.com/politica/elisa-carrio-hablo-carta-cristina-lanzo-dura-definicion-nestor-kirchner_0_Ry1ltWnE6.html");
		uris.add("https://www.clarin.com/economia/fuerte-baja-dolar-blue-vende-183_0_ilICWwrSZ.html");
		uris.add("https://www.clarin.com/sociedad/coronavirus-argentina-confirman-430-muertes-14-308-nuevos-casos-ultimas-24-horas_0_ZmjU87F2Q.html");
		uris.add("https://www.clarin.com/sociedad/provincia-prepara-vacunacion-masiva-coronavirus-plazo-maneja_0_NHemE_d9l.html");
		uris.add("https://www.clarin.com/deportes/razones-clave-minaron-poder-josep-maria-bartomeu-llevaron-renuncia-barcelona_0_10X2jnkys.html");
		uris.add("https://www.clarin.com/deportes/marcelo-gallardo-aislado-contacto-estrecho-coronavirus-alerta-river_0_6J_UrpE0a.html");
		return uris;
	}

	@Test
	public void testURI() {
		String uri = "https://www.clarin.com/";
		RadixTree tree = new RadixTree(uri);
		List<String> uris = populate();
		for(String u: uris) {
			try {
				tree.put(u);
			} catch(CrawlerException e) {
				System.err.println(e.getMessage());
				Assert.fail();
			}
		}
		tree.display();

		List<String> travel = tree.normalize();
		travel.forEach(System.out::println);
	}
}
