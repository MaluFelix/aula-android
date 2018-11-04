package br.com.sevenestoque.sevenestoque

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import java.util.*

class ClienteAdapter(var lista:ArrayList<Cliente>):RecyclerView.Adapter<ClienteAdapter.ViewHolder> () {
    override fun onBindViewHolder(holder: ViewHolder, posicao: Int) {
        var cliente :Cliente = lista[posicao]
        holder?.cpf.text = cliente.cpf.toString()
        holder?.nome.text = cliente.nome.toString()
        holder?.contato.text = cliente.contato.toString()
        holder?.email.text = cliente.email.toString()

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, pl: Int): ViewHolder {
        val v:View = LayoutInflater.from(parent?.context).inflate(R.layout.item_cliente,parent,false)
        return ViewHolder(v)
    }

    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {
        val cpf = itemview.findViewById<TextView>(R.id.txtCpf)
        val nome = itemview.findViewById<TextView>(R.id.txtNome)
        val contato = itemview.findViewById<TextView>(R.id.txtContato)
        val email = itemview.findViewById<TextView>(R.id.txtEmail)
        val btnEdit = itemview.findViewById<ImageButton>(R.id.ibtnEdit)
        val btnDelete = itemview.findViewById<ImageButton>(R.id.ibtnDelete)
    }




}