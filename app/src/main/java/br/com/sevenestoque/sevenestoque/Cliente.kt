package br.com.sevenestoque.sevenestoque

import java.io.Serializable

data class Cliente (val cpf:String?=null,
                    val nome:String?=null,
                    val contato:String?=null,
                    val email:String?=null):Serializable {

}