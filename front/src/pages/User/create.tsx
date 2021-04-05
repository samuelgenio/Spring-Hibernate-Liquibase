import React, { ChangeEvent, Fragment } from 'react';
import './styles.css';
import { FiArrowLeft } from 'react-icons/fi';
import { Link } from 'react-router-dom';
import { RouteComponentProps } from 'react-router';
import api from '../../services/api';

import logo from '../../assets/logo.png';
import * as yup from 'yup'
import { Formik } from 'formik';
import InputMask from 'react-input-mask';

import {DataInterface} from '../../interfaces/Data';
import NumberFormat from '../../components/NumberFormat';

interface RouteParams { email: string }

class User extends React.Component<RouteComponentProps<RouteParams>> {

    state = {
        data:null,
        UserEmail: null,
        vlr_original:0
    }

    componentDidMount() {
        this.setState({ UserEmail: this.props.match.params.email });
    }

    yupValidate() {
        return yup.object().shape({
            nome: yup
                .string()
                .max(40, 'Nome deve ter no máximo 40 caracteres!')
                .required('Nome é Obrigatório!'),
            val_original: yup
                .string().typeError("Apenas números!")
                .required('Valor Original é Obrigatório!'),
            dat_vencimento: yup
                .string()
                .required('Data de Vencimento é Obrigatório!'),
            dat_pagamento: yup
                .string()
                .required('Data de Pagamento é Obrigatório!'),
        })
    }

    async handleOnsubmit(values: any) {

        console.log(values);

        var partsDatVencimento = values.dat_vencimento.split('/');
        var partsDatPagamento = values.dat_pagamento.split('/');

        if (this.state.UserEmail != null) {
            await api.post('/contas/', {
                'nome': values.nome,
                'email': this.state.UserEmail,
                'vlrOriginal': this.state.vlr_original,
                'datVencimento': partsDatVencimento[2] + '-' + partsDatVencimento[1] + '-' + partsDatVencimento[0],
                'datPagamento': partsDatPagamento[2] + '-' + partsDatPagamento[1] + '-' + partsDatPagamento[0],
            })
                .then((response) => {
                    alert("Despesa adicionada!");
                    this.props.history.push('/user/' + this.state.UserEmail);
                }, (error) => {
                    alert("Falha ao adicionar Despesa!");
                });
        } else {
            this.props.history.push("/");
        }
    }

    render() {
        return (

            <div id="page-create">
                <header>
                    <Link to="/">
                        <img src={logo} alt="DevilerIt" id="logo" />
                    </Link>
                </header>
                <div id="content-form">
                    <Formik
                        initialValues={{
                            nome: '',
                            val_original: '',
                            dat_vencimento: '',
                            dat_pagamento: ''
                        }}
                        onSubmit={values => this.handleOnsubmit(values)}
                        validationSchema={this.yupValidate}
                    >
                        {({ values, handleChange, errors, setFieldTouched, touched, isValid, handleSubmit }) => (
                            <Fragment>

                                <h1>Cadastro de Despesa</h1>

                                <fieldset>
                                    <legend>
                                        <h2>Geral</h2>
                                    </legend>

                                    <div className="field">

                                        <label htmlFor="nome">Descrição</label>

                                        <input
                                            type="text"
                                            name="nome"
                                            id="nome"
                                            value={values.nome}
                                            onChange={handleChange('nome')}
                                            onBlur={() => setFieldTouched('nome')}
                                        />
                                        {touched.nome && errors.nome &&
                                            <p className="error-info">{errors.nome}</p>
                                        }
                                    </div>

                                    <div className="field">

                                        <label htmlFor="val_original">Valor Original</label>
                                        
                                        <NumberFormat 
                                            displayType="Input"
                                            name="val_original"
                                            id="val_original"
                                            value={values.val_original}
                                            onChange={handleChange('val_original')}
                                            onblur={() => setFieldTouched('val_original')}
                                            onValueChange={(values:any) => {
                                                const {value} = values;
                                                this.setState({vlr_original:value});
                                              }}
                                        />

                                        {touched.val_original && errors.val_original &&
                                            <p className="error-info">{errors.val_original}</p>
                                        }
                                    </div>

                                    <div className="field-group">
                                        <div className="field">
                                            <label htmlFor="dat_vencimento">Data de Vencimento</label>
                                            <InputMask mask="99/99/9999" name="dat_vencimento"
                                                id="dat_vencimento"
                                                value={values.dat_vencimento}
                                                onChange={handleChange('dat_vencimento')}
                                                onBlur={() => setFieldTouched('dat_vencimento')} />

                                            {touched.dat_vencimento && errors.dat_vencimento &&
                                                <p className="error-info">{errors.dat_vencimento}</p>
                                            }
                                        </div>
                                        <div className="field">
                                            <label htmlFor="dat_pagamento">Data de Pagamento</label>
                                            <InputMask mask="99/99/9999" name="dat_pagamento"
                                                id="dat_pagamento"
                                                value={values.dat_pagamento}
                                                onChange={handleChange('dat_pagamento')}
                                                onBlur={() => setFieldTouched('dat_pagamento')} />

                                            {touched.dat_pagamento && errors.dat_pagamento &&
                                                <p className="error-info">{errors.dat_pagamento}</p>
                                            }
                                        </div>
                                    </div>

                                </fieldset>

                                <button type="submit" onClick={e => handleSubmit()} >{"Adicionar"}</button>

                            </Fragment>
                        )}
                    </Formik>
                </div>
            </div>
        )
    }


}

export default User;