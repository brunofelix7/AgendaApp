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
import br.com.sample.agendaapp.models.ListItem;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<ListItem> listData;
    private LayoutInflater inflater;
    private ItemClickCallback itemClickCallback;

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public MyAdapter(List<ListItem> listData, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.listData = listData;
    }

    //  Cria o RecyclerView.ViewHolder, e inicializa os componentes particulares
    //  que eu quero usar no meu RecyclerView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  Recebo minha lista de itens
        View view = inflater.inflate(R.layout.itens_list, parent, false);                        //  CardView - Seta isso
        return new MyViewHolder(view);
    }

    // Atualiza o conteúdo do RecyclerView.ViewHolder com o item na posição determinada e
    // também configura alguns campos particulares para serem usados ​​pelo RecyclerView.
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSubTitle());
    }

    //  Retorna o número total de itens no conjunto de dados mantido pelo adaptador.
    @Override
    public int getItemCount() {
        return listData.size();
    }

    /**
     * Um ViewHolder descreve uma exibição de item e metadados sobre seu lugar dentro do RecyclerView.
     * As implementações do RecyclerView.Adapter devem subclassificar ViewHolder e adicionar campos
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //  REPRESENTA O QUE EU TENHO LÁ NO MEU item_list
        private TextView title;
        private TextView subTitle;
        private ImageView thumbnail;
        private ImageView secondaryIcon;                                            //  CardView - Comente isso
        private View container;
        //private Button btn_detail;                                                      //  CardView - Acrescente isso

        public MyViewHolder(View itemView) {
            super(itemView);

            title           = (TextView) itemView.findViewById(R.id.lbl_item_text);
            subTitle        = (TextView) itemView.findViewById(R.id.lbl_item_sub_title);
            thumbnail       = (ImageView) itemView.findViewById(R.id.im_item_icon);
            secondaryIcon   = (ImageView) itemView.findViewById(R.id.im_item_icon_secondary);   //  CardView - Comente isso
            container       = itemView.findViewById(R.id.cont_item_root);
            //btn_detail      = (Button) itemView.findViewById(R.id.btn_card_load);   //  CardView - Acrescente isso
            container.setOnClickListener(this);                                     //  CardView - Comente isso
            secondaryIcon.setOnClickListener(this);                                 //  CardView - Comente isso
            //btn_detail.setOnClickListener(this);                                        //  CardView - Acrescente isso
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.cont_item_root){                                     //  CardView - Mude para btn ao invés do container
                //  PEGA A POSIÇÃO DO ITEM CLICADO NO ADAPTER
                itemClickCallback.onItemClick(getAdapterPosition());
            }else{
                itemClickCallback.onSecondaryIconClick(getAdapterPosition());       //  CardView - Comente isso
            }
        }
    }

    //  Pode ser um método me apagar a ocorrência
    public void removeAll(ArrayList<ListItem> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }


}
