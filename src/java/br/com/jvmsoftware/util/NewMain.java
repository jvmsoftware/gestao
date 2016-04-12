/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.util;

import br.com.jvmsoftware.daos.AcsUsuarioSistemaDAO;
import br.com.jvmsoftware.daos.PubConfigEmpresaDAO;
import br.com.jvmsoftware.daos.PubConteudoDAO;
import br.com.jvmsoftware.daos.PubEmpresaDAO;
import br.com.jvmsoftware.daos.PubEstadoDAO;
import br.com.jvmsoftware.daos.PubMenuDAO;
import br.com.jvmsoftware.daos.PubMunicipioDAO;
import br.com.jvmsoftware.daos.PubPosicaoDAO;
import br.com.jvmsoftware.daos.PubSistemaDAO;
import br.com.jvmsoftware.daos.PubUsuarioDAO;
import br.com.jvmsoftware.entities.AcsUsuarioSistema;
import br.com.jvmsoftware.entities.PubConfigEmpresa;
import br.com.jvmsoftware.entities.PubConteudo;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubMenu;
import br.com.jvmsoftware.entities.PubMunicipio;
import br.com.jvmsoftware.entities.PubPosicao;
import br.com.jvmsoftware.entities.PubSistema;
import br.com.jvmsoftware.entities.PubUsuario;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.mail.EmailException;


