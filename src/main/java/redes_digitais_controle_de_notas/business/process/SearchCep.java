package redes_digitais_controle_de_notas.business.process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchCep {

    public String getEndereco(String CEP) throws IOException {

    	//***************************************************
    	try{

    		Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+CEP)
    				.timeout(120000)
    				.get();
    		Elements urlPesquisa = doc.select("span[itemprop=streetAddress]");
    		if(!urlPesquisa.isEmpty()){
    			for (Element urlEndereco : urlPesquisa) {
    				return urlEndereco.text();
    			}
    		}else{
    			CEP = "";
    		}

    	} catch (SocketTimeoutException e) {

    	} catch (HttpStatusException w) {

    	}
    	return CEP;
    }

    public String getBairro(String CEP) throws IOException {

        //***************************************************
        try{

        Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+CEP)
                  .timeout(120000)
                  .get();
        Elements urlPesquisa = doc.select("td:gt(1)");
        if(!urlPesquisa.isEmpty()){
    			for (Element urlBairro : urlPesquisa) {
    				return urlBairro.text();
    			}
    		}else{
    			CEP = "";
    		}

        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        return CEP;
    }

    public String getCidade(String CEP) throws IOException {

        //***************************************************
        try{

        Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+CEP)
                  .timeout(120000)
                  .get();
        Elements urlPesquisa = doc.select("span[itemprop=addressLocality]");
        if(!urlPesquisa.isEmpty()){
    			for (Element urlCidade : urlPesquisa) {
    				return urlCidade.text();
    			}
    		}else{
    			CEP = "";
    		}

        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        return CEP;
    }

    public String getUF(String CEP) throws IOException {

        //***************************************************
        try{

        Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+CEP)
                  .timeout(120000)
                  .get();
        Elements urlPesquisa = doc.select("span[itemprop=addressRegion]");
        if(!urlPesquisa.isEmpty()){
    			for (Element urlUF : urlPesquisa) {
    				return urlUF.text();
    			}
    		}else{
    			CEP = "";
    		}

        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        return CEP;
    }
}
