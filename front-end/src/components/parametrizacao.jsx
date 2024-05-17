import React, { useState } from "react";
import axios from "axios";
import './parametrizacao.css';
import Header from './header';
import Footer from './footer';

export default function Parametrizacao() {
    const [formData, setFormData] = useState({
        parametrizacaoNomeFantasia: "",
        parametrizacaoRazaoSocial: "",
        parametrizacaoTelefone: "",
        parametrizacaoemail: "",
        parametrizacaoSite: "",
        parametrizacaoLogradouro: "",
        parametrizacaoBairro: "",
        parametrizacaoCEP: "",
        parametrizacaoNumero: "",
        parametrizacaoLogoTipoGrande: "",
        parametrizacaoLogoTipoPequena: ""
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            console.log("Dados a serem enviados para o servidor:", formData); // Adiciona este console.log
            const response = await axios.post("http://localhost:8080/parametrizacao/salvar", formData, {
                headers: {
                    "Content-Type": "application/json"
                }
            });
            if (response.status === 201) { // 201 é o código para Created
                // Limpar o formulário após o envio bem-sucedido
                setFormData({
                    parametrizacaoNomeFantasia: "",
                    parametrizacaoRazaoSocial: "",
                    parametrizacaoTelefone: "",
                    parametrizacaoemail: "",
                    parametrizacaoSite: "",
                    parametrizacaoLogradouro: "",
                    parametrizacaoBairro: "",
                    parametrizacaoCEP: "",
                    parametrizacaoNumero: "",
                    parametrizacaoLogoTipoGrande: "",
                    parametrizacaoLogoTipoPequena: ""
                });
                alert("Formulário enviado com sucesso!");
            } else {
                alert("Ocorreu um erro ao enviar o formulário. Por favor, tente novamente mais tarde.");
            }
        } catch (error) {
            console.error("Erro ao enviar o formulário:", error);
            alert("Erro ao enviar o formulário. Por favor, tente novamente mais tarde.");
        }
    };



    return (
        <>
            <Header />
            <form onSubmit={handleSubmit} encType="multipart/form-data">
                <div className="page-container">
                    <div className="form_Par">
                        <p>Parametrização de dados</p>
                        <div className="campos">
                            <div className="form_dados row">
                                <section className="form_bloco">
                                    <div className="form_dados col">
                                        <div className="form_group">
                                            <input required type="text" className="form_input col" name="parametrizacaoNomeFantasia" value={formData.parametrizacaoNomeFantasia} onChange={handleChange} />
                                            <label className="form_label">Nome fantasia</label>
                                        </div>
                                        <div className="form_group">
                                            <input required type="text" className="form_input col" name="parametrizacaoRazaoSocial" value={formData.parametrizacaoRazaoSocial} onChange={handleChange} />
                                            <label className="form_label">Razão social</label>
                                        </div>
                                        <div className="form_group">
                                            <input required type="text" className="form_input col" name="parametrizacaoTelefone" value={formData.parametrizacaoTelefone} onChange={handleChange} />
                                            <label className="form_label">Telefone</label>
                                        </div>
                                        <div className="form_group">
                                            <input required type="text" className="form_input col" name="parametrizacaoemail" value={formData.parametrizacaoemail} onChange={handleChange} />
                                            <label className="form_label">Email</label>
                                        </div>
                                        <div className="form_group">
                                            <input required type="text" className="form_input col" name="parametrizacaoSite" value={formData.parametrizacaoSite} onChange={handleChange} />
                                            <label className="form_label">Site</label>
                                        </div>
                                    </div>
                                    <div className="form_dados col">
                                        <div className="form_group">
                                            <input required type="text" className="form_input col" name="parametrizacaoLogradouro" value={formData.parametrizacaoLogradouro} onChange={handleChange} />
                                            <label className="form_label">Rua</label>
                                        </div>
                                        <div className="form_group">
                                            <input required type="text" className="form_input col" name="parametrizacaoBairro" value={formData.parametrizacaoBairro} onChange={handleChange} />
                                            <label className="form_label">Bairro</label>
                                        </div>
                                        <div className="form_group">
                                            <input required type="text" className="form_input col" name="parametrizacaoCEP" value={formData.parametrizacaoCEP} onChange={handleChange} />
                                            <label className="form_label">CEP</label>
                                        </div>
                                        <div className="form_group">
                                            <input required type="text" className="form_input col" name="parametrizacaoNumero" value={formData.parametrizacaoNumero} onChange={handleChange} />
                                            <label className="form_label">Número</label>
                                        </div>
                                    </div>
                                </section>
                            </div>


                            <div className="form_dados col">
                                <div className="form_group">
                                    <input required type="text" className="form_input col" name="parametrizacaoLogoTipoGrande" value={formData.parametrizacaoLogoTipoGrande} onChange={handleChange} />
                                    <label className="form_label">Caminho da imagem Grande</label>
                                </div>
                                <div className="form_group">
                                    <input required type="text" className="form_input col" name="parametrizacaoLogoTipoPequena" value={formData.parametrizacaoLogoTipoPequena} onChange={handleChange} />
                                    <label className="form_label">Caminho da imagem Pequena</label>
                                </div>
                            </div>
                            
                            <input type="submit" />

                        </div>
                    </div>
                </div>
            </form>

            <Footer />
        </>
    );
}
