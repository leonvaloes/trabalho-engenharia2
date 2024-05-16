import React from "react";
import './parametrizacao.css';
import Header from './header';
import Footer from './footer';

export default function parametrizacao(){
    return(
        <>        
        <Header/>
        <div className="page-container">
            <div className="form_Par">
                <p>Dados da empresa</p>
                <div className="campos">
                    
                    <div className="form_dados row">
                        <input type="text" className="form_input col" name="" id="" placeholder="Nome fantasia" />
                        <input type="text" className="form_input col" name="" id="" placeholder="CNPJ" />
                        <input type="text" className="form_input col" name="" id="" placeholder="Telefone" />
                        <input type="text" className="form_input col" name="" id="" placeholder="Email" />
                    </div>
                </div>
                
                <div className="campos">
                    <p>Endereço da instituição</p>
                    <div className="form_dados row">
                        <input type="text" className="form_input col" name="" id="" placeholder="Rua"/>
                        <input type="text" className="form_input col" name="" id="" placeholder="Bairro" />
                        <input type="text" className="form_input col" name="" id="" placeholder="Estado"/>
                    </div>
                    <div className="form_dados row">
                        <input type="text" className="form_input col" name="" id="" placeholder="CEP" />
                        <input type="text" className="form_input col" name="" id="" placeholder="Número"/>
                        <input type="text" className="form_input col" name="" id="" placeholder="Complemento" />
                    </div> 
                </div>
                

                <div className="div_img">
                    <input type="file" className="input_img" id="input_img" accept="image/*" />
                    <label htmlFor="input_img">Selecionar imagem</label>
                </div>

                


            </div>
        </div>
        <Footer/>
        </>

        
    );
}
