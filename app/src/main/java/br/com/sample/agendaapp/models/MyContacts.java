package br.com.sample.agendaapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe representa a imitação os seus dados, que estariamos recebendo do SQLite ou WebService por exemplo
 */
public class MyContacts {

    /**
     * Método que contém os nomes que serão exibidos no RecyclerView
     * @return Lista de nomes
     */
    private static List<String> contactsName(){
        List<String> listNames = new ArrayList<>();
        listNames.add("Bruno");
        listNames.add("João");
        listNames.add("Emerson");
        listNames.add("Filomena");
        listNames.add("Florantina");
        listNames.add("Astrogildo");
        return listNames;
    }

    /**
     * Método que contém os números que serão usados na intent.ACTION_DIAL
     * @return Lista de telefones
     */
    private static List<String> contactsPhone(){
        List<String> listPhones = new ArrayList<>();
        listPhones.add("988340564");
        listPhones.add("996135508");
        listPhones.add("988994981");
        listPhones.add("988167941");
        listPhones.add("987605412");
        listPhones.add("999686022");
        return listPhones;
    }

    /**
     * Este seria meu método que retorna minha Lista de Data pegando pelo requesterId
     * @return List<Data>
     */
    public static List<Contact> getListData(){
        List<Contact> listContacts = new ArrayList<>();

        //  ADICIONA NO RECYCLERVIEW TODAS AS NOSSAS LISTAS
        for(int i = 0; i < contactsName().size() && i < contactsPhone().size(); i++){
            Contact contact = new Contact();
            contact.setName(contactsName().get(i));
            contact.setPhone(contactsPhone().get(i));
            listContacts.add(contact);
        }
        return listContacts;
    }

}
