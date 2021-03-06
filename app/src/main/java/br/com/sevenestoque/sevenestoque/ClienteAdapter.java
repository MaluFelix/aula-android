package br.com.sevenestoque.sevenestoque;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.MyHoder> {

    List<FireModel> list;
    Context context;

    public ClienteAdapter(List<FireModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_cliente, parent, false);
        MyHoder myHoder = new MyHoder(view);

        return myHoder;
    }


    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        FireModel mylist = list.get(position);
        holder.name.setText(mylist.getNome());
        holder.CPF.setText(mylist.getCPF());
        holder.contato.setText(mylist.getContato());
        holder.email.setText(mylist.getEmail());
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try {
            if (list.size() == 0) {

                arr = 0;

            } else {

                arr = list.size();
            }


        } catch (Exception e) {


        }

        return arr;

    }

    class MyHoder extends RecyclerView.ViewHolder {
        TextView name, CPF, contato, email;


        public MyHoder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtNome);
            CPF = (TextView) itemView.findViewById(R.id.txtCpf);
            contato = (TextView) itemView.findViewById(R.id.txtContato);
            email = (TextView) itemView.findViewById(R.id.txtEmail);

        }
    }

}