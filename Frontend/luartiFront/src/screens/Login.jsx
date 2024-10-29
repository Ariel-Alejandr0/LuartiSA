import React, { useEffect, useState } from "react";

export default function Login() {
  const [className, setClassName] = useState("");

  useEffect(() => {
    document.body.className = className;
  }, [className]);

  const handleOnClickSignIn = (e) => {
    e.preventDefault();
    setClassName("sign-in-js");
  };

  const handleOnClickSignUp = (e) => {
    e.preventDefault();
    setClassName("sign-up-js");
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
                <input type="text" placeholder="Nome" />
              </label>

              <label className="label-input">
                <i className="far fa-envelope icon-modify"></i>
                <input type="email" placeholder="Email" />
              </label>

              <label className="label-input">
                <i className="fas fa-lock icon-modify"></i>
                <input type="password" placeholder="Senha" />
              </label>

              <button
                className="btn btn-second"
                onClick={(e) => handleOnClickSignUp(e)}
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
                <input type="email" placeholder="Email" />
              </label>

              <label className="label-input">
                <i className="fas fa-lock icon-modify"></i>
                <input type="password" placeholder="Senha" />
              </label>

              <a className="password" href="#">
                Esqueci minha senha
              </a>
              <button
                className="btn btn-second"
                onClick={(e) => handleOnClickSignIn(e)}
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
