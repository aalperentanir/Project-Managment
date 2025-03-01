import React, { useState } from "react";
import Register from "./Register";
import Login from "./Login";
import { Button } from "../../components/ui/button";
import "./Auth.css"
export const Auth = () => {
  const [active, setActive] = useState(true);
  return (
    <div className="loginContainer">
      <div className="box h-[30rem] w-[25rem]">
        <div className="minContainer login">
          <div className="loginBox w-full px-10 space-y-5">
            {active ? <Register /> : <Login />}

            <div>
              <span>Already have an account ? </span>
              <Button variant="ghost" onClick={() => setActive(!active)}>
                {active ? "signin" : "signup"}
              </Button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Auth;
