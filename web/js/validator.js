/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var req;

function validarSenha(){
    senha1 = document.formCad.senha.value;
    senha2 = document.formCad.confSenha.value;

    if (senha1 === senha2)
        alert("SENHAS IGUAIS");
    else
        alert("SENHAS DIFERENTES");
}