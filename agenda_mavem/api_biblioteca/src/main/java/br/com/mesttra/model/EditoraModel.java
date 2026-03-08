package br.com.mesttra.model;

public class EditoraModel {
    private int id;
    private String editora;
    private String cnpj;
    private String email;
    private String telefone;
    private String cep;
    private String estado;
    private String bairro;
    private String endereco;
    private String nacionalidade;
    private String endereco_web;

    public EditoraModel() {
    }

    public EditoraModel(String editora, String cnpj, String telefone, String email, String cep, String estado, String bairro, String endereco, String nacionalidade, String endereco_web) {
        this.editora = editora;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.estado = estado;
        this.bairro = bairro;
        this.endereco = endereco;
        this.nacionalidade = nacionalidade;
        this.endereco_web = endereco_web;
    }

    public EditoraModel(int id, String editora, String cnpj, String telefone, String email, String cep, String estado, String bairro, String endereco, String nacionalidade, String endereco_web) {
        this.id = id;
        this.editora = editora;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.estado = estado;
        this.bairro = bairro;
        this.endereco = endereco;
        this.nacionalidade = nacionalidade;
        this.endereco_web = endereco_web;
    }

    public int getId() {
        return id;
    }

    public String getEditora() {
        return editora;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getEstado() {
        return estado;
    }

    public String getBairro() {
        return bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getEndereco_web() {
        return endereco_web;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setEndereco_web(String endereco_web) {
        this.endereco_web = endereco_web;
    }
}
