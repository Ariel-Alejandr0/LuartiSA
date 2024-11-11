import React, { useEffect, useState } from "react";
import { useAuth } from "../contexts/auth";
import { requestCreateUser } from "../service/POSTS/CreateUser";

export default function Login() {
  const { doLogin } = useAuth();
  const [className, setClassName] = useState("");
  const [loginData, setLoginData] = useState({});
  const [signUpData, setSignUpData] = useState({});
  useEffect(() => {
    document.body.className = className;
  }, [className]);

  //função do evento css
  const handleOnClickSignIn = (e) => {
    e.preventDefault();
    setClassName("sign-in-js");
  };

  const handleOnClickSignUp = (e) => {
    e.preventDefault();
    setClassName("sign-up-js");
  };

  const handleOnChangeLogin = (e) => {
    e.preventDefault();
    const { id, value } = e.target;
    setLoginData((prev) => ({
      ...prev,
      [id]: value,
    }));
  };
  //onchange
  const handleOnChangeSignUp = (e) => {
    e.preventDefault();
    const { id, value } = e.target;
    console.log(signUpData);
    setSignUpData((prev) => ({
      ...prev,
      [id]: value,
    }));
  };

  //eventos de login ou criar conta
  const handleOnLogin = async (e) => {
    e.preventDefault();
    console.log(loginData);
    if (loginData.email && loginData.senha) {
      await doLogin(loginData.email, loginData.senha);
    } else {
      alert("PREENCHA TODOS OS DADOS");
    }
  };
  const handleOnSignUp = async (e) => {
    e.preventDefault();
    if (signUpData.nome && signUpData.email && signUpData.senha) {
      await requestCreateUser(
        signUpData.nome,
        signUpData.email,
        signUpData.senha,
        "ATIVO",
        "ADMIN",
        0
      );
      await doLogin(signUpData.email, signUpData.senha);
    } else {
      alert("PREENCHA TODOS OS DADOS");
    }
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        width: "100%",
        height: "100%",
      }}
    >
      <div className="container">
        <div className="content first-content">
          <div className="first-column">
            <h2 className="title title-primary">Seja bem-vindo novamente!</h2>
            <p className="description description-primary">
              para manter-se conectado
            </p>
            <p className="description description-primary">
              faça login com seus dados
            </p>
            <button
              id="signin"
              className="btn btn-primary"
              onClick={(e) => handleOnClickSignIn(e)}
            >
              sign in
            </button>
          </div>
          <div className="second-column">
            <h2 className="title title-second">Crie sua conta de admin</h2>
            <form className="form">
              <label className="label-input">
                <i className="far fa-user icon-modify"></i>
                <input
                  type="text"
                  placeholder="Nome"
                  id="nome"
                  onChange={(e) => handleOnChangeSignUp(e)}
                />
              </label>

              <label className="label-input">
                <i className="far fa-envelope icon-modify"></i>
                <input
                  type="email"
                  placeholder="Email"
                  id="email"
                  onChange={(e) => handleOnChangeSignUp(e)}
                />
              </label>

              <label className="label-input">
                <i className="fas fa-lock icon-modify"></i>
                <input
                  type="password"
                  placeholder="Senha"
                  id="senha"
                  onChange={(e) => handleOnChangeSignUp(e)}
                />
              </label>

              <button
                className="btn btn-second"
                onClick={(e) => handleOnSignUp(e)}
              >
                sign up
              </button>
            </form>
          </div>
        </div>
        <div className="content second-content">
          <div className="first-column">
            <h2 className="title title-primary">Olá!</h2>
            <p className="description description-primary">
              Preencha alguns dados
            </p>
            <p className="description description-primary">
              e comece sua jornada conosco
            </p>
            <button
              id="signup"
              className="btn btn-primary"
              onClick={(e) => handleOnClickSignUp(e)}
            >
              sign up
            </button>
          </div>
          <div className="second-column">
            <h2 className="title title-second">Entre no sistema</h2>
            <form className="form">
              <label className="label-input">
                <i className="far fa-envelope icon-modify"></i>
                <input
                  type="email"
                  placeholder="Email"
                  id="email"
                  onChange={(e) => handleOnChangeLogin(e)}
                />
              </label>

              <label className="label-input">
                <i className="fas fa-lock icon-modify"></i>
                <input
                  type="password"
                  placeholder="Senha"
                  id="senha"
                  onChange={(e) => handleOnChangeLogin(e)}
                />
              </label>

              <a className="password" href="#">
                Esqueci minha senha
              </a>
              <button
                className="btn btn-second"
                onClick={(e) => handleOnLogin(e)}
              >
                sign in
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
