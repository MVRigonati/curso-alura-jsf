package br.com.caelum.livraria.bean;

import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.model.Livro;

@ManagedBean
@ViewScoped
public class VendasBean {

	public BarChartModel initBarModel() {
        
		BarChartModel model = new BarChartModel();
		model.setAnimate(true); // Opening animation
		
        model.addSeries(getChartSerieWithName("2018"));
        model.addSeries(getChartSerieWithName("2017"));
        return model;
        
    }
	
	public ChartSeries getChartSerieWithName(String name) {
		
		ChartSeries livrosChart = new ChartSeries();
		livrosChart.setLabel(name);
        
		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		Random rand = new Random();
		
		for (Livro livro : livros) {
			int quantidade = rand.nextInt(500);
			livrosChart.set(livro.getTitulo(), quantidade);
		}
		
		return livrosChart;
		
	}
	
}