/**
 *
 * @author jose
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, EmailException, ParseException {
        NewMain main = new NewMain();
        
        //System.out.println("**************************");
        //main.getMenu();
        
        //System.out.println("**************************");
        //main.getPosicao();
        
        //System.out.println("**************************");
        //main.getConteudo();
        
        //main.random();
        //main.mail();l
        //main.senha();
        //main.enviarMailRecuperaSenha();
        //main.municipios();
        
        //main.triggerEmp();
        //main.triggerUsu();
                
        //main.updateSis();
        //main.insPosi();
        //main.insUsu();
        
        main.permissoes();
        
    }
    
    private void municipios() throws SQLException {
        PubMunicipioDAO munDAO = new PubMunicipioDAO();
        PubEstadoDAO estDAO = new PubEstadoDAO();
        List<PubMunicipio> listMunic;
        listMunic = munDAO.listMunicipiosByEstado(estDAO.getById(35));
        System.out.println("municipios: " + listMunic.size());
        
    }
    
    private void enviarMailRecuperaSenha() throws EmailException, SQLException {
        // busca config da empresa padrão (jvmdsoftware)
        PubConfigEmpresaDAO confDAO = new PubConfigEmpresaDAO();
        PubUsuarioDAO usuDAO = new PubUsuarioDAO();
        PubConfigEmpresa conf = confDAO.getById(1);
        PubUsuario usu = usuDAO.getUsuariosByMail("jrsiesler@gmail.com");
        // envia email
        EnviarMail mail = new EnviarMail();
        String mensagem = usu.getNomeUsuario() + "!\n \n" +
                "Este é um e-mail de recuperação de senha. Sua senha foi reiniciada. \n" +
                "acesse o link http://localhost:8080 \n" +
                "Faça o login e informe seu codigo de verificação para ativar sua conta.";
        mail.emailSimples(conf, usu, mensagem, "Bem vindo!");
    }
    
    private void insUsu() throws SQLException {
        PubUsuario usu = new PubUsuario();
        PubUsuarioDAO usuDAO = new PubUsuarioDAO();
        GeraCodigoVerificacao gera = new GeraCodigoVerificacao(); //você pode usar outras máscaras 
        // set usuario
        usu.setDataNascimento(new Date());
        usu.setEmail("jrsiesler@gmail.com");
        usu.setNomeUsuario("José");
        usu.setDataCadastro(new Date());
        usu.setSenha(Criptografia.criptografar("Matheus1108"));
        usu.setCodigoVerificacao(gera.geraCodigo());
        usu.setAtivo(true);
        usuDAO.inserirUsuario(usu);
        System.out.println("Usuario Inserido");
    }
    
    private void insPosi() throws SQLException {
        PubPosicao pos = new PubPosicao();
        PubPosicaoDAO posDAO = new PubPosicaoDAO();
        pos.setPosicao("teste");
        pos.setDescricao("teste");
        posDAO.inserirPosicao(pos);
        System.out.println("Posicao Inserida");
    }
    
    private void updateSis() throws SQLException {
        PubSistema sis;
        PubSistemaDAO sisDAO = new PubSistemaDAO();
        sis = sisDAO.getById(1);
        sis.setAtivo(true);
        sisDAO.atualizarSistema(sis);
        
    }
    
    private void triggerEmp() throws SQLException {
        PubEmpresaDAO empDAO = new PubEmpresaDAO();
        PubEmpresa emp = empDAO.getById(1);
        empDAO.atualizarEmpresa(emp);
        empDAO.closeSession();
    }
    
    private void triggerUsu() throws SQLException, ParseException {
        
        PubEmpresaDAO empDAO = new PubEmpresaDAO();
        
        SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); //você pode usar outras máscaras 
        Date y = new Date();
        Date x = sdf1.parse("25/01/1978");
        System.out.println(sdf1.format(y));
        System.out.println(sdf1.format(x));
                
        PubEmpresa emp = empDAO.getById(1);
        PubUsuarioDAO usuDAO = new PubUsuarioDAO();
        PubUsuario usu = usuDAO.getById(1);
        usu.setPubEmpresa(emp);
        //usu.setDataCadastro(y);
        usu.setDataNascimento(x);
        usu.setAtivo(true);
        usu.setDataVerificacao(y);
        
    }
    
    private void senha() {
        String senha;
        senha = Criptografia.criptografar("Matheus1108");
        System.out.println("senha: " + senha);
    }

    private void random() {
        GeraCodigoVerificacao gera = new GeraCodigoVerificacao();
        String codigo;
        codigo = gera.geraCodigo();
        System.out.println("codigo: " + codigo);
    }
    
    private void mail() throws EmailException, SQLException {
        PubUsuarioDAO usuDAO = new PubUsuarioDAO();
        PubConfigEmpresaDAO confDAO = new PubConfigEmpresaDAO();
        PubConfigEmpresa conf = confDAO.getById(1);
        PubUsuario usu = usuDAO.getById(1);
        EnviarMail mail = new EnviarMail();
        String msg = "bem vindo ao sistema " + usu.getNomeUsuario() + ".\n \n" +
                "Seu codigo de verificação é: " + usu.getCodigoVerificacao() + ". \n" +
                "Faça o login e informe seu codigo de verificação para ativar sua conta.";
        mail.emailSimples(conf, usu, msg, "Bem vindo!");
    }
    
    private void getMenu() throws SQLException {
        PubMenuDAO menuDAO = new PubMenuDAO();
        List<PubMenu> menus;
        List<PubMenu> itensMenu;
        PubMenu menu;
        menus = menuDAO.listBarraMenu();
        System.out.println("** Barra de menus **");
        for (int i=0; i<menus.size(); i++) {
            System.out.println("Barra de menu " + menus.get(i).getMenu());
            menu = menuDAO.getById(menus.get(i).getIdMenu());
            System.out.println("** Itens de menu de " + menu.getMenu() + " **");
            itensMenu = menuDAO.listItensMenu(menu);
            if (!itensMenu.isEmpty()) {
                for (int j=0; j<itensMenu.size(); j++) {
                    System.out.println("Item de menu " + itensMenu.get(j).getMenu());
                }
            }
        }
        menus = menuDAO.listaTodos();
    }
    
    /*
    public void getPosicao() throws SQLException {
        PubPosicaoDAO posicaoDAO = new PubPosicaoDAO();
        List<PubPosicao> posicoes;
        PubPosicao posicao;
        posicao = posicaoDAO.getById(1);
        System.out.println("** getById - posicao: " + posicao.getPosicao() + " **");
        System.out.println("*** listPosicoes ***");
        posicoes = posicaoDAO.listAllPosicoes();
        for (int i=0; i<posicoes.size(); i++) {
            System.out.println("posicao: " + posicoes.get(i).getPosicao());
        }
    }
    */
    
    public void getConteudo() throws SQLException {
        PubConteudoDAO conteudoDAO = new PubConteudoDAO();
        List<PubConteudo> conteudos;
        PubConteudo conteudo;
        conteudo = conteudoDAO.getById(1);
        System.out.println("** getById - conteudo: " + conteudo.getConteudo() + " **");
        System.out.println("*** listConteudos ***");
        conteudos = conteudoDAO.listAllConteudos();
        for (int i=0; i<conteudos.size(); i++) {
            System.out.println("conteudo: " + conteudos.get(i).getConteudo());
        }
    }
    
    public void permissoes() throws SQLException {
        AcsUsuarioSistemaDAO usuSisDAO = new AcsUsuarioSistemaDAO();
        PubUsuarioDAO usuDAO = new PubUsuarioDAO();
        PubSistemaDAO sisDAO = new PubSistemaDAO();
        AcsUsuarioSistema rel = new AcsUsuarioSistema();
        List<AcsUsuarioSistema> list;
        list = (List<AcsUsuarioSistema>) usuSisDAO.listUsuarioSistemaByUsuario(usuDAO.getById(1));
        rel = usuSisDAO.getUsuarioSistemaByUsuarioSistema(usuDAO.getById(1), sisDAO.getById(1));
        List<String> newList = new ArrayList<>();
        for (AcsUsuarioSistema list1 : list) {
            newList.add(list1.getPubSistema().getNomeSistema() + "," + list1.isAtivo());
        }
    }
    
}
