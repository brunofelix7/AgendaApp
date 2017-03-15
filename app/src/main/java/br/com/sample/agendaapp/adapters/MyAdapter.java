package br.com.sample.agendaapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import br.com.sample.agendaapp.R;
import br.com.sample.agendaapp.interfaces.ItemClickCallback;
import br.com.sample.agendaapp.models.Contact;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<Contact> listContacts;
    private LayoutInflater inflater;
    private ItemClickCallback itemClickCallback;

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public MyAdapter(List<Contact> listContacts, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.listContacts = listContacts;
    }

    //  Cria o RecyclerView.ViewHolder, e inicializa os componentes particulares
    //  que eu quero usar no meu RecyclerView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  Recebo minha lista de itens
        View view = inflater.inflate(R.layout.itens_list, parent, false);
        return new MyViewHolder(view);
    }

    // Atualiza o conteúdo do RecyclerView.ViewHolder com o item na posição determinada e
    // também configura alguns campos particulares para serem usados ​​pelo RecyclerView.
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact item = listContacts.get(position);
        holder.tv_contact_name.setText(item.getName());
    }

    //  Retorna o número total de itens no conjunto de dados mantido pelo adaptador.
    @Override
    public int getItemCount() {
        return listContacts.size();
    }

    /**
     * Um ViewHolder descreve uma exibição de item e metadados sobre seu lugar dentro do RecyclerView.
     * As implementações do RecyclerView.Adapter devem subclassificar ViewHolder e adicionar campos
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //  Representa as Views que eu tenho no meu item_list
        private TextView tv_contact_name;
        private ImageView iv_account;
        private ImageView iv_call_number;
        private View container;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_contact_name  = (TextView) itemView.findViewById(R.id.tv_contact_name);
            iv_account       = (ImageView) itemView.findViewById(R.id.iv_account);
            iv_call_number   = (ImageView) itemView.findViewById(R.id.iv_call_number);
            container        = itemView.findViewById(R.id.cont_item_root);
            iv_call_number.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.iv_call_number){
                //  Pega o id da posição do item clicado no meu adapter
                itemClickCallback.onIconClick(getAdapterPosition());
            }
        }
    }

}
