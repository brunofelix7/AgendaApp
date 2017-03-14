package br.com.sample.agendaapp.models;

import java.util.ArrayList;
import java.util.List;

import br.com.sample.agendaapp.R;

/**
 * Essa classe representa a imitação os seus dados, que estariamos recebendo do SQLite ou WebService por exemplo
 */
public class MyContacts {

    //  LISTA DE NOMES QUE VÃO APARECER NO RECYCLERVIEW
    //  SERÁ O MEU ARRAY QUE ESTÁ VINDO DA API
    private static final String[] titles = {
            "Contato 1",
            "Contato 2",
            "Contato 3",
            "Contato 4",
            "Contato 5",
            "Contato 6"
    };

    private static final String[] subTitles = {
            "Description 1",
            "Description 2",
            "Description 3",
            "Description 4",
            "Description 5",
            "Description 6"
    };

    //  REPRESENTA OS RECURSOS DE IMAGEM
    private static final int[] icons = {
            android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_menu_call,
            android.R.drawable.ic_menu_mylocation,
            android.R.drawable.ic_menu_mylocation,
            android.R.drawable.ic_menu_mylocation,
            android.R.drawable.ic_menu_mylocation
    };


    /**
     * Este seria meu método que retorna minha Lista de Data pegando pelo requesterId
     * @return List<Data>
     */
    public static List<ListItem> getListData(){
        List<ListItem> data = new ArrayList<>();

        //  ADICIONA NO RECYCLERVIEW TODAS AS NOSSAS LISTAS
        for(int i = 0; i < titles.length; i++){
            ListItem item = new ListItem();
            item.setTitle(titles[i]);
            item.setSubTitle(subTitles[i]);
            data.add(item);
        }
        return data;
    }

}
