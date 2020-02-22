package br.com.spdm.inventario.bean;
/*import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class SetorDaUnidadeBean implements Serializable{
// VARIAVEIS PARA MUDAR O PRODUTO DE ACORDO COM SUA CATEGORIA

    private String unidade;
    private String setorUnidade = null;
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private Map<String,String> unidades;
    private Map<String,String> setoresUnidades;    
//

@PostConstruct      
public void init(){

    unidades = new HashMap<String, String>();
    unidades.put("Tapiocas", "Tapiocas");
    unidades.put("Lanche", "Lanche");
    unidades.put("Bebidas", "Bebidas");

    // PREENCHENDO A CATEGORIA REFERENTES A TAPIOCA
        listaTapiocas = TapiocariaFacade.listarProdutoTapiocas();
        Map<String,String> map = new HashMap<String, String>();
        for (Produto produto : listaTapiocas) {
            map.put(produto.getNome(), produto.getNome().toString());   
        }
        data.put("Tapiocas", map);
    //
    // PREENCHENDO A CATEGORIA REFERENTES A LANCHES
        listaLanches =  TapiocariaFacade.listarProdutoLanches();
        map = new HashMap<String, String>();
        for (Produto produto : listaLanches) {
            map.put(produto.getNome(), produto.getNome().toString());
        }
        data.put("Lanche", map);
    //

    // PREENCHENDO A CATEGORIA REFERENTES A BEBIDAS
        listaBebidas = TapiocariaFacade.listarProdutoBebidas();
        map = new HashMap<String, String>();
        for (Produto produto : listaBebidas) {
            map.put(produto.getNome(), produto.getNome().toString());
        }
        data.put("Bebidas", map);
    //
}
// REALIZAR TROCA DO PRODUTO APOS SELECIONAR A SUA CATEGORIA
            public void onCountryChange() {
                if(categoria !=null && !categoria.equals(""))
                    produtosCategorias = data.get(categoria);
                 else
                    produtosCategorias = new HashMap<String, String>();
                }
        //  

        // ENVIANDO MENSAGEM CASO NAO SEJA SELEICONADO NENHUM PRODUTO
            public void displayLocation() {
                FacesMessage msg;
                if(produtoCategoria != null && categoria!=null){

                    System.out.println(categoria +"---"+produtoCategoria);
                    msg = new FacesMessage("Selecionado", produtoCategoria + " De " + categoria);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
               }else{
                   System.out.println("nao tem nada seleciondo");
                   msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Produto não selecionado!."); 

                     FacesContext.getCurrentInstance().addMessage(null, msg);
                  }
            }
            //*/